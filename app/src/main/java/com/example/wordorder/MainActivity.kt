package com.example.wordorder

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.wordorder.adapter.SentenceAdapter
import com.example.wordorder.adapter.WordsAdapter
import com.example.wordorder.databinding.ActivityMainBinding
import com.google.android.flexbox.*

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val wordArray4 = resources.getStringArray(R.array.sentence4)
        val words = mutableListOf<String>(*wordArray4)

        val wordsAdapter = WordsAdapter {
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
        button.setOnClickListener {
            if (sentenceAdapter.currentList == words) {
                button.setBackgroundColor(Color.GREEN)
            } else {
                button.setBackgroundColor(Color.RED)
            }
        }

    }
}
