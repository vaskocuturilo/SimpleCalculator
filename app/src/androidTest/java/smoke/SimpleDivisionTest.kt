package smoke

import base.BaseClass
import junit.framework.Assert.assertEquals
import org.junit.Test
import screens.CalculatorPage

class SimpleDivisionTest : BaseClass() {

    private val randomNumber: Int get() = (Math.random() * 10).toInt()

    private val firstNumber = randomNumber
    private val secondNumber = randomNumber

    @Test
    fun simpleDivisionTest() {
        val calculate = CalculatorPage(on = device)
            .enterNumber(firstNumber)
            .operationDivision(secondNumber)

        assertEquals(firstNumber / secondNumber, calculate.numberOnScreen)
    }
}