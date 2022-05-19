package com.example.coursework.services

import android.app.NotificationChannel
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.coursework.R
import com.example.coursework.network.LinksNews
import com.example.coursework.network.NetWorkGoogleNews
import com.example.coursework.network.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.ThreadLocalRandom

class TimerTaskService(
    private val builder: NotificationCompat.Builder,
    private val notificationManager: NotificationManagerCompat,
    private val channel: NotificationChannel
) : TimerTask() {

    companion object{
        const val NOTIFICATION_ID = 451
    }

    override fun run() {

        notificationManager.createNotificationChannel(channel)

        Log.i("test", "notification")

        NetWorkGoogleNews.getNewWork()
            .create(LinksNews::class.java)
            .getNews(size = 10)
            .enqueue(object : Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {


                    val body = response.body() as News

                    val article = body.articles[ThreadLocalRandom.current().nextInt(body.articles.size)]

                    builder.setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentText(article.url)
                        .setContentTitle(article.title)
                        .setAutoCancel(true)

                    notificationManager.notify(NOTIFICATION_ID, builder.build())


                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.e("error", t.toString());
                }

            })

    }

}