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
import com.iremdogan.fooddeliveryproject.model.entity.cart.MealInfo
import com.iremdogan.fooddeliveryproject.model.entity.meal.MealData

class CartRecyclerViewAdapter : RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder>() {
    private var itemList: MutableList<MealInfo> = mutableListOf()
    private var listener: ICartOnClick ?= null

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

    fun setData(itemList: MutableList<MealInfo>?) {
        itemList?.let {
            this.itemList = itemList
            notifyDataSetChanged()
        }
    }

    fun addListener(listener: ICartOnClick){
        this.listener = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val cartItemImageView: AppCompatImageView = view.findViewById(R.id.meal_image_view)
        private val cartItemMealName: TextView = view.findViewById(R.id.meal_name_text_view)
        private val cartItemMealPrice: TextView = view.findViewById(R.id.meal_price_text_view)
        private val cartItemMealCount: TextView = view.findViewById(R.id.item_count_text_view)
        private val increaseButton: AppCompatButton = view.findViewById(R.id.increase_count_button)
        private val decreaseButton: AppCompatButton = view.findViewById(R.id.decrease_count_button)

        fun bind(meal: MealInfo, listener: ICartOnClick?) {
            cartItemMealName.text = meal.mealInfo.name
            cartItemMealPrice.text = meal.mealInfo.price.toString()
            cartItemMealCount.text = meal.count.toString()

            Glide.with(cartItemImageView.context)
                .load(meal.mealInfo.imageUrl).into(cartItemImageView)

            increaseButton.setOnClickListener {
                meal.count++
                cartItemMealCount.text = meal.count.toString()
                listener?.onClickIncreaseButton(meal)
            }

            decreaseButton.setOnClickListener {
                if((meal.count - 1) != 0 ){
                    meal.count--
                    cartItemMealCount.text = meal.count.toString()
                    listener?.onClickDecreaseButton(meal)
                }
            }

        }
    }

    fun removeAt(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }
}