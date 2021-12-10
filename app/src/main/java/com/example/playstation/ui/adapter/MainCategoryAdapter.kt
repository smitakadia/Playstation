package com.example.playstation.ui.adapter

import android.net.Uri
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.playstation.R
import com.example.playstation.databinding.RowMainCategoryBinding
import com.example.playstation.model.PlaystationListModel
import com.example.playstation.ui.activity.ActPlayStationList
import com.example.playstation.utils.DateUtil


class MainCategoryAdapter(
    var mContext: ActPlayStationList,
    var mList: List<PlaystationListModel.Data>?,
    var listener: CustomViewHolderListener
) :
    RecyclerView.Adapter<MainCategoryAdapter.UserViewHolder>() {

    interface CustomViewHolderListener{
        fun onCustomItemClicked(x: PlaystationListModel.Data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val mBinding = RowMainCategoryBinding.inflate(mContext.layoutInflater, parent, false)
        return UserViewHolder(mBinding)
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        // bind data
        holder.bindData(mList!![position])

      if(mList!![position].imageBackground != null)
        Glide.with(holder.mBinding.ivImage.context).load(Uri.parse(mList!![position].imageBackground)).error(R.drawable.ic_placeholder).into(holder.mBinding.ivImage)



        holder.mBinding.tvTime.text = DateUtil.getDateFormatedddMMYYYY(mList!![position].updated)

        holder.itemView.setOnClickListener {
            listener.onCustomItemClicked(mList!![position])
        }



    }

    class UserViewHolder(var mBinding: RowMainCategoryBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun bindData(mdl: PlaystationListModel.Data ) {
            mBinding.mdl = mdl


            mBinding.executePendingBindings()

        }
    }
}