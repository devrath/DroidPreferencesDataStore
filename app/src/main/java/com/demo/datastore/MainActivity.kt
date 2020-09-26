package com.demo.datastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var dataManager: DataManager
    private var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Get reference to our userManager class
        dataManager = DataManager(this)

        // Observe data
        dataManager.userNameFlow.asLiveData().observe(this, {
            name = it
            tv_name.text = it.toString()
        })

        // Gets the input and save it
        btn_save.setOnClickListener {
            name = et_name.text.toString()

            //Stores the values
            GlobalScope.launch {
                dataManager.storeUser(name)
            }
        }
    }

}