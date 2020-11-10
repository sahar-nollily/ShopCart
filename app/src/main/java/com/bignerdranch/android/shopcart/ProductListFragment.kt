package com.bignerdranch.android.shopcart

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_product_list.view.*


class ProductListFragment : Fragment() {
    private lateinit var  productViewModel: ProductViewModel
    lateinit var recyclerView: RecyclerView
    private var product = emptyList<Product>()
    private var adapter = ProductAdapter(product)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_product_list, container, false)
        recyclerView = view.findViewById(R.id.product_recyclerView)
        recyclerView.layoutManager = GridLayoutManager(context,2)
        productViewModel= ViewModelProvider(this).get(ProductViewModel::class.java)
        productViewModel.getProducts.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
        recyclerView.adapter = adapter

        view.floatingActionButton.setOnClickListener {
            var product1 = Product(0,"item1",13.50,"http://placeimg.com/640/480/business")
            productViewModel.addProduct(product1)
        }

        return view
    }

    private inner class ProductHolder(view: View): RecyclerView.ViewHolder(view){
        lateinit var product: Product
        var productImage: ImageView= view.findViewById(R.id.product_img)
        var productName : TextView = view.findViewById(R.id.product_name_text_view)
        var productPrice : TextView = view.findViewById(R.id.product_price_text_view)



        fun bind(product: Product){
            this.product= product
            Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(productImage)
            productName.text = product.productName
            productPrice.text = product.productPrice.toString()




        }

    }

    private inner class ProductAdapter(var product: List<Product>):RecyclerView.Adapter<ProductHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
            var view = layoutInflater.inflate(R.layout.product_list_item, parent,false)

            return ProductHolder(view)
        }

        override fun onBindViewHolder(holder: ProductHolder, position: Int) {
            var product = this.product[position]
            holder.bind(product)
        }

        override fun getItemCount(): Int {
            return product.size
        }

        fun setData(product: List<Product>){
            this.product = product
            notifyDataSetChanged()
        }

    }
}