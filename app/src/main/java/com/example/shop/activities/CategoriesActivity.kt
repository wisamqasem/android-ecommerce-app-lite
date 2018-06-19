package com.example.shop.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.shop.R
import com.example.shop.adapters.CategoriesAdapter
import com.example.shop.contracts.CategoriesContract
import com.example.shop.entities.Category
import com.example.shop.presenter.CategoriesPresenter
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.android.synthetic.main.app_bar_home.*
import java.util.*

class CategoriesActivity : BaseActivity(), CategoriesContract.ICategoriesView {

    private lateinit var categories: ArrayList<Category>
    private lateinit var viewAdapter: CategoriesAdapter
    private lateinit var presenter: CategoriesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_categories, content_container)
        setupViewItems()
        presenter.load()
        supportActionBar!!.title = getString(R.string.categories)
    }

    override fun onResume() {
        super.onResume()
        nav_view.menu.getItem(1).isChecked = true
    }


    private fun setupViewItems() {
        var viewManager = LinearLayoutManager(this)
        categories = ArrayList()
        viewAdapter = CategoriesAdapter(this, categories)

        shop_recycler.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
        presenter = CategoriesPresenter(this)
    }

    override fun categoriesLoaded(categories: ArrayList<Category>) {
        this.categories.addAll(categories)
        viewAdapter.notifyDataSetChanged();
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, CategoriesActivity::class.java)
            context.startActivity(intent)
        }
    }
}
