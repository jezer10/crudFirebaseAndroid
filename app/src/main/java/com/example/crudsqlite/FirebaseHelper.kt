package com.example.crudsqlite

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseHelper {
    private val firestore = Firebase.firestore

    private val categoriesRef = firestore.collection("categories")
    private val productsRef = firestore.collection("products")

    fun createProduct(product: ProductModel): Task<DocumentReference> {
        val newProduct = hashMapOf(
            "name" to product.nomprod,
            "price" to product.precio,
            "stock" to product.stock,
            "idcategory" to product.idcategoria
        )
        return productsRef.add(newProduct)
    }

    fun listProduct(): Task<QuerySnapshot> {
        return productsRef.get()
    }

    fun updateProduct(product: ProductModel): Task<Void> {
        val newProduct: Map<String, *> = hashMapOf(
            "name" to product.nomprod,
            "price" to product.precio,
            "stock" to product.stock,
            "idcategory" to product.idcategoria
        )
        return productsRef.document(product.idproducto.toString()).update(newProduct)
    }

    fun delProduct(idproducto: String): Task<Void> {
        return productsRef.document(idproducto).delete()
    }

    fun findProductById(idproduct: String): Task<DocumentSnapshot> {
        return productsRef.document(idproduct).get()
    }


    fun createCategory(category: CategoriaModel): Task<DocumentReference> {
        val newCategory = hashMapOf(
            "nombre" to category.name
        )
        return categoriesRef.add(newCategory)
    }

    fun listCategory(): Task<QuerySnapshot> {
        return categoriesRef.get()
    }

    fun updateCategory(category: CategoriaModel): Task<Void> {
        val newCategory: Map<String, *> = hashMapOf(
            "nombre" to category.name
        )
        return categoriesRef.document(category.id).update(newCategory)
    }

    fun delCategory(idcategoria: String): Task<Void> {
        return categoriesRef.document(idcategoria).delete()
    }

    fun findCategoryById(idcategoria: String): Task<DocumentSnapshot> {
        return categoriesRef.document(idcategoria).get()
    }
}