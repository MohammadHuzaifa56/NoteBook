package com.example.notebook.presentation.notes_listing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notebook.databinding.ActivityMainBinding
import com.example.notebook.presentation.add_new_note.AddNotesActivity
import com.example.notebook.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var notesListAdapter: GroupedNotesListAdapter
    private val notesViewModel by viewModels<NotesListViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launchWhenStarted {
            notesViewModel.getNotesList().collect{
                when (it) {
                    is Resource.Error -> {
                        showLoading(false)
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        showLoading(false)
                        notesListAdapter.setNotesList(it.data)
                        binding.recNotes.apply {
                            layoutManager = LinearLayoutManager(
                                this@MainActivity,
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                            adapter = notesListAdapter

                        }

                    }
                }
            }
        }

        binding.fabAddNotes.setOnClickListener{
            startActivity(Intent(this, AddNotesActivity::class.java))
        }
    }

    private fun showLoading(showLoading: Boolean) {
        if (showLoading) {
            binding.recNotes.visibility = View.GONE
            binding.progressCircular.visibility = View.VISIBLE
        } else {
            binding.recNotes.visibility = View.VISIBLE
            binding.progressCircular.visibility = View.GONE
        }
    }
}