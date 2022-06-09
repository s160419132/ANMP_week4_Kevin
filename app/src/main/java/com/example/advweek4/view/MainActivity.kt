package com.example.advweek4.view

import android.app.Notification
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.advweek4.R
import com.example.advweek4.util.CreatNotificationChannel

class MainActivity : AppCompatActivity() {

    init {
        instance=this
    }
    companion object{
        private var instance:MainActivity?=null
        fun showNotification(title:String,content:String,icon:Int){
            val channelId ="${instance?.packageName}-${instance?.getString(R.string.app_name)}"
            val builder =NotificationCompat.Builder(instance!!.applicationContext,channelId)
                .apply {
                    setSmallIcon(icon)
                    setContentTitle(title)
                    setContentText(content)
                    setStyle(NotificationCompat.BigPictureStyle())
                    priority =NotificationCompat.PRIORITY_DEFAULT
                    setAutoCancel(true)
                 }
            val notif = NotificationManagerCompat.from(instance!!.applicationContext)
            notif.notify(1001,builder.build())
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CreatNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, true,getString(R.string.app_name) , "App Channel")
    }
}