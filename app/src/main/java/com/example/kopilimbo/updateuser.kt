package com.example.kopilimbo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.content.Intent

class updateuser : AppCompatActivity() {
    lateinit var userDBHelper: DBHelper
    lateinit var inputemail: EditText
    lateinit var inputpass: EditText
    lateinit var inputfullname: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updateuser)
        inputemail = findViewById(R.id.userrr)
        inputpass = findViewById(R.id.passs)
        inputfullname = findViewById(R.id.namaaa)
        userDBHelper = DBHelper(this)
        val bundle = intent.extras
        if (bundle!=null){
            inputemail.setText(bundle.getString("emailk"))
            inputpass.setText(bundle.getString("passk"))
            inputfullname.setText(bundle.getString("fullnamek"))

        }
    }
    fun updateData (v: View) {
        var emailin = inputemail.text.toString()
        var passin = inputpass.text.toString()
        var fullnamein = inputfullname.text.toString()
        userDBHelper.updatedata(emailin, passin, fullnamein)
        var pindah = Intent(this, isidaftar::class.java)
        startActivity(pindah)
    }
    fun cancleData (v: View){
        var pindah = Intent(this, isidaftar::class.java)
        startActivity(pindah)
    }
}
