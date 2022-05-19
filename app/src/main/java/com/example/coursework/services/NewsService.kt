package com.example.coursework.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.*

class NewsService : Service() {

    companion object {
        const val CHANNEL_ID = "channel"
        const val NOTIFY_INTERVAL: Long = 600 * 1_000
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        val mTimer = Timer()

        val builder = NotificationCompat.Builder(this@NewsService, CHANNEL_ID)
        val notificationManager = NotificationManagerCompat.from(this@NewsService)

        val channel = NotificationChannel(CHANNEL_ID, "News", NotificationManager.IMPORTANCE_HIGH).apply {
            description = "Новость"
            enableLights(true)
            lightColor = Color.BLUE
            vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 100)
        }

        mTimer.scheduleAtFixedRate(TimerTaskService(builder, notificationManager, channel), 0, NOTIFY_INTERVAL)
    }
}