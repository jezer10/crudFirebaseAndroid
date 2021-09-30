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



        firebaseHelper.listCategory().addOnSuccessListener { categoryQS->
            var categoryList: ArrayList<CategoriaModel> = arrayListOf()
            var productList: ArrayList<ProductModel> = arrayListOf()

            categoryQS.documents.forEach { categoryDocuments->
                categoryList.add(
                    CategoriaModel(
                        categoryDocuments.id,
                        categoryDocuments.data?.get("name") as String
                    )
                )

            }


            firebaseHelper.listProduct().addOnSuccessListener {productQS->
                productQS.documents.forEach { productDocuments->
                    productList.add(
                        ProductModel(
                            productDocuments.id,
                            productDocuments.data?.get("name") as String,
                            (productDocuments.data?.get("price") as Double),
                            (productDocuments.data?.get("stock") as Long).toInt(),
                            productDocuments.data?.get("idcategory") as String,
                            categoryList.filter {
                                it.id == productDocuments.data?.get("idcategory")
                            }[0].name
                        )
                    )
                }
                productList.forEach {
                    Log.w(ContentValues.TAG, "${it.idproducto}")
                }
                recycler.adapter = ProductAdapter(
                    productList
                )

            }

        }


    }
}
