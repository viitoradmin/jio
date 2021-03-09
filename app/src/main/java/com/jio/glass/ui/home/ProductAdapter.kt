package com.jio.glass.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jio.glass.databinding.ItemJioProductsBinding
import com.jio.glass.domain.entity.Product

class ProductAdapter(val productList: List<Product>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(val productBinding: ItemJioProductsBinding)
        : RecyclerView.ViewHolder(productBinding.root) {

            fun bindProduct(product: Product){
                productBinding.civProductLogo.setImageResource(product.image)
                productBinding.atvProductName.text = product.name
            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemJioProductsBinding.inflate(LayoutInflater.from(parent.context))
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindProduct(productList[position])
    }

    override fun getItemCount() = productList.size

}