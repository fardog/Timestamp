package io.fardog.timestamp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager

class TimestampHideReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?:return
        intent?:return

        val id = intent.getIntExtra("id", -1)
        if (id > 0) {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            prefs.edit().putBoolean(context.getString(R.string.storage_ts_timestamp_hide) + ".${id}", true).apply()
        }

        SettingsActivity.updateComplication(context, TimestampProviderClass::class.java)
    }
}
