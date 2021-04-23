package screens

import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import base.BaseScreen

class Calculator(on: UiDevice) : BaseScreen(on) {

    private val plusButton = By.res("$id/button_add")
    private val equalButton = By.res("$id/button_calc")
    private val textView = By.res("$id/textView")

    private val numberPattern = "$id/button_%s"
    private val delimiterRegex = Regex("[^0-9]")

    val numberOnScreen: Int
        get() = findSelector(textView).text
            .replace(delimiterRegex, "")
            .toInt()

    fun enter(number: Int): Calculator {
        inputNumber(number)
        return this
    }

    fun plus(number: Int) = calculate(number, plusButton)

    private fun inputNumber(number: Int) = number
        .toString()
        .toCharArray()
        .forEach {
            click(By.res(String.format(numberPattern, it)))
        }

    private fun calculate(number: Int, operationButton: BySelector): Calculator {
        click(operationButton)
        inputNumber(number)
        click(equalButton)
        return this
    }
}