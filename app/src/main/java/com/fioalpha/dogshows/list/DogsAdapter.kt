package com.fioalpha.dogshows.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.fioalpha.dogshows.R
import com.fioalpha.dogshows.domain.model.Dog
import com.squareup.picasso.Picasso

class DogsAdapter(
    private val action: (String) -> Any
) : RecyclerView.Adapter<DogsAdapter.DogsViewHolder>() {

    private val dogsItems: MutableList<Dog> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_dog_image, parent, false).run {
                DogsViewHolder(this, action)
            }
    }

    override fun getItemCount(): Int = dogsItems.size

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        holder.bind(dogsItems[position])
    }

    fun setData(dogs: List<Dog>) {
        dogsItems.clear()
        dogsItems.addAll(dogs)
        notifyDataSetChanged()
    }

    class DogsViewHolder(
        view: View,
        private val action: (String) -> Any
    ) : RecyclerView.ViewHolder(view) {
        fun bind(item: Dog) {
            Picasso.with(itemView.context)
                .load(item.images)
                .placeholder(R.drawable.ic_dog)
                .centerCrop()
                .fit()
                .into(itemView.findViewById<ImageView>(R.id.dog_image))
            itemView.setOnClickListener { action(item.images) }
        }
    }
}