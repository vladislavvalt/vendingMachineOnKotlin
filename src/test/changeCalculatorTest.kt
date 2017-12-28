package test

import domain.ChangeCalculator
import domain.Banknote
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

fun main(args: Array<String>) {

    assertTrue(ChangeCalculator.possibleToGiveChange(1, listOf(Banknote.ONE)))
    assertTrue(ChangeCalculator.possibleToGiveChange(5, listOf(Banknote.FIVE)))
    assertFalse(ChangeCalculator.possibleToGiveChange(2, listOf(Banknote.FIVE)))

    assertTrue(ChangeCalculator.possibleToGiveChange(8, listOf(Banknote.FIVE, Banknote.TWO, Banknote.ONE)))
    assertFalse(ChangeCalculator.possibleToGiveChange(8, listOf(Banknote.FIVE, Banknote.TWO, Banknote.TWO)))

    assertEquals(emptyList(), ChangeCalculator.calculate(0, listOf(Banknote.FIVE, Banknote.ONE, Banknote.TWO)))
    assertEquals(listOf(Banknote.ONE), ChangeCalculator.calculate(1, listOf(Banknote.FIVE, Banknote.ONE, Banknote.TWO)))
    assertEquals(listOf(Banknote.TWO), ChangeCalculator.calculate(2, listOf(Banknote.FIVE, Banknote.ONE, Banknote.TWO)))
    assertEquals(listOf(Banknote.FIVE), ChangeCalculator.calculate(5, listOf(Banknote.FIVE, Banknote.ONE, Banknote.TWO)))

    assertEquals(listOf(Banknote.ONE, Banknote.ONE), ChangeCalculator.calculate(2, listOf(Banknote.FIVE, Banknote.ONE, Banknote.ONE)))

    assertEquals(listOf(Banknote.ONE, Banknote.ONE, Banknote.ONE, Banknote.ONE, Banknote.ONE), ChangeCalculator.calculate(5, listOf(Banknote.ONE, Banknote.ONE, Banknote.ONE, Banknote.ONE, Banknote.ONE)))

    assertEquals(listOf(Banknote.TWO, Banknote.TWO, Banknote.ONE), ChangeCalculator.calculate(5, listOf(Banknote.TWO, Banknote.TWO, Banknote.TWO, Banknote.ONE)))

    assertEquals(listOf(Banknote.FIVE, Banknote.TWO, Banknote.ONE, Banknote.ONE), ChangeCalculator.calculate(9, listOf(Banknote.FIVE, Banknote.FIVE, Banknote.TWO, Banknote.ONE, Banknote.ONE, Banknote.ONE)))
}