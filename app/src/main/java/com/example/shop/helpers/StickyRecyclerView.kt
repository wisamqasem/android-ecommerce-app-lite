package com.example.shop.helpers

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View

/**
 * A [android.support.v7.widget.RecyclerView] implementation which snaps the current visible
 * item to the center of the screen based on scroll directions defined by [ ][.getScrollDirection]
 *
 *
 * Designed to work with [LinearLayoutManager] with [.HORIZONTAL] orientation *ONLY*
 */

class StickyRecyclerView : RecyclerView {

    /**
     * Returns the last recorded scrolling direction of the StickyRecyclerView as
     * set in [.onScrolled]
     *
     * @return [.SCROLL_DIRECTION_LEFT] or [.SCROLL_DIRECTION_RIGHT]
     */
    var scrollDirection: Int = 0
        private set(dx) {
            field = if (dx >= 0) SCROLL_DIRECTION_LEFT else SCROLL_DIRECTION_RIGHT
        }
    private var mCenterItemChangedListener: OnCenterItemChangedListener? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            if (mCenterItemChangedListener != null) {
                mCenterItemChangedListener!!.onCenterItemChanged(findCenterViewIndex())
            }
        }
    }

    override fun onScrolled(dx: Int, dy: Int) {
        super.onScrolled(dx, dy)
        scrollDirection = dx

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val percentage = getPercentageFromCenter(child)
            val scale = 1f - 0.4f * percentage

            child.scaleX = scale
            child.scaleY = scale
        }
    }

    override fun fling(velocityX: Int, velocityY: Int): Boolean {
        smoothScrollToCenter()
        return true
    }

    private fun getPercentageFromCenter(child: View): Float {
        val centerX = (measuredWidth / 2).toFloat()
        val childCenterX = child.x + child.width / 2
        val offSet = Math.max(centerX, childCenterX) - Math.min(centerX, childCenterX)
        val maxOffset = measuredWidth / 2 + child.width
        return offSet / maxOffset
    }

    private fun findCenterViewIndex(): Int {
        val count = childCount
        var index = -1
        var closest = Integer.MAX_VALUE
        val centerX = measuredWidth / 2

        for (i in 0 until count) {
            val child = layoutManager.getChildAt(i)
            val childCenterX = (child.x + child.width / 2).toInt()
            val distance = Math.abs(centerX - childCenterX)
            if (distance < closest) {
                closest = distance
                index = i
            }
        }

        return if (index == -1) {
            throw IllegalStateException("Can\'t find central view.")
        } else {
            index
        }
    }

    private fun smoothScrollToCenter() {
        val linearLayoutManager = layoutManager as LinearLayoutManager
        val lastVisibleView = linearLayoutManager.findLastVisibleItemPosition()
        val firstVisibleView = linearLayoutManager.findFirstVisibleItemPosition()
        val firstView = linearLayoutManager.findViewByPosition(firstVisibleView)
        val lastView = linearLayoutManager.findViewByPosition(lastVisibleView)
        val screenWidth = this.width

        //since views have variable sizes, we need to calculate side margins separately.
        val leftMargin = (screenWidth - lastView.width) / 2
        val rightMargin = (screenWidth - firstView.width) / 2 + firstView.width
        val leftEdge = lastView.left
        val rightEdge = firstView.right
        val scrollDistanceLeft = leftEdge - leftMargin
        val scrollDistanceRight = rightMargin - rightEdge

        if (scrollDirection == SCROLL_DIRECTION_LEFT) {
            smoothScrollBy(scrollDistanceLeft, 0)
        } else if (scrollDirection == SCROLL_DIRECTION_RIGHT) {
            smoothScrollBy(-scrollDistanceRight, 0)
        }
    }

    /**
     * Set a listener that will be notified when the central item is changed.
     *
     * @param listener Listener to set or null to clear
     */
    fun setOnCenterItemChangedListener(listener: OnCenterItemChangedListener) {
        mCenterItemChangedListener = listener
    }

    /**
     * A listener interface that can be added to the [StickyRecyclerView] to get
     * notified when the central item is changed.
     */
    interface OnCenterItemChangedListener {

        /**
         * @param centerPosition position of the center item
         */
        fun onCenterItemChanged(centerPosition: Int)
    }

    companion object {

        /**
         * The horizontal distance (in pixels) scrolled is > 0
         *
         * @see .getScrollDirection
         */
        val SCROLL_DIRECTION_LEFT = 0

        /**
         * The horizontal distance scrolled (in pixels) is < 0
         *
         * @see .getScrollDirection
         */
        val SCROLL_DIRECTION_RIGHT = 1
    }
}