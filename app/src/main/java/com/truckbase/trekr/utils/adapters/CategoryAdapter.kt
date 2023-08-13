package com.truckbase.trekr.utils.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.truckbase.trekr.R
import com.truckbase.trekr.domain.model.CategoryResponse

class CategoryAdapter(var categoryList: List<CategoryResponse.CategoryResponseItem>, private var onClick : (Int)-> Unit) :
    RecyclerView.Adapter<CategoryAdapter.CategoryNamesViewHolder>() {
    inner class CategoryNamesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var category1 = itemView.findViewById<MaterialButton>(R.id.category)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryNamesViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_namesvh, parent, false)
        return CategoryNamesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryNamesViewHolder, position: Int) {
        val categories = categoryList[position]
        val categoryName = categories.name
        holder.category1.text = categoryName
        holder.category1.setOnClickListener {
            val categoryId = categories.id
            categoryId.let {
                if (it != null) {
                    onClick(it)
                }
            }
        }
        
    }
}