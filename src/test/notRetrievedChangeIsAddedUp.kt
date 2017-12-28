package test

import controllers.VendingMachine
import controllers.VendingMachineImpl
import domain.Banknote
import domain.Product
import kotlin.test.assertEquals

fun main(args: Array<String>) {
    val vendingMachine: VendingMachine = VendingMachineImpl(4,
            mapOf(1 to VendingMachineImpl.SlotContent(Product("Water", 3), 3)
            ),
            mutableListOf(Banknote.ONE, Banknote.TWO)
    )

    vendingMachine.insertBanknote(Banknote.FIVE)
    assertEquals(5, vendingMachine.currentBalance())
    vendingMachine.clickNumberButton(1)
    vendingMachine.clickBuyButton()
    vendingMachine.retrievePurchasedProduct()
    vendingMachine.insertBanknote(Banknote.TWO)
    vendingMachine.insertBanknote(Banknote.TWO)
    vendingMachine.clickNumberButton(1)
    vendingMachine.clickBuyButton()
    vendingMachine.retrievePurchasedProduct()
    assertEquals(listOf(Banknote.TWO, Banknote.ONE), vendingMachine.retrieveChange())
}