package com.example.mayada.homework5.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.mayada.homework5.R
import kotlinx.android.synthetic.main.fragment_blue.*

class BlueFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val intent = Intent("MY_TEXT_CHANGE")
                intent.putExtra("EXTRA_TEXT_CHANGED", edit_text.text.toString())
                activity!!.sendBroadcast(intent)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        switch_blue_fragment.setOnCheckedChangeListener { buttonView, isChecked ->
            val intent = Intent("MY_SWITCH_CHANGE")
            intent.putExtra("EXTRA_SWITCH_CHANGED", isChecked)
            activity!!.sendBroadcast(intent)
        }
        button_blue_fragment.setOnTouchListener { v, event ->
            val intent = Intent("MY_BUTTON_CHANGE")
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    intent.putExtra("EXTRA_BUTTON_CHANGED", true)
                }
                MotionEvent.ACTION_UP -> {
                    intent.putExtra("EXTRA_BUTTON_CHANGED", false)
                }
            }
            activity!!.sendBroadcast(intent)
            v?.onTouchEvent(event) ?: true
        }
    }

}