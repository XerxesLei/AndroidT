package com.androidt.sdk.study

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class MainActivity : AppCompatActivity() {

    private val mAdapter by lazy {
        Adapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                }

                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                    return false
                }

                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                }

            })
        }

        mAdapter.setOnClick {
            when (it) {
                "TextView" -> {
                    startActivity(Intent(this, ContainActivity::class.java).apply {
                        putExtra(ContainActivity.ARGS, it)
                    })
                }
            }
        }
    }

    class Adapter : RecyclerView.Adapter<Holder>() {

        private val items = listOf<String>("EditView", "TextView")

        private lateinit var onClick : (string : String) -> Unit

        fun setOnClick(f : (string : String) -> Unit) {
            onClick = f
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            return Holder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false))
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.mTextView.text = items[position]
            holder.mTextView.setOnClickListener {
                onClick(items[position])
            }
        }

    }

    class Holder(item : View) : RecyclerView.ViewHolder(item) {
        val mTextView: TextView = item.text
    }
}
