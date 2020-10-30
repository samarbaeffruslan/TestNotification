package com.samarbaeffruslan.testnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.ColorSpace
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_HIGH

const val NOTIFY_ID = 1;
const val CHANNEL_ID = "CHANNEL_ID"

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var notificationManager: NotificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener{
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            val pendingIntent = PendingIntent.getActivity(applicationContext, 0 , intent, PendingIntent.FLAG_UPDATE_CURRENT);
            val notificationBuilder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                    .setAutoCancel(false)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(pendingIntent)
                    .setContentTitle("Заголовок")
                    .setContentText("Какой-то текст...")
                    .setPriority(PRIORITY_HIGH)
            createChannelIfNeeded(notificationManager)
            notificationManager.notify(NOTIFY_ID, notificationBuilder.build())

        }

    }

    fun createChannelIfNeeded(manager: NotificationManager ){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            var notification: NotificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(notification)
        }

    }
}