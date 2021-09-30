package com.example.crudsqlite

 class CategoriaModel{
    lateinit var id : String
    lateinit var name : String

    constructor() {

    }

    constructor(
        name:String

    ){
        this.name=name

    }

    constructor(
        id: String,
        name:String

    ){
        this.id=id
        this.name=name

    }
    override fun toString(): String {
        return name
    }
};