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
    var sentenceList = mutableListOf<Int>(
        R.array.sentence1,
        R.array.sentence2,
        R.array.sentence3,
        R.array.sentence4,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var words = mutableListOf<String>()
        for (sentence in sentenceList) {
            val wordArray4 = resources.getStringArray(R.array.sentence4)
            words = mutableListOf<String>(*wordArray4)
        }

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
                if (button.text == "Confirm") {
                    button.setBackgroundColor(Color.GREEN)
                    button.text = "Continue"
                    button.setTextColor(Color.BLACK)
                } else {
                    button.setBackgroundColor(Color.rgb(98, 0, 238))
                    button.setTextColor(Color.WHITE)
                    button.text = "Confirm"
                }

            } else {
                button.setBackgroundColor(Color.RED)
                button.text = "Confirm"
            }
        }

    }
}
