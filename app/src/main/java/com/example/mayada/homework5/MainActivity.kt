package com.example.mayada.homework5

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.mayada.homework5.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.supportFragmentManager.beginTransaction().add(R.id.fragments_host, MainFragment()).commit()
    }

    fun setActionBarTitle(title: String) {
        supportActionBar!!.title = title
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
