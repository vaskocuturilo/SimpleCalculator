package screens

import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import base.BaseScreen

class CalculatorPage(on: UiDevice) : BaseScreen(on) {

    //operation button
    private val addButton = By.res("$id/button_add")
    private val multiButton = By.res("$id/button_multi")
    private val divisionButton = By.res("$id/button_division")
    private val subtractionButton = By.res("$id/button_subtraction")
    private val calcButton = By.res("$id/button_calc")
    private val textView = By.res("$id/textView")

    //patterns
    private val numberPattern = "$id/button_%s"
    private val delimiterRegex = Regex("[^0-9]")

    val numberOnScreen: Int
        get() = findElement(textView).text
            .replace(delimiterRegex, "")
            .toInt()

    fun enterNumber(number: Int): CalculatorPage {
        inputNumber(number)
        return this
    }

    fun operationAdd(number: Int) = calculate(number, addButton)

    fun operationMulti(number: Int) = calculate(number, multiButton)

    fun operationDivision(number: Int) = calculate(number, divisionButton)

    fun operationSubtraction(number: Int) = calculate(number, subtractionButton)


    private fun inputNumber(number: Int) = number
        .toString()
        .toCharArray()
        .forEach {
            clickElement(By.res(String.format(numberPattern, it)))
        }

    private fun calculate(number: Int, operationButton: BySelector): CalculatorPage {
        clickElement(operationButton)
        inputNumber(number)
        clickElement(calcButton)
        return this
    }
}