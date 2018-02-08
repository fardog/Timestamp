package io.fardog.timestamp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.preference.PreferenceManager
import android.support.wearable.complications.ComplicationData
import android.support.wearable.complications.ComplicationManager
import android.support.wearable.complications.ComplicationProviderService
import android.support.wearable.complications.ComplicationText

class TimestampProviderClass : ComplicationProviderService() {

    override fun onComplicationUpdate(id: Int, complicationType: Int, manager: ComplicationManager?) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val useMillis = prefs.getBoolean(getString(R.string.ts_setting_use_millis_key), true)
        val shouldHide = prefs.getBoolean(getString(R.string.storage_ts_timestamp_hide) + ".${id}", true)
        val dismissAfter = prefs.getString(getString(R.string.ts_setting_dismiss_after_key), "7").toIntOrNull()?:7

        val text = if(shouldHide) {
            ComplicationText.plainText(getString(R.string.ts_complication_label_default))
        } else {
            val intent = Intent(this, TimestampHideReceiver::class.java).putExtra("id", id)
            val alarm: AlarmManager? = this.getSystemService(Context.ALARM_SERVICE) as? AlarmManager

            alarm?.setExact(
                    AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() + dismissAfter * 1000,
                    PendingIntent.getBroadcast(this, id, intent, PendingIntent.FLAG_CANCEL_CURRENT)
            )
            val ts = when(useMillis) {
                true ->
                    System.currentTimeMillis()
                false ->
                    System.currentTimeMillis() / 1000
            }.toString()
            ComplicationText.plainText(when(useMillis) { true -> ts + "ms" false -> ts + "s"})
        }

        val intent = Intent(this, TimestampShowReceiver::class.java).putExtra("id", id)
        val data = when(complicationType) {
            ComplicationData.TYPE_LONG_TEXT ->
                    ComplicationData.Builder(ComplicationData.TYPE_LONG_TEXT)
                            .setIcon(Icon.createWithResource(this, R.drawable.ic_ts))
                            .setLongText(text)
                            .setTapAction(PendingIntent.getBroadcast(this, id, intent, PendingIntent.FLAG_CANCEL_CURRENT))
            else ->
                    null
        }?:return

        manager?.updateComplicationData(id, data.build())
    }
}