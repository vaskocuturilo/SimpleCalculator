package base

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.squareup.spoon.Spoon
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith

@Ignore("Ignoring BaseClass")
@RunWith(AndroidJUnit4::class)
abstract class BaseClass() {

    lateinit var device: UiDevice

    @Before
    fun setUp() {
        device = startEmulator()
    }

    private fun startEmulator(): UiDevice {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        val pkg = InstrumentationRegistry.getInstrumentation().targetContext.packageName
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = getIntent(context, pkg)
        val launchTimeout = 10000L

        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(pkg).depth(0)), launchTimeout)
        Spoon.screenshot(activity, "on_start")
        return device
    }

    private fun getIntent(context: Context, pkg: String): Intent? = context.packageManager
        .getLaunchIntentForPackage(pkg)
        ?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)


    @Rule
    @JvmField
    var watcher = object : TestWatcher() {
        override fun failed(e: Throwable, description: Description) {
            Spoon.screenshot(
                activity,
                "on_failed",
                description.testClass.name,
                description.methodName
            )
        }

        override fun succeeded(description: Description) {
            Spoon.screenshot(
                activity,
                "on_finish",
                description.testClass.name,
                description.methodName
            )
        }
    }

    private val activity: Activity?
        get() {
            var activity: Activity? = null
            InstrumentationRegistry.getInstrumentation().runOnMainSync {
                activity = ActivityLifecycleMonitorRegistry
                    .getInstance()
                    .getActivitiesInStage(Stage.RESUMED)
                    .first()

            }
            return activity
        }
}