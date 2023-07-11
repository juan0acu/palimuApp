package com.example.palimuapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView

class LoginActivity : AppCompatActivity() {

    private lateinit var card_correo:CardView
    private lateinit var card_facebook:CardView
    private lateinit var card_gmail:CardView
    private lateinit var btn_registrar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initComponents()
        initListener()
    }

    private fun initComponents(){
        card_correo = findViewById(R.id.card_correo)
        card_facebook= findViewById(R.id.card_facebook)
        card_gmail = findViewById(R.id.card_gmail)
        btn_registrar = findViewById(R.id.btn_registrar)
    }

    private fun initListener(){
        card_correo.setOnClickListener {
           val intent = Intent(this, LoginImboxActivity::class.java)
            startActivity(intent)
        }
        card_facebook.setOnClickListener {
            //TODO
        }
        card_gmail.setOnClickListener {
            //TODO
        }
        btn_registrar.setOnClickListener {
            //TODO
        }
    }
}