package test

import controllers.VendingMachine
import controllers.VendingMachineImpl
import domain.Banknote
import domain.Product
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

fun main(args: Array<String>) {
    val vendingMachine: VendingMachine = VendingMachineImpl(4,
            mapOf(1 to VendingMachineImpl.SlotContent(Product("Coca cola", 5), 3)
            ),
            mutableListOf(Banknote.ONE, Banknote.TWO)
    )

    vendingMachine.insertBanknote(Banknote.FIVE)
    assertEquals(5, vendingMachine.currentBalance())
    vendingMachine.clickNumberButton(1)
    vendingMachine.clickBuyButton()

    assertEquals(Product("Coca cola", 5), vendingMachine.retrievePurchasedProduct())
    assertEquals(emptyList(), vendingMachine.retrieveChange())
    assertEquals(0, vendingMachine.currentBalance())


    vendingMachine.insertBanknote(Banknote.TWO)
    vendingMachine.insertBanknote(Banknote.TWO)
    vendingMachine.insertBanknote(Banknote.TWO)
    assertEquals(6, vendingMachine.currentBalance())
    vendingMachine.clickNumberButton(1)
    vendingMachine.clickBuyButton()

    assertEquals(Product("Coca cola", 5), vendingMachine.retrievePurchasedProduct())
    assertEquals(listOf(Banknote.ONE), vendingMachine.retrieveChange())
    assertEquals(0, vendingMachine.currentBalance())


    vendingMachine.insertBanknote(Banknote.FIVE)
    vendingMachine.insertBanknote(Banknote.TWO)
    assertEquals(7, vendingMachine.currentBalance())
    vendingMachine.clickNumberButton(1)
    vendingMachine.clickBuyButton()

    assertEquals(Product("Coca cola", 5), vendingMachine.retrievePurchasedProduct())
    assertEquals(listOf(Banknote.TWO), vendingMachine.retrieveChange())
    assertEquals(0, vendingMachine.currentBalance())


    // No cola left
    vendingMachine.insertBanknote(Banknote.FIVE)
    assertEquals(5, vendingMachine.currentBalance())
    vendingMachine.clickNumberButton(1)
    vendingMachine.clickBuyButton()

    assertEquals(5, vendingMachine.currentBalance())
    assertNull(vendingMachine.retrievePurchasedProduct())
    assertTrue(vendingMachine.retrieveChange().isEmpty())

    vendingMachine.clickCancelButton()
    assertEquals(0, vendingMachine.currentBalance())
    assertNull(vendingMachine.retrievePurchasedProduct())
    assertEquals(listOf(Banknote.FIVE), vendingMachine.retrieveChange())
}