package com.eshopthis.finds.data

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class Space(private val space: Int) : RecyclerView.ItemDecoration() {

    // This class is for adding space between items in the RecyclerView
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.left = space
        outRect.right = space
        outRect.top = space
        outRect.bottom = space
    }
}