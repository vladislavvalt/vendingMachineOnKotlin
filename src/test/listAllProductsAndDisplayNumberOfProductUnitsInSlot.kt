package test

import controllers.VendingMachine
import controllers.VendingMachineImpl
import domain.Product
import kotlin.test.assertEquals

fun main(args: Array<String>) {
    val vendingMachine: VendingMachine = VendingMachineImpl(4,
            mapOf(1 to VendingMachineImpl.SlotContent(Product("Coca cola", 5), 3),
                    2 to VendingMachineImpl.SlotContent(Product("Twix", 7), 4)
            ))

    val allProducts = vendingMachine.listAllProducts()
    assertEquals(mapOf(1 to Product("Coca cola", 5),
            2 to Product("Twix", 7)), allProducts)

    assertEquals(3, vendingMachine.numberOfProductUnitsInSlot(1))
    assertEquals(4, vendingMachine.numberOfProductUnitsInSlot(2))
    assertEquals(0, vendingMachine.numberOfProductUnitsInSlot(3))
}