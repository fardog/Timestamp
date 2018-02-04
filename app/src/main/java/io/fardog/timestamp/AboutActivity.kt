package io.fardog.timestamp

import android.os.Bundle
import android.support.wearable.activity.WearableActivity

class AboutActivity : WearableActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        // Enables Always-on
        setAmbientEnabled()
    }
}
