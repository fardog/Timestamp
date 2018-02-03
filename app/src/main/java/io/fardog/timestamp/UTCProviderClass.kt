package io.fardog.timestamp

import android.support.wearable.complications.ComplicationData
import android.support.wearable.complications.ComplicationManager
import android.support.wearable.complications.ComplicationProviderService
import android.support.wearable.complications.ComplicationText
import java.util.*

class UTCProviderClass : ComplicationProviderService() {
    override fun onComplicationUpdate(id: Int, complicationType: Int, manager: ComplicationManager?) {
        val fmt = when(complicationType) {
            ComplicationData.TYPE_SHORT_TEXT ->
                "h:mm"
            ComplicationData.TYPE_LONG_TEXT ->
                "HH:mm 'UTC'"
            else ->
                null
        } ?: return

        val title = ComplicationText.plainText("UTC")
        val text = ComplicationText.TimeFormatBuilder()
                .setFormat(fmt)
                .setTimeZone(TimeZone.getTimeZone("Etc/UTC"))
                .build()

        val data = when(complicationType) {
            ComplicationData.TYPE_SHORT_TEXT ->
                ComplicationData.Builder(ComplicationData.TYPE_SHORT_TEXT)
                        .setShortTitle(title)
                        .setShortText(text)
                        .build()
            ComplicationData.TYPE_LONG_TEXT ->
                ComplicationData.Builder(ComplicationData.TYPE_LONG_TEXT)
                        .setLongText(text)
                        .build()
            else ->
                null
        }

        if (data != null) {
            manager?.updateComplicationData(id, data)
        }
    }
}