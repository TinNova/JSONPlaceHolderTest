package com.novakovic.tin.nutmegtest.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.novakovic.tin.nutmegtest.R
import com.novakovic.tin.nutmegtest.model.UserPostModel
import kotlinx.android.synthetic.main.item_list_posts.view.*

class PostAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var posts: MutableList<UserPostModel> = mutableListOf()

    fun setData(data: MutableList<UserPostModel>) {
        posts = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = posts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_posts, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.titleTv.text = posts[position].title
        holder.itemView.usernameTv.text = posts[position].username
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
