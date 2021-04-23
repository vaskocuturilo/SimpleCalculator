package smoke

import base.BaseClass
import junit.framework.Assert.assertEquals
import org.junit.Test
import screens.Calculator

class SimpleAdditionalTest: BaseClass() {

    private val randomNumber: Int get() = (Math.random() * 10).toInt()

    private val firstNumber = randomNumber
    private val secondNumber = randomNumber

    @Test
    fun simpleAdditionalTest() {
        val calculator = Calculator(on = device)
            .enter(firstNumber)
            .plus(secondNumber)

        assertEquals(firstNumber + secondNumber, calculator.numberOnScreen)
    }
}