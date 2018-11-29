package com.example.mayada.homework5.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mayada.homework5.MainActivity
import com.example.mayada.homework5.R

class CommunicationFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as MainActivity).setActionBarTitle("Fragments Communication")
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        return inflater.inflate(R.layout.fragment_communication, container, false)
    }
}