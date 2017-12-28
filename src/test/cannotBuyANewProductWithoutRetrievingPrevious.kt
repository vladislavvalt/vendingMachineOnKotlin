package test

import controllers.VendingMachine
import controllers.VendingMachineImpl
import domain.Banknote
import domain.Product
import kotlin.test.assertEquals
import kotlin.test.assertFalse
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
    val result1 = vendingMachine.clickBuyButton()
    assertTrue(result1)

    vendingMachine.insertBanknote(Banknote.FIVE)
    assertEquals(5, vendingMachine.currentBalance())
    vendingMachine.clickNumberButton(1)
    val result2 = vendingMachine.clickBuyButton()
    assertFalse(result2)

    vendingMachine.retrievePurchasedProduct()
    val result3 = vendingMachine.clickBuyButton()
    assertTrue(result3)
}