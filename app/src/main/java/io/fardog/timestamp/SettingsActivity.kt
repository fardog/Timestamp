package io.fardog.timestamp

import android.content.ComponentName
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceFragment
import android.preference.PreferenceManager
import android.support.wearable.activity.WearableActivity
import android.support.wearable.complications.ComplicationProviderService
import android.support.wearable.complications.ProviderUpdateRequester

class SettingsActivity : WearableActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    companion object {
        private fun updateComplication(context: Context, cls: Class<out ComplicationProviderService>) {
            val component = ComponentName(context, cls)
            val req = ProviderUpdateRequester(context, component)
            req.requestUpdateAll()
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        // update complications for new settings
        SettingsActivity.updateComplication(this, UTCProviderClass::class.java)
    }

    override fun onResume() {
        super.onResume()
        PreferenceManager
                .getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        PreferenceManager
                .getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this)
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
