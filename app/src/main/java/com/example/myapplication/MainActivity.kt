package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*

import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    val client = HttpClient(CIO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainScope().launch(Dispatchers.IO) {
            var iq = client.get<String>( host = "192.168.111.247", port = 8080)
            withContext(Dispatchers.Main){
                findViewById<TextView>(R.id.text).text = iq
            }


        findViewById<Button>(R.id.but).setOnClickListener {
            MainScope().launch(Dispatchers.IO) {
                client.get<String>( host = "192.168.111.247", port = 8080, path = "/cheanime"){
                    val text =  withContext(Dispatchers.Main){
                    findViewById<EditText>(R.id.text2).text.toString()
                }
                    parameter("state", text)
                }



        }
}
    }
}
}

