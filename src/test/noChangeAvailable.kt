package test

import controllers.VendingMachine
import controllers.VendingMachineImpl
import domain.Banknote
import domain.Product
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull

fun main(args: Array<String>) {

    val vendingMachine: VendingMachine = VendingMachineImpl(4,
            mapOf(1 to VendingMachineImpl.SlotContent(Product("Lion", 6), 3),
                    2 to VendingMachineImpl.SlotContent(Product("Water", 3), 2)
            ),
            mutableListOf(Banknote.FIVE, Banknote.ONE)
    )

    vendingMachine.insertBanknote(Banknote.TWO)
    vendingMachine.insertBanknote(Banknote.TWO)
    vendingMachine.insertBanknote(Banknote.TWO)
    vendingMachine.insertBanknote(Banknote.TWO)
    vendingMachine.insertBanknote(Banknote.TWO)
    vendingMachine.insertBanknote(Banknote.TWO)

    assertEquals(12, vendingMachine.currentBalance())
    vendingMachine.clickNumberButton(1)
    vendingMachine.clickBuyButton()

    assertEquals(Product("Lion", 6), vendingMachine.retrievePurchasedProduct())
    assertEquals(listOf(Banknote.FIVE, Banknote.ONE), vendingMachine.retrieveChange())

    vendingMachine.insertBanknote(Banknote.FIVE)
    vendingMachine.clickNumberButton(2)
    vendingMachine.clickBuyButton()

    assertEquals(Product("Water", 3), vendingMachine.retrievePurchasedProduct())
    assertEquals(listOf(Banknote.TWO), vendingMachine.retrieveChange())

    vendingMachine.insertBanknote(Banknote.TWO)
    vendingMachine.insertBanknote(Banknote.TWO)
    vendingMachine.clickNumberButton(2)
    val result = vendingMachine.clickBuyButton()
    assertFalse(result)
    assertEquals(4, vendingMachine.currentBalance())
    assertNull(vendingMachine.retrievePurchasedProduct())
    assertEquals(emptyList(), vendingMachine.retrieveChange())

    vendingMachine.clickCancelButton()
    assertEquals(0, vendingMachine.currentBalance())
    assertNull(vendingMachine.retrievePurchasedProduct())
    assertEquals(listOf(Banknote.TWO, Banknote.TWO), vendingMachine.retrieveChange())
}