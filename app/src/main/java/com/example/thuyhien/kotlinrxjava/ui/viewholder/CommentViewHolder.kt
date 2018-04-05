package com.example.thuyhien.kotlinrxjava.ui.viewholder

import android.view.View
import com.example.thuyhien.kotlinrxjava.model.Comment
import kotlinx.android.synthetic.main.item_comment.*

/**
 * Created by thuyhien on 3/29/18.
 */
class CommentViewHolder(containerView: View) : BaseViewHolder<Comment>(containerView) {
    override fun bind(data: Comment) {
        tvComment.text  = data.comment
    }
}