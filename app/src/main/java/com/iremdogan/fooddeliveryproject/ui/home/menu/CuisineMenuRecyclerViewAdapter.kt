package com.iremdogan.fooddeliveryproject.ui.home.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.iremdogan.fooddeliveryproject.R

class CuisineMenuRecyclerViewAdapter : RecyclerView.Adapter<CuisineMenuRecyclerViewAdapter.ViewHolder>(){

    private lateinit var itemList: List<String>
    private var listener: ICuisineMenuItemOnClick? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cuisine_menu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int = itemList.size

    fun setData(itemList: List<String>?) {
        itemList?.let {
            this.itemList = itemList
            notifyDataSetChanged()
        }
    }

    fun addListener(listener: ICuisineMenuItemOnClick?) {
        this.listener = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val cuisineMenuLayout : ConstraintLayout = view.findViewById(R.id.cuisine_menu_layout)
        private val cuisineMenuItemName: TextView = view.findViewById(R.id.cuisine_menu_item_text_view)

        fun bind(itemModel: String, listener: ICuisineMenuItemOnClick?) {
            cuisineMenuItemName.text = itemModel

            cuisineMenuLayout.setOnClickListener {
                listener?.onClick(itemModel)
            }
        }
    }
}