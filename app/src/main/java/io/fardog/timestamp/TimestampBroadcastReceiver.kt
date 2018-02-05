package io.fardog.timestamp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TimestampBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?:return
        SettingsActivity.updateComplication(context, TimestampProviderClass::class.java)
    }

}