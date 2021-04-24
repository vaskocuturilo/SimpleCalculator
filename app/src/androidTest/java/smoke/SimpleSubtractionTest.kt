package smoke

import base.BaseClass
import junit.framework.Assert.assertEquals
import org.junit.Test
import screens.CalculatorPage

class SimpleSubtractionTest : BaseClass() {

    private val randomNumber: Int get() = (Math.random() * 10).toInt()

    private val firstNumber = randomNumber
    private val secondNumber = randomNumber

    @Test
    fun simpleSubtractionTest() {
        val calculate = CalculatorPage(on = device)
            .enterNumber(firstNumber)
            .operationSubtraction(secondNumber)

        assertEquals(firstNumber - secondNumber, calculate.numberOnScreen)
    }
}