package com.example.githubapi.presentation.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubapi.R
import com.example.githubapi.data.remote.api.models.ClosedPRModel

class ClosedPRListAdapter : RecyclerView.Adapter<ClosedPRListAdapter.ClosedPRListViewHolder>() {

    private lateinit var context: Context
    private var closedPRList = mutableListOf<ClosedPRModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClosedPRListViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.list_github_closed_pr, parent, false)
        return ClosedPRListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClosedPRListViewHolder, position: Int) {

        val closedPRModelValue: ClosedPRModel = closedPRList[position]
        closedPRModelValue.let {
            val user = it.user
            Glide.with(context)
                .load(user.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.imgAvatar)

            holder.txtPRTitle.text = it.title
            holder.txtUserName.text = user.login
            holder.txtPRCreated.text = "Created at: " + it.getCreatedDateAsString()
            holder.txtPRClosed.text = "Closed at: " + it.getClosedDateAsString()
        }
    }

    fun setData(dataPresent: List<ClosedPRModel>) {
        this.closedPRList = dataPresent.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return closedPRList.size
    }

    inner class ClosedPRListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgAvatar: ImageView = itemView.findViewById(R.id.imgAvatar)
        val txtUserName: AppCompatTextView = itemView.findViewById(R.id.txtUserName)
        val txtPRTitle: AppCompatTextView = itemView.findViewById(R.id.txtPRTitle)
        val txtPRCreated: AppCompatTextView = itemView.findViewById(R.id.txtPRCreated)
        val txtPRClosed: AppCompatTextView = itemView.findViewById(R.id.txtPRClosed)
    }

}