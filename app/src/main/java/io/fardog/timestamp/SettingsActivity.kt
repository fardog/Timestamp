package io.fardog.timestamp

import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.preference.PreferenceFragment
import android.support.wearable.activity.WearableActivity
import android.support.wearable.complications.ComplicationProviderService
import android.support.wearable.complications.ProviderUpdateRequester

class SettingsActivity : WearableActivity() {
    companion object {
        private fun updateComplication(context: Context, cls: Class<out ComplicationProviderService>) {
            val component = ComponentName(context, cls)
            val req = ProviderUpdateRequester(context, component)
            req.requestUpdateAll()
        }
    }

    override fun onDestroy() {
        // update complications for new settings
        SettingsActivity.updateComplication(this, UTCProviderClass::class.java)
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Enables Always-on
        setAmbientEnabled()
    }

    class UTCPreferenceFragment : PreferenceFragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.utc_settings)
        }
    }
}
