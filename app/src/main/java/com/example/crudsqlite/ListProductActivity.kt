package com.example.crudsqlite

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListProductActivity : AppCompatActivity() {
    lateinit var recycler: RecyclerView
    var firebaseHelper: FirebaseHelper = FirebaseHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_product)
        recycler = findViewById(R.id.rv_list_product)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        firebaseHelper.listProduct().addOnSuccessListener {
            var productList: ArrayList<ProductModel> = arrayListOf()
            it.documents.forEach {
                productList.add(
                    ProductModel(
                        "",
                        it.data?.get("name") as String,
                        it.data?.get("price") as Double,
                        it.data?.get("stock") as Int,
                        it.data?.get("idcategory") as String
                    )
                )
            }
            recycler.adapter = ProductAdapter(
                productList
            )
        }


    }
}
