package com.example.wordorder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordorder.adapter.WordsAdapter
import com.example.wordorder.callback.DropListener
import com.google.android.flexbox.*
import com.example.wordorder.adapter.SentenceAdapter
import com.example.wordorder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // values of the draggable views (usually this should come from a data source)
    private val words = mutableListOf("world", "a", "!", "What", "wonderful")
    // last selected word
    private var selectedWord = ""

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sentenceAdapter = SentenceAdapter()
        val wordsAdapter = WordsAdapter {
            selectedWord = it
        }.apply {
            submitList(words)
        }

        binding.rvSentence.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvSentence.adapter = sentenceAdapter

        binding.rvSentence.setOnDragListener(
            DropListener {
                wordsAdapter.removeItem(selectedWord)
                sentenceAdapter.addItem(selectedWord)
            }
        )

        binding.rvWords.layoutManager = FlexboxLayoutManager(this, FlexDirection.ROW, FlexWrap.WRAP).apply {
            justifyContent = JustifyContent.SPACE_EVENLY
            alignItems = AlignItems.CENTER
        }

        binding.rvWords.adapter = wordsAdapter
    }
}
