package com.example.kopilimbo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.kopilimbo.isidaftar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnLogout: Button = findViewById(R.id.btnout)
        val btnisda: Button = findViewById(R.id.isda)
        val btn_produk: Button = findViewById(R.id.btn_produk)
        val savedLogin = getSharedPreferences("Login", MODE_PRIVATE)
        val editSavedLogin = savedLogin.edit()
        btnLogout.setOnClickListener {
            editSavedLogin.putString("Email", null)
            editSavedLogin.putString("Password", null)
            editSavedLogin.putString("Status", "Off")
            editSavedLogin.commit()
            startActivity(Intent(this, login::class.java))
        }

        btnisda.setOnClickListener {
            startActivity(Intent(this, isidaftar::class.java))
        }
        btn_produk.setOnClickListener{
            val intent = Intent(this, ProdukMain::class.java)
            startActivity(intent)
        }
    }
}