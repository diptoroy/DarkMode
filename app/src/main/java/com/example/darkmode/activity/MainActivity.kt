package com.example.darkmode.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.darkmode.modePresenter.PrefClass
import com.example.darkmode.R
import com.example.darkmode.modePresenter.DataStoreClass
import com.example.darkmode.util.UiMode
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val pref by lazy { PrefClass(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //intent with putExtra
        nextActivity.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        settingactivity.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }


//        if (pref.getBoolean("pref_is_dark")) {
//            AppCompatDelegate.setDefaultNightMode( AppCompatDelegate.MODE_NIGHT_YES )
//        } else {
////            AppCompatDelegate.setDefaultNightMode( AppCompatDelegate.MODE_NIGHT_NO )
//            AppCompatDelegate.getDefaultNightMode() // set default from device
//        }
    }


}