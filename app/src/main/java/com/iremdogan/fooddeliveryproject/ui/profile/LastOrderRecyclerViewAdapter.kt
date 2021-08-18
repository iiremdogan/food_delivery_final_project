package com.iremdogan.fooddeliveryproject.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iremdogan.fooddeliveryproject.R

class LastOrderRecyclerViewAdapter: RecyclerView.Adapter<LastOrderRecyclerViewAdapter.ViewHolder>() {
    private lateinit var itemList: MutableList<OrderModel>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = itemList.size

    fun setData(itemList: MutableList<OrderModel>?) {
        itemList?.let {
            this.itemList = itemList
            notifyDataSetChanged()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val cartItemImageView: AppCompatImageView = view.findViewById(R.id.meal_image_view)
        private val cartItemRestaurantName: TextView = view.findViewById(R.id.restaurant_name_text_view)
        private val cartItemMealName: TextView = view.findViewById(R.id.meal_name_text_view)
        private val cartItemMealPrice: TextView = view.findViewById(R.id.meal_price_text_view)

        fun bind(itemModel: OrderModel) {
            cartItemRestaurantName.text = itemModel.restaurantName
            cartItemMealName.text = itemModel.mealName
            cartItemMealPrice.text = itemModel.price

            Glide.with(cartItemImageView.context)
                .load(R.drawable.ic_heart_filled).into(cartItemImageView)
        }
    }

}