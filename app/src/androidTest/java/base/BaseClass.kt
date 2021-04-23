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
import org.junit.Before
import org.junit.Ignore
import org.junit.runner.RunWith

@Ignore("Ignoring BaseClass")
@RunWith(AndroidJUnit4::class)
abstract class BaseClass() {

    lateinit var device: UiDevice

    @Before
    fun setUp() {
        device = startDevice()
    }

    private fun startDevice(): UiDevice {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        val pkg = InstrumentationRegistry.getTargetContext().packageName
        val context = InstrumentationRegistry.getContext()
        val intent = getIntent(context, pkg)
        val launchTimeout = 10000L

        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(pkg).depth(0)), launchTimeout)

        return device
    }

    private fun getIntent(context: Context, pkg: String): Intent? = context.packageManager
        .getLaunchIntentForPackage(pkg)
        ?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

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