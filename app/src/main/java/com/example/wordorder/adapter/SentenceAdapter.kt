package com.example.wordorder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wordorder.R
import com.example.wordorder.callback.WordsDiffCallback

/**
 * A @androidx.recyclerview.widget.RecyclerView adapter to show dropped items
 * */
class SentenceAdapter(wordsAdapter: WordsAdapter) : ListAdapter<String, SentenceAdapter.WordsViewHolder>(WordsDiffCallback()) {
    private val wa: WordsAdapter = wordsAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)
        return WordsViewHolder(view)
    }

    init{
        wa.setSentenceAdapter(this)
    }



    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun removeItem(selectedWord: String) {
        val list = ArrayList(currentList)
        list.remove(selectedWord)
        submitList(list)
    }

    fun addItem(selectedWord: String) {
        val list = ArrayList(currentList)
        list.add(selectedWord)
        submitList(list)
    }

    inner class WordsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(word: String) = itemView.run {
            findViewById<TextView>(R.id.tvWord).text = word

            setOnClickListener {view->
                print("what?")
                removeItem(word)
                wa.addItem(word)
                true
            }
        }
    }
}
