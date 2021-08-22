package com.iremdogan.fooddeliveryproject.ui.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iremdogan.fooddeliveryproject.R
import com.iremdogan.fooddeliveryproject.model.entity.order.OrderData

class LastOrderRecyclerViewAdapter: RecyclerView.Adapter<LastOrderRecyclerViewAdapter.ViewHolder>() {
    private lateinit var itemList: MutableList<OrderData>

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

    fun setData(itemList: MutableList<OrderData>?) {
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

        fun bind(order: OrderData) {
            cartItemRestaurantName.text = order.restaurantData.name
            cartItemMealName.text = order.mealInfoList[0].name
            cartItemMealPrice.text = order.mealInfoList[0].price.toString()

            Glide.with(cartItemImageView.context)
                .load(R.drawable.ic_heart_filled).into(cartItemImageView)

//            Glide.with(cartItemImageView.context)
//                .load(order.mealInfoList[0].imageUrl).into(cartItemImageView)
        }
    }

}