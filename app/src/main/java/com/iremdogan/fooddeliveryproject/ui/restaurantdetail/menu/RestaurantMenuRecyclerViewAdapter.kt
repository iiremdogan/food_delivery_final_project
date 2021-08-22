package com.iremdogan.fooddeliveryproject.ui.restaurantdetail.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iremdogan.fooddeliveryproject.R
import com.iremdogan.fooddeliveryproject.model.entity.meal.MealData

class RestaurantMenuRecyclerViewAdapter: RecyclerView.Adapter<RestaurantMenuRecyclerViewAdapter.ViewHolder>() {
    private lateinit var mealList: List<MealData>
    private var listener: IMealOnClick? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = mealList[position]
        holder.bind(meal, listener)
    }

    override fun getItemCount(): Int = mealList.size

    fun setData(mealList: List<MealData>?) {
        mealList?.let {
            this.mealList = mealList
            notifyDataSetChanged()
        }
    }

    fun addListener(listener: IMealOnClick?) {
        this.listener = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mealLayout : ConstraintLayout = view.findViewById(R.id.meal_layout)
        private val mealImageView: AppCompatImageView = view.findViewById(R.id.meal_image_view)
        private val mealName: TextView = view.findViewById(R.id.meal_name_text_view)
        private val mealIngredients: TextView = view.findViewById(R.id.meal_ingredients_text_view)
        private val mealPrice: TextView = view.findViewById(R.id.meal_price_text_view)

        fun bind(mealModel: MealData, listener: IMealOnClick?) {
            mealName.text = mealModel.name
            mealPrice.text = mealModel.price.toString()

            var ingredientsText = ""
            mealModel.ingredients.forEach {
                ingredientsText += "$it  "
            }

            mealIngredients.text = ingredientsText

            Glide.with(mealImageView.context)
                .load(R.drawable.ic_heart_filled).into(mealImageView)

//            Glide.with(mealImageView.context)
//                .load(mealModel.imageUrl).into(mealImageView)

            mealLayout.setOnClickListener {
                listener?.onClick(mealModel)
            }
        }
    }
}