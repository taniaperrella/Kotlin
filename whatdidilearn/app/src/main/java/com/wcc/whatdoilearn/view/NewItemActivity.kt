package com.wcc.whatdoilearn.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.wcc.whatdoilearn.R
import com.wcc.whatdoilearn.data.LearnedItemsDatabase
import com.wcc.whatdoilearn.databinding.ActivityMainBinding
import com.wcc.whatdoilearn.databinding.ActivityNewItemBinding
import com.wcc.whatdoilearn.entities.LearnedItem
import com.wcc.whatdoilearn.repository.LearnedItemsRepository
import com.wcc.whatdoilearn.viewmodel.NewLearnedItemViewModel
import com.wcc.whatdoilearn.viewmodel.NewLearnedItemViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class NewItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewItemBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.title = "New Learned Item"

        val dao = LearnedItemsDatabase.getDatabase(this, CoroutineScope(Dispatchers.IO)).learnedItemDao()
        val repository = LearnedItemsRepository(dao)
        val viewModelFactory = NewLearnedItemViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(NewLearnedItemViewModel::class.java)

        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val description = binding.descriptionEditText.text.toString()

            viewModel.insertNewLearnedItem(title, description)

            navigateToMainActivity()
        }
    }

    private fun navigateToMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}