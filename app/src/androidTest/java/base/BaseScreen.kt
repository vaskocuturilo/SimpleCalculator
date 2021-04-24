package base

import androidx.test.InstrumentationRegistry
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2

abstract class BaseScreen(private val device: UiDevice) {

    val id = "${InstrumentationRegistry.getTargetContext().packageName}:id"

    fun findElement(by: BySelector): UiObject2 = device.findObject(by)

    fun clickElement(by: BySelector) = findElement(by).click()
}