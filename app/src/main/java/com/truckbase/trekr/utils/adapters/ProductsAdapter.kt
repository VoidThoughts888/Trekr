package com.truckbase.trekr.utils.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.truckbase.trekr.R
import com.truckbase.trekr.domain.model.ProductsResponse

class ProductsAdapter(var productsList: List<ProductsResponse>?, var onClick: (Int)->Unit) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>(){

    class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var productImage = itemView.findViewById<ImageView>(R.id.product_image)
        var productName = itemView.findViewById<TextView>(R.id.product_name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.products_vh,parent,false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        var pList = productsList?.get(position)?.get(position)?.category

        Glide.with(holder.productImage)
            .load(pList?.image)
            .into(holder.productImage)
    }
}