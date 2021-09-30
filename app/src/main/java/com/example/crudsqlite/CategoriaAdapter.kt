package com.example.crudsqlite

import android.view.LayoutInflater
import android.view.View;
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot


class CategoriaAdapter : RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder>() {

    private var catList: MutableList<CategoriaModel> = mutableListOf()
    private var onClickItem: ((CategoriaModel) -> Unit)? = null
    private var onClickDeleteItem: ((CategoriaModel) -> Unit)? = null

    fun addItems(items: MutableList<CategoriaModel>) {
        this.catList = items;
        notifyDataSetChanged()
    }

    fun setOnClickItem(callback: (CategoriaModel) -> Unit) {
        this.onClickItem = callback
    }

    fun setOnclickDeleteItem(callback: (CategoriaModel) -> Unit) {
        this.onClickDeleteItem = callback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoriaViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.activity_category_item, parent, false)
    )

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {

        val cat = catList[position]
        holder.bindView(cat);
        holder.itemView.setOnClickListener { onClickItem?.invoke(cat) }
        holder.btnDelete.setOnClickListener { onClickDeleteItem?.invoke(cat) }
    }


    override fun getItemCount(): Int = catList.size


    class CategoriaViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private var name = view.findViewById<TextView>(R.id.name);
        var btnDelete = view.findViewById<Button>(R.id.btnDelete)


        fun bindView(cat: CategoriaModel) {
            name.text = cat.name;
        }


    }
}