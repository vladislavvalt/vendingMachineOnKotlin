package test

import controllers.VendingMachine
import controllers.VendingMachineImpl
import domain.Product
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

fun main(args: Array<String>) {
    val vendingMachine: VendingMachine = VendingMachineImpl(4,
            mapOf(1 to VendingMachineImpl.SlotContent(Product("Coca cola", 5), 3)
            ))
    assertEquals(0, vendingMachine.currentBalance())
    assertEquals(emptyList(), vendingMachine.retrieveChange())
    assertNull(vendingMachine.retrievePurchasedProduct())

    vendingMachine.clickBuyButton()
    assertEquals(3, vendingMachine.numberOfProductUnitsInSlot(1))
    assertNull(vendingMachine.retrievePurchasedProduct())
    assertTrue(vendingMachine.retrieveChange().isEmpty())

    vendingMachine.clickCancelButton()
    assertEquals(3, vendingMachine.numberOfProductUnitsInSlot(1))
    assertNull(vendingMachine.retrievePurchasedProduct())
    assertTrue(vendingMachine.retrieveChange().isEmpty())

    vendingMachine.clickNumberButton(1)
    vendingMachine.clickBuyButton()
    assertEquals(0, vendingMachine.currentBalance())
    assertEquals(emptyList(), vendingMachine.retrieveChange())
    assertNull(vendingMachine.retrievePurchasedProduct())
}