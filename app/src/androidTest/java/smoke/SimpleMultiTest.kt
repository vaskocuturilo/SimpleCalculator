package smoke

import base.BaseClass
import junit.framework.Assert.assertEquals
import org.junit.Test
import screens.CalculatorPage

class SimpleMultiTest : BaseClass() {

    private val randomNumber: Int get() = (Math.random() + 10).toInt()
    private val firstNumber = randomNumber
    private val secondNumber = randomNumber

    @Test
    fun simpleMultiTest() {
        val calculate = CalculatorPage(on = device)
            .enterNumber(firstNumber)
            .operationMulti(secondNumber)

        assertEquals(firstNumber * secondNumber, calculate.numberOnScreen)
    }
}