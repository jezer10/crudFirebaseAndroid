package com.example.crudsqlite

class ProductModel {
    var idproducto: String
    var nomprod: String
    var precio: Double
    var stock: Int
    var idcategoria: String
    lateinit var nomcat: String

    constructor(
        idproducto: String,
        nomprod: String,
        precio: Double,
        stock: Int,
        idcategoria: String
    ) {
        this.idproducto = idproducto
        this.nomprod = nomprod
        this.precio = precio
        this.stock = stock
        this.idcategoria = idcategoria

    }

    constructor(
        idproducto: String,
        nomprod: String,
        precio: Double,
        stock: Int,
        idcategoria: String,
        nomcat: String
    ) {
        this.idproducto = idproducto
        this.nomprod = nomprod
        this.precio = precio
        this.stock = stock
        this.idcategoria = idcategoria
        this.nomcat = nomcat
    }


}