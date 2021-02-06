package com.example.kopilimbo

import android.provider.BaseColumns

object DBInfo {
    class UserTable: BaseColumns {
        companion object {
            val TABLE_NAMEU = "user"
            val COL_EMAIL = "email"
            val COL_PASS = "pass"
            val COL_FULLNAME = "fullname"
            val COL_JUMLAH = "jumlah"
        }
    }

    class ProdukTable: BaseColumns {
        companion object {
            val TABLE_NAME = "produk"
            val COL_ID = "id"
            val COL_NAME = "name"
            val COL_GRIND = "grind"
            val COL_SIZE = "size"
            val COL_PRICE = "price"
        }
    }
}