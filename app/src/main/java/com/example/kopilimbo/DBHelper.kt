package com.example.kopilimbo

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        val DATABASE_NAME = "myaps.db"
        val DATABASE_VERSION = 1
        private val SQL_CREATE_USER =
            "CREATE TABLE " + DBInfo.UserTable.TABLE_NAMEU + "(" + DBInfo.UserTable.COL_EMAIL + " VARCHAR(200) PRIMARY KEY, " + DBInfo.UserTable.COL_PASS + " TEXT, " + DBInfo.UserTable.COL_FULLNAME + " TEXT)"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBInfo.UserTable.TABLE_NAMEU
        private val SQL_CREATE_PRODUK = "CREATE TABLE " + DBInfo.ProdukTable.TABLE_NAME + "("+DBInfo.ProdukTable.COL_ID+" VARCHAR(200) PRIMARY KEY, " + DBInfo.ProdukTable.COL_NAME +" TEXT, " + DBInfo.ProdukTable.COL_GRIND +" TEXT, " + DBInfo.ProdukTable.COL_SIZE + " TEXT, " + DBInfo.ProdukTable.COL_PRICE + " TEXT)"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_USER)
        db?.execSQL(SQL_CREATE_PRODUK)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun RegisterUser(emailin: String, passin: String, fullnamein: String) {
        val db = writableDatabase
        val namatable = DBInfo.UserTable.TABLE_NAMEU
        val emailt = DBInfo.UserTable.COL_EMAIL
        val passt = DBInfo.UserTable.COL_PASS
        val fullnamet = DBInfo.UserTable.COL_FULLNAME
        var sql =
            "INSERT INTO " + namatable + " (" + emailt + ", " + passt + ", " + fullnamet + ") VALUES('" + emailin + "', '" + passin + "', '" + fullnamein + "')"
        db.execSQL(sql)
    }

    fun cekUser(emailin: String): String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""
        try {
            cursor = db.rawQuery("SELECT COUNT("+ DBInfo.UserTable.COL_EMAIL +") as jumlah FROM "+ DBInfo.UserTable.TABLE_NAMEU + " WHERE " + DBInfo.UserTable.COL_EMAIL + "=='" + emailin +"'" , null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return "Tabel Dibuat"
        }
        if (cursor!!.moveToFirst()){
            jumlah = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_JUMLAH))
        }
        return jumlah
    }

    fun cekLogin(emailin: String, passin: String): String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""
        try {
            cursor = db.rawQuery(
                "SELECT COUNT(" + DBInfo.UserTable.COL_EMAIL + ") as jumlah FROM " + DBInfo.UserTable.TABLE_NAMEU + " WHERE " + DBInfo.UserTable.COL_EMAIL + "=='" + emailin + "' AND " + DBInfo.UserTable.COL_PASS + "=='" + passin + "'",
                null
            )
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return "Tabel Dibuat"
        }
        if (cursor!!.moveToFirst()) {
            jumlah = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_JUMLAH))
        }
        return jumlah
    }

    fun fullDataUser(): ArrayList<DataModelUser> {
        val users = arrayListOf<DataModelUser>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM " + DBInfo.UserTable.TABLE_NAMEU, null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return ArrayList()
        }
        var emailt: String
        var passt: String
        var fullnamet: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                emailt = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_EMAIL))
                passt = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_PASS))
                fullnamet = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_FULLNAME))
                users.add(DataModelUser(emailt, passt, fullnamet))
                cursor.moveToNext()
            }
        }
        return users
    }

    fun deleteData(emailin: String){
        val db = writableDatabase
        val namatablet = DBInfo.UserTable.TABLE_NAMEU
        val emailt = DBInfo.UserTable.COL_EMAIL
        val sql = "DELETE FROM " +namatablet+ " WHERE "+emailt+"='"+emailin+"'"
        db.execSQL(sql)
    }

    fun updatedata(emailin: String, passin: String, fullnamein: String){
        val db = writableDatabase
        val nametablet = DBInfo.UserTable.TABLE_NAMEU
        val emailt = DBInfo.UserTable.COL_EMAIL
        val passt = DBInfo.UserTable.COL_PASS
        val fullnamet = DBInfo.UserTable.COL_FULLNAME
        var sql = "UPDATE "+ nametablet + " SET "+fullnamet+"='"+fullnamein+"', "+passt+"='"+passin+"'"+" WHERE "+emailt+"='"+emailin+"'"
        db.execSQL(sql)
    }

    fun InsertProduk(idin: String, namein:String, grindin: String, sizein:String, pricein: String) {
        val db = writableDatabase
        val namatable = DBInfo.ProdukTable.TABLE_NAME
        val idt = DBInfo.ProdukTable.COL_ID
        val namet = DBInfo.ProdukTable.COL_NAME
        val grindt = DBInfo.ProdukTable.COL_GRIND
        val sizet = DBInfo.ProdukTable.COL_SIZE
        val pricet = DBInfo.ProdukTable.COL_PRICE
        var sql = "INSERT INTO " + namatable + " (" + idt + ", " + namet + ", " + grindt + ", " + sizet + ", " + pricet + ") VALUES ('" + idin + "', '" + namein + "', '" + grindin + "', '" + sizein + "', '" + pricein + "')"
        db.execSQL(sql)
    }
    fun fullDataProduk():ArrayList<DataModelProduk> {
        val cakes = arrayListOf<DataModelProduk>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM "+DBInfo.ProdukTable.TABLE_NAME, null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_PRODUK)
            return ArrayList()
        }
        var idt: String
        var namet: String
        var grindt: String
        var sizet: String
        var pricet: String
        if (cursor!!.moveToFirst()){
            while (cursor.isAfterLast==false){
                idt = cursor.getString(cursor.getColumnIndex(DBInfo.ProdukTable.COL_ID))
                namet = cursor.getString(cursor.getColumnIndex(DBInfo.ProdukTable.COL_NAME))
                grindt = cursor.getString(cursor.getColumnIndex(DBInfo.ProdukTable.COL_GRIND))
                sizet = cursor.getString(cursor.getColumnIndex(DBInfo.ProdukTable.COL_SIZE))
                pricet = cursor.getString(cursor.getColumnIndex(DBInfo.ProdukTable.COL_PRICE))

                cakes.add(DataModelProduk(idt, namet, grindt, sizet, pricet))
                cursor.moveToNext()
            }
        }
        return  cakes
    }
    fun deleteProduk(idin: String){
        val db = writableDatabase
        val namatablet = DBInfo.ProdukTable.TABLE_NAME
        val idt = DBInfo.ProdukTable.COL_ID
        val sql = "DELETE FROM " +namatablet+ " WHERE "+idt+"='"+idin+"'"
        db.execSQL(sql)
    }
    fun updateProduk(idin: String, namein:String, grindin: String, sizein: String, pricein: String){
        val db = writableDatabase
        val namatablet = DBInfo.ProdukTable.TABLE_NAME
        val idt = DBInfo.ProdukTable.COL_ID
        val namet = DBInfo.ProdukTable.COL_NAME
        val grindt = DBInfo.ProdukTable.COL_GRIND
        val sizet = DBInfo.ProdukTable.COL_SIZE
        val pricet = DBInfo.ProdukTable.COL_PRICE
        var sql = "UPDATE "+ namatablet + " SET "+
                namet+"='"+namein+"', "+grindt+"='"+grindin+"', "+sizet+"='"+sizein+"', "+pricet+"='"+pricein+"' "+
                "WHERE "+idt+"='"+idin+"'"
        db.execSQL(sql)
    }
}