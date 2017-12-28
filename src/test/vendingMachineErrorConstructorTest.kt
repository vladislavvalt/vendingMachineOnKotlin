package test

import controllers.VendingMachineImpl
import domain.Product
import kotlin.test.assertFailsWith

fun main(args: Array<String>) {
    assertFailsWith(IllegalArgumentException::class, {
        VendingMachineImpl(2,
                mapOf(1 to VendingMachineImpl.SlotContent(Product("Coca cola", 5), 3)))
    })
    assertFailsWith(IllegalArgumentException::class, {
        VendingMachineImpl(-1,
                mapOf(1 to VendingMachineImpl.SlotContent(Product("Coca cola", 5), 3)))
    })
}