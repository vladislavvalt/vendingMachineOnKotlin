package controllers

import domain.Banknote
import domain.Product

class VendingMachineImpl : VendingMachine {

    class Builder {

    }

    private class Slot {

    }

    override fun listAllProducts(): Map<Int, Product> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun numberOfProductUnitsInSlot(slotNumber: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertBanknote(banknote: Banknote) {

    }

    override fun currentBalance(): Int = 1

    override fun clickNumberButton(number: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clickBuyButton() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clickCancelButton() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkChangeCompartment(): List<Banknote> = emptyList()
}