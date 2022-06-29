package com.cesor.android.storesprueba1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cesor.android.storesprueba1.databinding.ItemStoreBinding

/****
 * Project: StoresPrueba1
 * From: com.cesor.android.storesprueba1
 * Created by: César Castro on 22/06/2022 at 19:11.
 ***/
class StoreAdapter(private var stores: MutableList<StoreEntity>, private var listener: OnClickListener) :
    RecyclerView.Adapter<StoreAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context

        val view = LayoutInflater.from(mContext).inflate(R.layout.item_store, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val store = stores[position]
        with(holder){
            setListener(store)
            mBinding.tvName.text = store.name

            Glide.with(mContext)
                .load(store.photoUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(mBinding.imgPhoto)
        }

    }

    override fun getItemCount(): Int = stores.size

    fun add(storeEntity: StoreEntity) {
        if (!stores.contains(storeEntity)) {
            stores.add(storeEntity)
            notifyItemInserted(stores.size-1)
        }

    }

    fun setStores(stores: MutableList<StoreEntity>) {
        this.stores = stores
        notifyDataSetChanged()

    }

    fun update(storeEntity: StoreEntity) {
        val index = stores.indexOf(storeEntity)
        if (index != -1){
            stores[index] = storeEntity
            notifyItemChanged(index)
        }
    }

    fun delete(storeEntity: StoreEntity) {
        val index = stores.indexOf(storeEntity)
        if (index != -1){
            stores.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val mBinding = ItemStoreBinding.bind(view)

        fun setListener(storeEntity: StoreEntity){
            with(mBinding.root){
                setOnClickListener { listener.onClick(storeEntity.id) }
                setOnLongClickListener {
                    listener.onDeleteStore(storeEntity)
                    true}
            }
            mBinding.cbFavorite.setOnClickListener { listener.onFavoriteStore(storeEntity) }
        }
    }
}