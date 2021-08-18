package com.iremdogan.fooddeliveryproject.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iremdogan.fooddeliveryproject.R

class CartRecyclerViewAdapter : RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder>() {
    private lateinit var itemList: List<CartItem>
    private var listener: ICartItemOnClick? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int = itemList.size

    fun setData(itemList: List<CartItem>?) {
        itemList?.let {
            this.itemList = itemList
            notifyDataSetChanged()
        }
    }

    fun addListener(listener: ICartItemOnClick?) {
        this.listener = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val cartItemImageView: AppCompatImageView = view.findViewById(R.id.meal_image_view)
        private val cartItemRestaurantName: TextView = view.findViewById(R.id.restaurant_name_text_view)
        private val cartItemMealName: TextView = view.findViewById(R.id.meal_name_text_view)
        private val cartItemMealPrice: TextView = view.findViewById(R.id.meal_price_text_view)
        private val cartItemMealCount: TextView = view.findViewById(R.id.item_count_text_view)
        private val increaseButton: AppCompatButton = view.findViewById(R.id.increase_count_button)
        private val decreaseButton: AppCompatButton = view.findViewById(R.id.decrease_count_button)

        fun bind(itemModel: CartItem, listener: ICartItemOnClick?) {
            cartItemRestaurantName.text = itemModel.restaurantName
            cartItemMealName.text = itemModel.mealName
            cartItemMealPrice.text = itemModel.mealPrice

            //TODO : BURALARDAN DEVAMKE

            Glide.with(cartItemImageView.context)
                .load(R.drawable.ic_heart_filled).into(cartItemImageView)

            increaseButton.setOnClickListener {
                listener?.onIncreaseButtonClick(cartItem = itemModel)
            }

            decreaseButton.setOnClickListener {
                listener?.onDecreaseButtonClick(cartItem = itemModel)
            }

        }
    }
}