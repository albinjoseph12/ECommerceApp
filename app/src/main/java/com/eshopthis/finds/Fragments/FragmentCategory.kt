package com.eshopthis.finds.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshopthis.finds.Activities.*
import com.eshopthis.finds.Adapter.CategoryAdapter
import com.eshopthis.finds.R
import com.eshopthis.finds.data.CategoryItem

class FragmentCategory : Fragment(), CategoryAdapter.OnCategoryClickListener {

    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter
    private val categoryItems = mutableListOf<CategoryItem>()
    private lateinit var searchView: SearchView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
            val view = inflater.inflate(R.layout.fragment_category, container, false)
            searchView = view.findViewById(R.id.searchView)
            setupSearchView()
            categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView)

            initializeCategoryItems()

            categoryRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            categoryAdapter = CategoryAdapter(requireContext(), categoryItems, this)
            categoryRecyclerView.adapter = categoryAdapter

    return view
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Perform the final search
                // TODO: Implement your search logic here
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Perform the search as the user types
                // TODO: Implement real-time search logic here
                return false
            }
        })
    }

    private fun initializeCategoryItems() {
        categoryItems.add(CategoryItem("Clearance", R.drawable.clearance))
        categoryItems.add(CategoryItem("Best Sellers", R.drawable.best_seller))
        categoryItems.add(CategoryItem("Classics", R.drawable.classics))
        categoryItems.add(CategoryItem("Scientific", R.drawable.scientific))
        categoryItems.add(CategoryItem("Fiction", R.drawable.fiction))
        categoryItems.add(CategoryItem("Religion", R.drawable.religion))
        categoryItems.add(CategoryItem("History", R.drawable.history))
        categoryItems.add(CategoryItem("Anime", R.drawable.anime))
        categoryItems.add(CategoryItem("Arts", R.drawable.arts))
        // Add other categories similarly
    }

    override fun onCategoryClick(categoryItem: CategoryItem) {
        val intent = when (categoryItem.name) {
            "Clearance" -> Intent(requireContext(), Clearance::class.java)
            "Best Sellers" -> Intent(requireContext(), BestSeller::class.java)
            "Classics" -> Intent(requireContext(), ClassicBooks::class.java)
            "Scientific" -> Intent(requireContext(), ScientificBooks::class.java)
            "Fiction" -> Intent(requireContext(), FictionBooks::class.java)
            "Religion" -> Intent(requireContext(), ReligiousBooks::class.java)
            "History" -> Intent(requireContext(), HistoryBooks::class.java)
            "Anime" -> Intent(requireContext(), AnimeBooks::class.java)
            "Arts" -> Intent(requireContext(), ArtsActivity::class.java)
            else -> null
        }

        intent?.let { startActivity(it) }
    }
}