package com.example.wordorder

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordorder.adapter.WordsAdapter
import com.example.wordorder.callback.DropListener
import com.google.android.flexbox.*
import com.example.wordorder.adapter.SentenceAdapter
import com.example.wordorder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // values of the draggable views (usually this should come from a data source)
    ////private val words = mutableListOf("Did", "you", "run", "the", "run", "right", "now", "?")
    // last selected word
    private var selectedWord = ""

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val wordArray4 = resources.getStringArray(R.array.sentence4)
        val words = mutableListOf<String>(*wordArray4)

        val wordsAdapter = WordsAdapter {
            selectedWord = it
        }.apply {
            submitList(words)
        }
        val sentenceAdapter = SentenceAdapter(wordsAdapter)

        binding.rvSentence.layoutManager =
            FlexboxLayoutManager(this, FlexDirection.ROW, FlexWrap.WRAP).apply {
                justifyContent = JustifyContent.FLEX_START
                alignItems = AlignItems.CENTER
            }
        binding.rvSentence.adapter = sentenceAdapter

        binding.rvWords.layoutManager =
            FlexboxLayoutManager(this, FlexDirection.ROW, FlexWrap.WRAP).apply {
                justifyContent = JustifyContent.FLEX_START
                alignItems = AlignItems.CENTER
            }

        binding.rvWords.adapter = wordsAdapter

        val buttonID = R.id.button2
        val button = findViewById<Button>(buttonID)
        button.setOnClickListener { view ->
            if (sentenceAdapter.currentList == words) {
                button.setBackgroundColor(Color.GREEN)
            } else {
                button.setBackgroundColor(Color.RED)
            }
        }

    }
}
