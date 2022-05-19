package com.example.coursework.utilities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework.R
import com.example.coursework.network.Articles

class NewsListAdapter(private val articles: List<Articles>) : RecyclerView.Adapter<NewsListAdapter.CastomViewHolder>() {

    class CastomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.title)
        val url: TextView = itemView.findViewById(R.id.url)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastomViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return CastomViewHolder(item)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: CastomViewHolder, position: Int) {
        holder.title.text = articles[position].title
        holder.url.text = articles[position].url
    }


}