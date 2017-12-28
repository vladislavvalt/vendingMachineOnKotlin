package domain

enum class Banknote(val value: Int) {
    ONE(1) {
        override fun higher(): Banknote = TWO
        override fun lower(): Banknote? = null
    },
    TWO(2) {
        override fun higher(): Banknote = FIVE
        override fun lower(): Banknote = ONE
    },
    FIVE(5) {
        override fun higher(): Banknote? = null
        override fun lower(): Banknote = TWO
    };

    abstract fun higher(): Banknote?
    abstract fun lower(): Banknote?

    companion object {
        val HIGHEST_BANKNOTE = Banknote.FIVE
    }
}