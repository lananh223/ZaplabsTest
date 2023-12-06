package com.lananh.android.zaplabstest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lananh.android.zaplabstest.view.PhotoFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fraggment = PhotoFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fraggment)
            .commit()
    }
}
