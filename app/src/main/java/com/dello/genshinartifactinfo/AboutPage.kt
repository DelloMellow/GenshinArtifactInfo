package com.dello.genshinartifactinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class AboutPage : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_page)

        val btnBackToMain: Button = findViewById(R.id.btnBackToMain)
        btnBackToMain.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val backToMain =Intent(this@AboutPage, MainActivity::class.java)
        startActivity(backToMain)
        finish()
    }
}