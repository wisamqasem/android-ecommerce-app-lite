package com.example.shop.helpers

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


class ItemDecoration : RecyclerView.ItemDecoration {

    /**
     * @param startPadding
     * @param endPadding
     */
    private val startPadding: Int
    private val endPadding: Int

    constructor(startPadding: Int, endPadding: Int) {
        this.startPadding = startPadding
        this.endPadding = endPadding
    }

    constructor(context: Context) {
        this.startPadding = Utils.getDisplayWidth(context) / 2
        this.endPadding = 0

    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        val totalWidth = parent.width
        //first element
        if (parent.getChildAdapterPosition(view) == 0) {
            var firstPadding = (totalWidth - startPadding) / 2
            //            int firstPadding = totalWidth / 2 - startPadding;
            firstPadding = Math.max(0, firstPadding)
            if (firstPadding == 0) {
                firstPadding = startPadding / 2
            }
            outRect.set(firstPadding, 0, 0, 0)
        }

        //last element
        if (parent.getChildAdapterPosition(view) == parent.adapter.itemCount - 1 && parent.adapter.itemCount > 1) {
            var lastPadding = (totalWidth - endPadding) / 2
            lastPadding = Math.max(0, lastPadding)
            outRect.set(0, 0, lastPadding, 0)
        }
    }

}