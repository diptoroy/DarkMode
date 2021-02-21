package com.example.darkmode.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.darkmode.R
import com.example.darkmode.modePresenter.DataStoreClass
import com.example.darkmode.modePresenter.PrefClass
import com.example.darkmode.util.UiMode
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.coroutines.launch

class SettingActivity : AppCompatActivity() {
    //SharedPrefrence
    private val pref by lazy { PrefClass(this) }

    //DataStore
    private lateinit var dataStoreClass: DataStoreClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

//        //SharedPrefrence
//        if ( pref.getBoolean("pref_is_dark") ) switch_theme.isChecked = true
//
//        switch_theme.setOnCheckedChangeListener { _, checked ->
//            if (checked) {
//                pref.put("pref_is_dark", true)
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//            } else {
//                pref.put("pref_is_dark", false)
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//            }
//        }

        //DataStore
        dataStoreClass = DataStoreClass(this)
        observUi()
        initView()
    }

    private fun observUi() {
        dataStoreClass.uiModeFlow.asLiveData().observe(this) { uiMode ->
            setCheckedMode(uiMode)
        }
    }

    private fun setCheckedMode(uiMode: UiMode?) {
        when (uiMode) {
            UiMode.LIGHT -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switch_theme.isChecked = false
            }
            UiMode.DARK -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switch_theme.isChecked = true
            }
        }
    }

    private fun initView() {
        switch_theme.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                when (isChecked) {
                    true -> dataStoreClass.setDarkMode(UiMode.DARK)
                    false -> dataStoreClass.setDarkMode(UiMode.LIGHT)
                }
            }
        }
    }
}