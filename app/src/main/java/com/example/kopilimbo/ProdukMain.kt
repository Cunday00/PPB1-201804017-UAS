package com.example.kopilimbo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class ProdukMain : AppCompatActivity() {
    lateinit var produkDBHelper: DBHelper
    lateinit var inputid: EditText
    lateinit var inputname: EditText
    lateinit var inputgrind: EditText
    lateinit var inputsize: EditText
    lateinit var inputprice: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.produk_main)
        inputid = findViewById(R.id.input_id)
        inputname = findViewById(R.id.input_name)
        inputgrind = findViewById(R.id.input_grind)
        inputsize = findViewById(R.id.input_size)
        inputprice = findViewById(R.id.input_price)
        produkDBHelper = DBHelper(this)
    }

    fun addData(v: View) {
        var idin = inputid.text.toString()
        var namein = inputname.text.toString()
        var grindin = inputgrind.text.toString()
        var sizein = inputsize.text.toString()
        var pricein = inputprice.text.toString()
        produkDBHelper.InsertProduk(idin, namein, grindin, sizein, pricein)
        inputid.setText("")
        inputname.setText("")
        inputgrind.setText("")
        inputsize.setText("")
        inputprice.setText("")

    }

    fun showAll(v: View) {
        var pindah = Intent(this, RvDataProduk::class.java)
        startActivity(pindah)
    }
}