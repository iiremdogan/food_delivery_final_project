package com.iremdogan.fooddeliveryproject.ui.home.restaurantmenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iremdogan.fooddeliveryproject.R

class RestaurantRecyclerViewAdapter: RecyclerView.Adapter<RestaurantRecyclerViewAdapter.ViewHolder>() {
    private lateinit var restaurantList: List<RestaurantMenuModel>
    private var listener: IRestaurantOnClick? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurantList[position]
        holder.bind(restaurant, listener)
    }

    override fun getItemCount(): Int = restaurantList.size

    fun setData(restaurantList: List<RestaurantMenuModel>?) {
        restaurantList?.let {
            this.restaurantList = restaurantList
            notifyDataSetChanged()
        }
    }

    fun addListener(listener: IRestaurantOnClick?) {
        this.listener = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val restaurantLayout : ConstraintLayout = view.findViewById(R.id.restaurant_item_layout)
        private val restaurantItemImageView: AppCompatImageView = view.findViewById(R.id.restaurant_item_image_view)
        private val restaurantName: TextView = view.findViewById(R.id.restaurant_name_text_view)
        private val restaurantMinFee: TextView = view.findViewById(R.id.restaurant_min_fee_text_view)
        private val restaurantDeliveryTime: TextView = view.findViewById(R.id.restaurant_delivery_time_text_view)

        fun bind(restaurantModel: RestaurantMenuModel, listener: IRestaurantOnClick?) {
            restaurantName.text = restaurantModel.name
            restaurantMinFee.text = restaurantModel.minFee
            restaurantDeliveryTime.text = restaurantModel.minDelivery

            Glide.with(restaurantItemImageView.context)
                .load(R.drawable.ic_heart_filled).into(restaurantItemImageView)

            restaurantLayout.setOnClickListener {
                listener?.onClick(restaurantModel)
            }
        }
    }
}