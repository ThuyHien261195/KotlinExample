package com.example.thuyhien.kotlinrxjava.ui.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thuyhien.kotlinrxjava.R
import com.example.thuyhien.kotlinrxjava.model.Comment
import com.example.thuyhien.kotlinrxjava.ui.viewholder.CommentViewHolder

/**
 * Created by thuyhien on 3/29/18.
 */
class CommentAdapter(private val commentList: ArrayList<Comment>) : RecyclerView.Adapter<CommentViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommentViewHolder {
        val rowView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(rowView)
    }

    override fun getItemCount(): Int = commentList.size


    override fun onBindViewHolder(holder: CommentViewHolder?, position: Int) {
        holder?.bind(commentList[position])
    }

    fun addComment(comment: Comment) {
        commentList.add(comment)
//        notifyItemInserted(commentList.size - 1)
        notifyDataSetChanged()
        Log.e("2359 comment", commentList.toString())
    }
}