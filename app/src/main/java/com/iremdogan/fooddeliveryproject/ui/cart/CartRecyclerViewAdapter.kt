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
import com.iremdogan.fooddeliveryproject.model.entity.meal.MealData

class CartRecyclerViewAdapter : RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder>() {
    private lateinit var itemList: MutableList<MealData>
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

    fun setData(itemList: MutableList<MealData>?) {
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

        // TODO : count?
        private var count = 1

        fun bind(meal: MealData, listener: ICartOnClick?) {
            cartItemMealName.text = meal.name
            cartItemMealPrice.text = meal.price.toString()

            Glide.with(cartItemImageView.context)
                .load(R.drawable.ic_heart_filled).into(cartItemImageView)

            Glide.with(cartItemImageView.context)
                .load(meal.imageUrl).into(cartItemImageView)

            increaseButton.setOnClickListener {
                count++
                cartItemMealCount.text = count.toString()
                listener?.onClickIncreaseButton(meal)
            }

            decreaseButton.setOnClickListener {
                if((count - 1) != 0 ){
                    count--
                    cartItemMealCount.text = count.toString()
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