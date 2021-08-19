package com.iremdogan.fooddeliveryproject.ui.mealdetail

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.iremdogan.fooddeliveryproject.R

class IngredientRecyclerViewAdapter: RecyclerView.Adapter<IngredientRecyclerViewAdapter.ViewHolder>() {
    private lateinit var ingredientList: List<IngredientModel>
    private var listener: IIngredientOnClick? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = ingredientList[position]
        holder.bind(meal, listener)
    }

    override fun getItemCount(): Int = ingredientList.size

    fun setData(ingredientList: List<IngredientModel>?) {
        ingredientList?.let {
            this.ingredientList = ingredientList
            notifyDataSetChanged()
        }
    }

    fun addListener(listener: IIngredientOnClick?) {
        this.listener = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ingredientLayout : CardView = view.findViewById(R.id.ingredient_layout)
        private val ingredientName: TextView = view.findViewById(R.id.ingredient_name_text_view)

        fun bind(ingredientModel: IngredientModel, listener: IIngredientOnClick?) {
            ingredientName.text = ingredientModel.name

            ingredientLayout.setOnClickListener {
                if(ingredientName.paintFlags == Paint.STRIKE_THRU_TEXT_FLAG){
                    ingredientName.paintFlags = 0
                } else {
                    ingredientName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }
                listener?.onClick(ingredientModel)
            }
        }
    }
}