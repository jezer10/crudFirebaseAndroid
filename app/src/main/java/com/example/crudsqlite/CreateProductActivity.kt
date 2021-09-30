package com.example.crudsqlite

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.DocumentSnapshot

class CreateProductActivity : AppCompatActivity() {

    lateinit var nameText: TextInputEditText
    lateinit var priceText: TextInputEditText
    lateinit var stockText: TextInputEditText
    lateinit var addButton: Button
    lateinit var listButton: Button
    lateinit var categoryDropdown: AutoCompleteTextView
    private var idcategoria: String = ""
    private var idproducto:String =""

    var firebaseHelper:FirebaseHelper= FirebaseHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_product)


        nameText = findViewById(R.id.tiet_create_product_name)
        priceText = findViewById(R.id.tiet_create_product_price)
        stockText = findViewById(R.id.tiet_create_product_stock)
        addButton = findViewById(R.id.btn_create_product_add)
        listButton = findViewById(R.id.btn_create_product_list)
        categoryDropdown = findViewById(R.id.actv_create_product_category)


        firebaseHelper.listCategory().addOnSuccessListener {
            val items: MutableList<CategoriaModel> = mutableListOf()
                it.documents.forEach {
                    items.add(CategoriaModel(
                        it.id as String,
                        it.data?.get("name") as String
                    ))
                }
            val adapter = ArrayAdapter(applicationContext, R.layout.dropdown_product_item, items)
            categoryDropdown.setAdapter(adapter)

        }



        intent.extras?.let {

            nameText.setText(it.getString("nomprod"))
            priceText.setText(it.getDouble("precio").toString())
            stockText.setText(it.getInt("stock").toString())

            if (it.getString("idproducto") != "") {
                idproducto = it.getString("idproducto").toString()
                addButton.text = "Editar Producto"
            }

        }


        addButton.setOnClickListener {
            validateForm(
                idproducto,
                nameText.text.toString(),
                priceText.text.toString(),
                stockText.text.toString(),
                idcategoria
            )

        }

        listButton.setOnClickListener {
            startActivity(Intent(applicationContext, ListProductActivity::class.java))
        }
        categoryDropdown.setOnItemClickListener { parent, view, position, id ->
            val item: CategoriaModel = categoryDropdown.adapter.getItem(position) as CategoriaModel
            idcategoria = item.id
        }


    }

    fun cleanForm() {
        nameText.setText("")
        priceText.setText("")
        stockText.setText("")
    }

    fun validateForm(idproducto:String,name: String, price: String, stock: String, idcategoria: String) {
        if (name.isBlank() && price.isBlank() && stock.isBlank() && idcategoria.isBlank()) {
            Log.i(ContentValues.TAG, "${categoryDropdown.editableText}")
            Toast.makeText(applicationContext, "Empty fields", Toast.LENGTH_SHORT).show()
            return
        }

        if(idproducto==""){
            var successCode =
                firebaseHelper.createProduct(
                    ProductModel(
                        idproducto,
                        name,
                        price.toDouble(),
                        stock.toInt(),
                        idcategoria
                    )
                )
            Log.i(ContentValues.TAG, "$successCode")
            if (false) {
                Toast.makeText(applicationContext, "Sucessfully Inserted Row", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(applicationContext, "que asiendi", Toast.LENGTH_SHORT).show()
            }
        }else{
            var successCode =
                firebaseHelper.updateProduct(
                    ProductModel(
                        idproducto,
                        name,
                        price.toDouble(),
                        stock.toInt(),
                        idcategoria
                    )
                )
            Log.i(ContentValues.TAG, "$successCode")
            if (false) {
                Toast.makeText(applicationContext, "Sucessfully Updated Row $successCode", Toast.LENGTH_SHORT)
                    .show()
                startActivity(Intent(applicationContext,ListProductActivity::class.java))
            } else {
                Toast.makeText(applicationContext, "que asiendi", Toast.LENGTH_SHORT).show()
            }
        }

    }
}