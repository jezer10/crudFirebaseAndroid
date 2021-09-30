package com.example.crudsqlite

import android.content.ContentValues
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.ktx.Firebase

class ProductAdapter(var scheduleList: ArrayList<ProductModel>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    var firebaseHelper:FirebaseHelper = FirebaseHelper()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_product_item, parent, false)




        return ProductViewHolder(view)

    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val productElement= scheduleList[position]
        holder.assignData(productElement )

        holder.deleteButton.setOnClickListener {
            Log.w(ContentValues.TAG, "INgeniero quevin")
            MaterialAlertDialogBuilder(holder.deleteButton.context)
                .setMessage(holder.deleteButton.context.resources.getString(R.string.delete_dialog))
                .setNegativeButton(holder.deleteButton.context.resources.getString(R.string.decline)) { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton(holder.deleteButton.context.resources.getString(R.string.accept)) { dialog, which ->
                    firebaseHelper.delProduct(productElement.idproducto)
                    deleteItem(position)
                    Toast.makeText(
                        holder.deleteButton.context,
                        "Successfully deleted element",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .show()
        }
    }
    fun deleteItem(position: Int){
        scheduleList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position,itemCount)
    }

    override fun getItemCount(): Int = scheduleList.size

    class ProductViewHolder : RecyclerView.ViewHolder, View.OnClickListener {
        var name: TextView
        var price: TextView
        var stock: TextView
        var nomcat: TextView
        var deleteButton: Button
        var editButton: Button



        constructor(view: View) : super(view) {
            this.name = view.findViewById(R.id.tv_product_item_name)
            this.price = view.findViewById(R.id.tv_product_item_price)
            this.stock = view.findViewById(R.id.tv_product_item_stock)
            this.nomcat = view.findViewById(R.id.tv_product_item_category_name)
            this.deleteButton = view.findViewById(R.id.btn_product_item_delete)
            this.editButton = view.findViewById(R.id.btn_product_item_edit)

        }

        fun assignData(productModel: ProductModel) {
            this.name.text = productModel.nomprod
            this.price.text = productModel.precio.toString()
            this.stock.text = productModel.stock.toString()
            this.nomcat.text = productModel.nomcat


            editButton.setOnClickListener {

                val intent = Intent(editButton.context, CreateProductActivity::class.java)
                intent.apply {
                    putExtra("idproducto",productModel.idproducto)
                    putExtra("nomprod", productModel.nomprod)
                    putExtra("precio", productModel.precio)
                    putExtra("stock", productModel.stock)

                }
                editButton.context.startActivity(intent)
            }

        }

        override fun onClick(v: View?) {
            val position = adapterPosition

        }

    }
}