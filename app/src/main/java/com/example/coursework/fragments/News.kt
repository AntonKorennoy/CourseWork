package com.example.coursework.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework.databinding.FragmentNewsBinding
import com.example.coursework.network.LinksNews
import com.example.coursework.network.NetWorkGoogleNews
import com.example.coursework.network.News
import com.example.coursework.utilities.NewsListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class News : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    private lateinit var list: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = binding.listNews
        list.layoutManager = LinearLayoutManager(requireContext().applicationContext)

        NetWorkGoogleNews.getNewWork()
            .create(LinksNews::class.java)
            .getNews(size = 10)
            .enqueue(object : Callback<com.example.coursework.network.News> {

                override fun onResponse(call: Call<News>, response: Response<News>) {

                    val body = response.body() as News

                    Log.i("body",body.toString())

                    list.adapter = NewsListAdapter(body.articles)


                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.e("error", t.toString())
                }
            })
    }
}
