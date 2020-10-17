package cl.storeproducts.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import cl.storeproducts.R
import cl.storeproducts.model.db.ProductMini
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_product.view.*


class ProductAdapter(private var productDataset: MutableList<ProductMini>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_product, parent, false)
        return ProductViewHolder(view)
    }
    val productSelected = MutableLiveData<ProductMini>()


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        Log.d("Adapter de los datos", "${productDataset.get(position)}")
        holder.textTitle.text = productDataset.get(position).price.toString()
        holder.textTitle2.text = productDataset.get(position).name
        holder.textTitle3.text = productDataset.get(position).id.toString()
        Picasso.get().load(productDataset.get(position).image).into(holder.textTitle4)
        productSelected.value = productDataset.get(position)
    }


    override fun getItemCount(): Int {
        return productDataset.size
    }

    fun updateItems(it: List<ProductMini>) {
        productDataset.clear()
        productDataset.addAll(it)
        notifyDataSetChanged()
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textTitle = itemView.nameView
        var textTitle2 = itemView.priceView
        var textTitle3 = itemView.idView
        var textTitle4 = itemView.imageView

    }
}