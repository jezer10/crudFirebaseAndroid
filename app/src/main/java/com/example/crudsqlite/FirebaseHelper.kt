package com.example.crudsqlite

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseHelper {
    val fireStore = Firebase.firestore

    fun createProduct(product:ProductModel){
        val newProduct = hashMapOf(
            "nombre" to product.nomprod,
            "precio" to product.precio,
            "stock" to product.stock,
            "idcategoria" to product.idcategoria
        )
        fireStore.collection("products").add(newProduct)
    }
    fun createCategory(category:CategoriaModel){
        val newCategory = hashMapOf(
            "nombre" to category.name
        )
        fireStore.collection("category").add(newCategory)
    }


    fun listProduct(): Task<QuerySnapshot> {
        return fireStore.collection("products").get()
    }
    fun listCategory(): Task<QuerySnapshot> {
        return fireStore.collection("category").get()
    }


    fun updateProduct(product:ProductModel){
        val newProduct:Map<String,*> = hashMapOf(
            "nombre" to product.nomprod,
            "precio" to product.precio,
            "stock" to product.stock,
            "idcategoria" to product.idcategoria
        )
        fireStore.collection("products").document(product.idproducto.toString()).update(newProduct)
    }
    fun updateCategory(category: CategoriaModel){
        val newCategory:Map<String,*> = hashMapOf(
            "nombre" to category.name
        )
        fireStore.collection("category").document(category.id.toString()).update(newCategory)
    }


    fun delProduct(idproducto: String){
        fireStore.collection("products").document(idproducto).delete()
    }
    fun delCategory(idcategoria: String){
        fireStore.collection("category").document(idcategoria).delete()
    }
}