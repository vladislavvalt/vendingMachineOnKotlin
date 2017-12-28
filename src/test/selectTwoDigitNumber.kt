package test

import controllers.VendingMachine
import controllers.VendingMachineImpl
import domain.Banknote
import domain.Product
import kotlin.test.assertEquals
import kotlin.test.assertNull

fun main(args: Array<String>) {
    val vendingMachine: VendingMachine = VendingMachineImpl(4,
            mapOf(31 to VendingMachineImpl.SlotContent(Product("Coca cola", 5), 3)
            )
    )

    vendingMachine.insertBanknote(Banknote.FIVE)
    assertEquals(5, vendingMachine.currentBalance())
    vendingMachine.clickNumberButton(1)
    vendingMachine.clickBuyButton()

    assertNull(vendingMachine.retrievePurchasedProduct())
    assertEquals(emptyList(), vendingMachine.retrieveChange())
    assertEquals(5, vendingMachine.currentBalance())

    vendingMachine.clickNumberButton(2)
    vendingMachine.clickBuyButton()

    assertNull(vendingMachine.retrievePurchasedProduct())
    assertEquals(emptyList(), vendingMachine.retrieveChange())
    assertEquals(5, vendingMachine.currentBalance())

    vendingMachine.clickCancelButton()
    assertEquals(listOf(Banknote.FIVE), vendingMachine.retrieveChange())
    vendingMachine.insertBanknote(Banknote.FIVE)
    vendingMachine.clickNumberButton(3)
    vendingMachine.clickNumberButton(1)
    vendingMachine.clickBuyButton()
    assertEquals(Product("Coca cola", 5), vendingMachine.retrievePurchasedProduct())
    assertEquals(emptyList(), vendingMachine.retrieveChange())
    assertEquals(0, vendingMachine.currentBalance())
}