package com.example.kopilimbo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class register : AppCompatActivity() {
    lateinit var eemail: EditText
    lateinit var epassword: EditText
    lateinit var efullname: EditText
    lateinit var btnregister: Button
    lateinit var btncancel: Button
    lateinit var userDBHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        eemail = findViewById(R.id.mail)
        epassword = findViewById(R.id.pass)
        efullname = findViewById(R.id.nama)
        btnregister = findViewById(R.id.sub)
        btncancel = findViewById(R.id.canc)
        userDBHelper = DBHelper(this)

    }

    fun registerme(view: View) {
        var iemail = eemail.text.toString()
        var ipassword = epassword.text.toString()
        var ifullname = efullname.text.toString()
        var cekuser = userDBHelper.cekUser(iemail)
        var status = "Gagal"
        if (cekuser == "0") {
            userDBHelper.RegisterUser(iemail, ipassword, ifullname)
            status = "Sukses"
            val intent = Intent(this, login::class.java)
            startActivity(intent)

        }
        val toast: Toast = Toast.makeText(
            applicationContext,
            status, Toast.LENGTH_SHORT
        )
        toast.show()
    }

    fun cancelme(view: View) {
        startActivity(Intent(this, login::class.java))
    }
}
