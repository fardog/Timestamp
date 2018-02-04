package io.fardog.timestamp

import android.preference.PreferenceManager
import android.support.wearable.complications.ComplicationData
import android.support.wearable.complications.ComplicationManager
import android.support.wearable.complications.ComplicationProviderService
import android.support.wearable.complications.ComplicationText
import java.util.*

class UTCProviderClass : ComplicationProviderService() {
    override fun onComplicationUpdate(id: Int, complicationType: Int, manager: ComplicationManager?) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val showLeadingZero = prefs.getBoolean(getString(R.string.utc_setting_leading_zero_key), true)
        val showLabel = prefs.getBoolean(getString(R.string.utc_setting_utc_label_key), true)

        val fmt = when(showLeadingZero) {true -> "HH:mm" else -> "H:mm"}

        val title = ComplicationText.plainText("UTC")
        val text = ComplicationText.TimeFormatBuilder()
                .setFormat(fmt)
                .setTimeZone(TimeZone.getTimeZone("Etc/UTC"))
                .build()

        val data = when(complicationType) {
            ComplicationData.TYPE_SHORT_TEXT ->
                ComplicationData.Builder(ComplicationData.TYPE_SHORT_TEXT)
                        .setShortText(text)
            ComplicationData.TYPE_LONG_TEXT ->
                ComplicationData.Builder(ComplicationData.TYPE_LONG_TEXT)
                        .setLongText(text)
            else ->
                null
        }?:return

        if (showLabel) {
            when(complicationType) {
                ComplicationData.TYPE_SHORT_TEXT ->
                        data.setShortTitle(title)
                ComplicationData.TYPE_LONG_TEXT ->
                        data.setLongTitle(title)
            }
        }

        manager?.updateComplicationData(id, data.build())
    }
}