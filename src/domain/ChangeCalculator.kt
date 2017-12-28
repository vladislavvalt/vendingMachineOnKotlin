package domain

object ChangeCalculator {

    fun possibleToGiveChange(amount: Int, availableBanknotes: List<Banknote>): Boolean {
        return calculateImpl(amount, availableBanknotes) != null
    }

    fun calculate(amount: Int, availableBanknotes: List<Banknote>): List<Banknote> {
        return calculateImpl(amount, availableBanknotes) ?: throw IllegalArgumentException("No way to give change")
    }

    private fun calculateImpl(amount: Int, availableBanknotes: List<Banknote>, currentBanknote: Banknote? = Banknote.HIGHEST_BANKNOTE): List<Banknote>? {
        if (amount == 0) {
            return emptyList()
        }
        if (currentBanknote == null) {
            return null
        }
        val banknotesOfCurrentValue = availableBanknotes.filter { it == currentBanknote }
        val numberOfCurrentBanknotesToGive = minOf(banknotesOfCurrentValue.size, amount / currentBanknote.value)

        val remainingAmount = amount - numberOfCurrentBanknotesToGive * currentBanknote.value
        return calculateImpl(
                remainingAmount,
                availableBanknotes,
                currentBanknote.lower())?.let {
            generateSequence { currentBanknote }.take(numberOfCurrentBanknotesToGive).toList().plus(it)
        }
    }

}