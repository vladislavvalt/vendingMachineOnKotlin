package controllers

import domain.Banknote
import domain.ChangeCalculator
import domain.Product

class VendingMachineImpl(slotCapacity: Int = 4,
                         private val slotsWithProducts: Map<Int, SlotContent>,
                         initialBanknotes: List<Banknote> = emptyList()) : VendingMachine {
    data class SlotContent(val product: Product, var amount: Int)

    init {
        val slotWithIllegalAmountExists = slotsWithProducts.values.any { it.amount > slotCapacity || it.amount < 0 }
        if (slotWithIllegalAmountExists) {
            throw IllegalArgumentException()
        }
    }

    private val banknotes: MutableList<Banknote> = initialBanknotes.toMutableList()
    private var currentNumber: Int = -1
    private var balance: Int = 0

    private var productToRetrieve: Product? = null
    private var changeToRetrieve: List<Banknote> = emptyList()

    override fun listAllProducts(): Map<Int, Product> = slotsWithProducts.mapValues { it.value.product }

    override fun numberOfProductUnitsInSlot(slotNumber: Int): Int = slotsWithProducts[slotNumber]?.amount ?: 0

    override fun insertBanknote(banknote: Banknote) {
        banknotes.add(banknote)
        balance += banknote.value
    }

    override fun currentBalance(): Int = balance

    override fun clickNumberButton(number: Int) {
        currentNumber = if (currentNumber != -1) {
            currentNumber * 10 + number % 10
        } else {
            number % 10
        }
    }

    override fun clickBuyButton(): Boolean {

        if (numberOfProductUnitsInSlot(currentNumber) > 0 && productToRetrieve == null) {
            val product = slotsWithProducts[currentNumber]!!.product
            if (product.price <= balance) {
                val changeToGive = balance - product.price

                if (!ChangeCalculator.possibleToGiveChange(changeToGive, banknotes)) {
                    return false
                }
                productToRetrieve = product
                returnChange(changeToGive)
                slotsWithProducts[currentNumber]!!.amount -= 1
                currentNumber = -1
                return true
            }
        }
        return false
    }

    private fun returnChange(changeToGive: Int) {
        val change = ChangeCalculator.calculate(changeToGive, banknotes)
        banknotes.removeAll(change)
        changeToRetrieve = changeToRetrieve.plus(change)
        balance = 0
    }

    override fun clickCancelButton() {
        returnChange(balance)
        currentNumber = -1
    }

    override fun retrievePurchasedProduct(): Product? {
        val retrieved = productToRetrieve
        productToRetrieve = null
        return retrieved
    }

    override fun retrieveChange(): List<Banknote> {
        val retrievedChange = changeToRetrieve
        changeToRetrieve = emptyList()
        return retrievedChange
    }
}