package com.idzayu.viewedlist.model

import android.R
import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.idzayu.viewedlist.MainActivity
import com.idzayu.viewedlist.model.repo.MovieList


class SeeLaterReceiver : BroadcastReceiver() {

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onReceive(context: Context?, intent: Intent?) {
        val intents = Intent(context, MainActivity::class.java)
        intents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intents.putExtra("Position", MovieList.getPositionSelectedMovie())
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intents,
            PendingIntent.FLAG_ONE_SHOT
        )
        val CHANNEL_ID = "channel"
        val mBuilder = context?.let {
            NotificationCompat.Builder(it, CHANNEL_ID)
                .setSmallIcon(R.mipmap.sym_def_app_icon) //иконка уведомления
                .setAutoCancel(true) //уведомление закроется по клику на него
                .setTicker("Hello") //текст, который отобразится вверху статус-бара при создании уведомления
                .setContentText("Suck my **** bitch!!!") // Основной текст уведомления
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis()) //отображаемое время уведомления
                .setContentTitle("AppName") //заголовок уведомления
                .setDefaults(Notification.DEFAULT_ALL)

        }


        if (mBuilder != null) {
            context.let { NotificationManagerCompat.from(it) }.notify(2, mBuilder.build())
        }
    }

}
