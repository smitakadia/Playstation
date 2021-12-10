package com.example.playstation.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
abstract class RecyclerViewPaginationCallback(private val recyclerView: RecyclerView, axis: AXIS) {

    var locked = false

    private val onScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val scrolled = when (axis) {

                AXIS.HORIZONTAL -> {
                    dx > 0
                }

                AXIS.VERTICAL -> {
                    dy > 0
                }

                AXIS.BOTH -> {
                    dx > 0 || dy > 0
                }
            }

            if (condition && scrolled && recyclerView.layoutManager != null) {

                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager

                val visibleItemCount = linearLayoutManager.childCount
                val totalItemCount = linearLayoutManager.itemCount
                val pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()

                if (!locked) {

                    if (visibleItemCount + pastVisibleItems >= totalItemCount) {

                        paginate()

                        lock()
                    }
                }
            }
        }
    }

    init {

        recyclerView.addOnScrollListener(onScrollListener)
    }

    abstract fun paginate()

    private var condition = false

    fun updateCondition(condition: Boolean) {
        this.condition = condition
    }

    fun lock() {
        locked = true
    }

    fun unlock() {
        locked = false
    }

    fun isLocked(): Boolean {
        return locked
    }

    enum class AXIS {

        HORIZONTAL,
        VERTICAL,
        BOTH
    }
}