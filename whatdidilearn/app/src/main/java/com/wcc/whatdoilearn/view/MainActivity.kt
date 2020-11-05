package com.wcc.whatdoilearn.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wcc.whatdoilearn.data.LearnedItemsDatabase
import com.wcc.whatdoilearn.databinding.ActivityMainBinding
import com.wcc.whatdoilearn.repository.LearnedItemsRepository
import com.wcc.whatdoilearn.viewmodel.LearnedItemViewModel
import com.wcc.whatdoilearn.viewmodel.LearnedItemViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.itemsLearnedRecyclerView
        val adapter = LearnedItemsAdapter()
        recyclerView.adapter = adapter


        val database = LearnedItemsDatabase.getDatabase(this, CoroutineScope(Dispatchers.IO))
        val learnedItemsDao = database.learnedItemDao()
        val repository = LearnedItemsRepository(learnedItemsDao)

        val viewModelFactory = LearnedItemViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(LearnedItemViewModel::class.java)

        val learnedItems = viewModel.learnedItems
        learnedItems.observe(this, Observer {
            adapter.data = it
        })

        binding.newItemButton.setOnClickListener {
            val intent = Intent(this, NewItemActivity::class.java)
            startActivity(intent)
        }
    }
}