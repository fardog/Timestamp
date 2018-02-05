package io.fardog.timestamp

import android.app.PendingIntent
import android.content.Intent
import android.preference.PreferenceManager
import android.support.wearable.complications.ComplicationData
import android.support.wearable.complications.ComplicationManager
import android.support.wearable.complications.ComplicationProviderService
import android.support.wearable.complications.ComplicationText

class TimestampProviderClass : ComplicationProviderService() {

    override fun onComplicationUpdate(id: Int, complicationType: Int, manager: ComplicationManager?) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val useMillis = prefs.getBoolean(getString(R.string.ts_setting_use_millis_key), true)

        val ts = when(useMillis) {
            true ->
                System.currentTimeMillis()
            false ->
                System.currentTimeMillis() / 1000
        }.toString()

        val intent = Intent(this, TimestampBroadcastReceiver::class.java)
        val text = ComplicationText.plainText(when(useMillis) { true -> ts + "ms" false -> ts + "s"})
        val data = when(complicationType) {
            ComplicationData.TYPE_LONG_TEXT ->
                    ComplicationData.Builder(ComplicationData.TYPE_LONG_TEXT)
                            .setLongText(text)
                            .setTapAction(PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT))
                            .build()
            else ->
                    null
        }?:return

        manager?.updateComplicationData(id, data)
    }
}