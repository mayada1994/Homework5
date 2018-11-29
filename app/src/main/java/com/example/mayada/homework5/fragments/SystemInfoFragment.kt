package com.example.mayada.homework5.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mayada.homework5.MainActivity
import com.example.mayada.homework5.R
import java.text.SimpleDateFormat
import java.util.*

class SystemInfoFragment : Fragment() {

    val broadcast: BroadcastReceiver by lazy(this::SystemInfoBroadcastReceiver)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as MainActivity).setActionBarTitle("System Info")
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        return inflater.inflate(R.layout.fragment_system_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateTime()
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(ConnectivityManager.CONNECTIVITY_ACTION)
            addAction(Intent.ACTION_HEADSET_PLUG)
            addAction(Intent.ACTION_HEADSET_PLUG)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_TIMEZONE_CHANGED)
        }
        context?.registerReceiver(broadcast, filter)

    }

    override fun onStop() {
        super.onStop()
        context?.unregisterReceiver(broadcast)
    }

    fun updateTime() {
        val textView = view!!.findViewById(R.id.text_date_time) as TextView
        val timeZone = TimeZone.getDefault()
        val timeText = "Current time:  ${SimpleDateFormat("hh:mm:ss").format(Date())} " +
                "\nCurrent timezone: ${timeZone.getDisplayName(false, TimeZone.SHORT)}"
        textView.text = timeText

    }

    fun updateNetwork() {
        val txtView = view!!.findViewById(R.id.text_network) as TextView
        val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected) {
            txtView.text = "Internet connection is available"
        } else txtView.text = "Internet connection is not available"
    }

    fun updateHeadset(intent: Intent) {
        val txtView = view!!.findViewById(R.id.text_headphones) as TextView
        val state = intent.getIntExtra("state", -1)
        when (state) {
            0 -> txtView.text = "Headphones are unplugged"
            1 -> txtView.text = "Headphones are plugged"
            else -> txtView.text = "No data"
        }
    }

    inner class SystemInfoBroadcastReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                ConnectivityManager.CONNECTIVITY_ACTION -> {
                    updateNetwork()
                }
                Intent.ACTION_HEADSET_PLUG -> {
                    updateHeadset(intent)
                }
                Intent.ACTION_POWER_CONNECTED -> {
                    val txtView = view!!.findViewById(R.id.text_charge) as TextView
                    txtView.text = "AC Adapter connected"
                }
                Intent.ACTION_POWER_DISCONNECTED -> {
                    val txtView = view!!.findViewById(R.id.text_charge) as TextView
                    txtView.text = "No connected AC adapter"
                }
                Intent.ACTION_TIME_CHANGED -> {
                    updateTime()
                }
                Intent.ACTION_TIMEZONE_CHANGED -> {
                    updateTime()
                }
            }
        }
    }

}