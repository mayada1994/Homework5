package com.example.mayada.homework5.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mayada.homework5.MainActivity
import com.example.mayada.homework5.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment: Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as MainActivity).setActionBarTitle("Homework 5")
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        system_info_button.setOnClickListener(this)
        communication_buttton.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.system_info_button -> {
                fragmentManager?.apply {
                    beginTransaction()
                        .replace(R.id.fragments_host, SystemInfoFragment())
                        .addToBackStack(null)
                        .commit()
                }
            }
            R.id.communication_buttton -> {
                fragmentManager?.apply {
                    beginTransaction()
                        .replace(R.id.fragments_host, CommunicationFragment())
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }
}