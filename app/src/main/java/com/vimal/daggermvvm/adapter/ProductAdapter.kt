/**
 * Created by Vimal on Feb-2023.
 */
package com.vimal.daggermvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vimal.daggermvvm.R
import com.vimal.daggermvvm.databinding.AdapterProductBinding
import com.vimal.daggermvvm.models.Product

class ProductAdapter(private val itemclicked: (Product) -> Unit) :
    RecyclerView.Adapter<ProductAdapter.PaymentHolder>() {

    var productList = mutableListOf<Product>()

    fun setMovies(movies: List<Product>) {
        this.productList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PaymentHolder {
        val itemBinding =
            AdapterProductBinding.inflate(LayoutInflater.from(parent.context))
        return PaymentHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PaymentHolder, position: Int) {
        val paymentBean: Product = productList[position]
        holder.bind(paymentBean)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class PaymentHolder(private val itemBinding: AdapterProductBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(paymentBean: Product) {

            paymentBean.let {
                when (it.title) {
                    null -> itemBinding.title.visibility = View.GONE
                    else -> itemBinding.title.text = it.title
                }
                Glide.with(itemBinding.root.context)
                    .load(it.image).placeholder(R.drawable.ic_launcher_background)
                    .into(itemBinding.image)
                itemBinding.description.text = it.description
                itemBinding.mainlay.setOnClickListener() {
                    var pos = position
                    pos++
                    Toast.makeText(
                        itemBinding.root.context, itemBinding.title.text.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
