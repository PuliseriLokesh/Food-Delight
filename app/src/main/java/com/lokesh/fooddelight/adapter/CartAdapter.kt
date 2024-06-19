package com.lokesh.fooddelight.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lokesh.fooddelight.R
import com.lokesh.fooddelight.database.order.FoodEntity

class CartAdapter(val context: Context,val foodInfo: List<FoodEntity>):RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_single_food_cart,parent,false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val info = foodInfo[position]
        holder.foodName.text = info.name
        holder.cost.text = info.cost
    }

    override fun getItemCount(): Int {
        return foodInfo.size
    }
    class CartViewHolder(view: View): RecyclerView.ViewHolder(view){
        val foodName: TextView = view.findViewById(R.id.txtfoodNameCart)
        val cost: TextView = view.findViewById(R.id.txtCostCart)
    }
}
