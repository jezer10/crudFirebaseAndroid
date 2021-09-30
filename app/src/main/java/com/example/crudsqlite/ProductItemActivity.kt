package com.example.crudsqlite

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProductItemActivity : AppCompatActivity() {
    lateinit var deleteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_item)
        Log.w(ContentValues.TAG,"INgeniero quevin")

        deleteButton = findViewById(R.id.btn_product_item_delete)
        deleteButton.setOnClickListener {

        }



    }
}