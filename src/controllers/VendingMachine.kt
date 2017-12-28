package controllers

import domain.Banknote
import domain.Product

interface VendingMachine {
    fun listAllProducts(): Map<Int, Product>

    fun numberOfProductUnitsInSlot(slotNumber: Int): Int

    fun insertBanknote(banknote: Banknote)

    fun currentBalance(): Int

    fun clickNumberButton(number: Int)

    fun clickBuyButton(): Boolean

    fun clickCancelButton()

    fun retrievePurchasedProduct(): Product?

    fun retrieveChange(): List<Banknote>
}