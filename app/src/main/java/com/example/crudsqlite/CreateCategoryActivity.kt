package com.example.crudsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CreateCategoryActivity : AppCompatActivity() {
    private lateinit var btnAdd: Button
    private lateinit var btnView: Button
    private lateinit var btnUpdate : Button
    private lateinit var edName: EditText
    private lateinit var recyclerView: RecyclerView
    private var adapter: CategoriaAdapter? = null;
    private var cat:CategoriaModel? = null
    var firebaseHelper:FirebaseHelper= FirebaseHelper()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)
        initRecyclerView();

        btnUpdate= findViewById(R.id.btnUpdate)!!
        edName = findViewById(R.id.til_create_category_name)
        btnAdd = findViewById(R.id.btn_button)
        btnView = findViewById(R.id.btn_button_view)


        btnAdd.setOnClickListener { addCategoria() }
        btnView.setOnClickListener { getCategoria() }
        btnUpdate.setOnClickListener {updateCategoria()}
        adapter?.setOnClickItem {
            Toast.makeText(this, it.name , Toast.LENGTH_SHORT).show()
            edName.setText(it.name);
            cat = it;

        }
        adapter?.setOnclickDeleteItem {
            deleteCategoria(it.id)
        }


    }

    private fun getCategoria() {
        firebaseHelper.listCategory().addOnSuccessListener {
            val catList: MutableList<CategoriaModel> = mutableListOf()

                it.documents.forEach{
                    catList.add(CategoriaModel(it?.id as String,
                        it.data?.get("name") as String
                        )
                    )
            }
            Log.e("ppp", "${catList.size}")

            adapter?.addItems(catList)
        };



    }
    private fun updateCategoria(){
        val name = edName.text.toString();
        if(name === cat?.name){
            Toast.makeText(this, "Registro no actualizado", Toast.LENGTH_LONG).show();
            return
        }
        if(cat == null) return
            val cat = CategoriaModel( cat!!.id,  name)
            val status = firebaseHelper.updateCategory(cat)
        if(false){
            clearEditText();
            getCategoria()

        }else{
            Toast.makeText(this, "Actualizado Correctamente", Toast.LENGTH_LONG).show();
        }


    }

    private fun addCategoria() {
        val name = edName.text.toString();
        if (name.isBlank()) {
            Toast.makeText(this, "Coloque datos por favor", Toast.LENGTH_LONG).show();

        } else {
            val cat = CategoriaModel(name)
            val status = firebaseHelper.createCategory(cat);
            if (false) {

                Toast.makeText(this, "Categoria añadida", Toast.LENGTH_SHORT).show()
                clearEditText()
            } else {
                Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show()
            }
        }


    }
    private fun deleteCategoria(id: String){
        if(id == null) return
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Estás seguro de eliminar este elemento?")
        builder.setCancelable(true);
        builder.setPositiveButton("si" ){ dialog, _->
            firebaseHelper.delCategory(id);
            getCategoria()
            dialog.dismiss()
        }
        builder.setNegativeButton("no"){ dialog, _->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
        }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.layoutManager = LinearLayoutManager(this);
        recyclerView.adapter = adapter

    }


    private fun clearEditText() {
        edName.setText("")
    }


}