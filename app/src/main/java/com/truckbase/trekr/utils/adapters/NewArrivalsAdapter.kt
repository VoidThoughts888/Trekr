package com.truckbase.trekr.utils.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.truckbase.trekr.R
import com.truckbase.trekr.data.adapterdata.NewArrivalsData

class NewArrivalsAdapter(val arrivalList: List<NewArrivalsData>) : RecyclerView.Adapter<NewArrivalsAdapter.NewArrivalsVH>() {

    inner class NewArrivalsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemName = itemView.findViewById<TextView>(R.id.itemName)
        var itemImage = itemView.findViewById<ImageView>(R.id.itemImage)
        var itemType = itemView.findViewById<TextView>(R.id.itemCategory)
        var itemPrice = itemView.findViewById<TextView>(R.id.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewArrivalsVH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.new_arrivals_vh, parent, false)

        return NewArrivalsVH(view)
    }

    override fun getItemCount(): Int {
        return arrivalList.size
    }

    override fun onBindViewHolder(holder: NewArrivalsVH, position: Int) {
        holder.itemName.text = arrivalList[position].itemName
        holder.itemImage.setImageResource(arrivalList[position].itemImage)
        holder.itemPrice.text = arrivalList[position].itemPrice.toString()
        holder.itemType.text = arrivalList[position].itemCategory
    }
}