package com.example.crudsqlite

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    lateinit var addProductButton:Button
    lateinit var addCategoryButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addProductButton= findViewById(R.id.btn_main_add_product)
        addCategoryButton= findViewById(R.id.btn_main_add_category)

        addProductButton.setOnClickListener {
            startActivity(Intent(applicationContext,CreateProductActivity::class.java))
        }
        addCategoryButton.setOnClickListener {
            startActivity(Intent(applicationContext,CreateCategoryActivity::class.java))
        }




    }




}