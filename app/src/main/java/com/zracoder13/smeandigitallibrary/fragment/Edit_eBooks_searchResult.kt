package com.zracoder13.smeandigitallibrary.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zracoder13.smeandigitallibrary.Adapter.Edit_ebooks_adapter

import com.zracoder13.smeandigitallibrary.R
import com.zracoder13.smeandigitallibrary.model.Ebook_list
import com.zracoder13.smeandigitallibrary.network.MyService
import com.zracoder13.smeandigitallibrary.network.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Edit_eBooks_searchResult : Fragment() {
    internal lateinit var recyclerView : RecyclerView
    internal lateinit var swipeRefreshLayout : SwipeRefreshLayout
    var Lib_list: List<Ebook_list>? = null
    internal lateinit var Adapter: Edit_ebooks_adapter
    var kategori_item : String = ""
    internal lateinit var manager: RecyclerView.LayoutManager
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val viewroot =  inflater.inflate(R.layout.fragment_edit_e_books_search_result, container, false)
        Toast.makeText(context,"Mencari...", Toast.LENGTH_SHORT).show()

        recyclerView = viewroot.findViewById(R.id.edit_ebooks_rv)
        manager = LinearLayoutManager(context)
        recyclerView.layoutManager = manager

        loadData()
        return viewroot
    }

    private fun loadData() {
        val judul_buku = getArguments()!!.getString("etsearch")
        val call = ServiceGenerator.createService(MyService::class.java).searcheBooks(
                judul_buku
        )
        call.enqueue(object : Callback<List<Ebook_list>> {
            override fun onFailure(call: Call<List<Ebook_list>>?, t: Throwable?) {
Toast.makeText(context,"Koneksi Bermasalah : "+t?.printStackTrace(),Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<List<Ebook_list>>?, response: Response<List<Ebook_list>>?) {
                Lib_list = response!!.body()
                Adapter = Edit_ebooks_adapter(context, Lib_list)
                recyclerView.adapter = Adapter
            }

        })
    }


}
