package com.zracoder13.smeandigitallibrary.fragment


import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.zracoder13.smeandigitallibrary.R
import com.zracoder13.smeandigitallibrary.model.Value
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
class Insert_eBooks : Fragment() {
lateinit var isbn : EditText
lateinit var judul_ebook : EditText
lateinit var penulis : EditText
lateinit var penerbit : EditText
lateinit var terbit : EditText
lateinit var jenis : EditText
lateinit var coverpdf : EditText
lateinit var linkpdf : EditText
lateinit var btnins : Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val viewroot = inflater.inflate(R.layout.fragment_insert_e_books, container, false)
        //declare input ebook
        isbn = viewroot.findViewById(R.id.isbn_ins_ebooks)
        judul_ebook = viewroot.findViewById(R.id.judul_buku_Ins_et_ebooks)
        penulis = viewroot.findViewById(R.id.penulis_ins_ebooks)
        penerbit = viewroot.findViewById(R.id.penerbit_ins_et_ebooks)
        terbit = viewroot.findViewById(R.id.terbit_ins_ebooks)
        jenis = viewroot.findViewById(R.id.jenis_ins_et_ebooks)
        coverpdf = viewroot.findViewById(R.id.cover_ins_et_ebooks)
        linkpdf = viewroot.findViewById(R.id.link_ins_et_ebooks)

        btnins = viewroot.findViewById(R.id.btn_Ins_bt)

        //button action
        btnins.setOnClickListener(View.OnClickListener {
            if (TextUtils.isEmpty(isbn.text.toString()))
            {isbn.setError("ISBN Must be Filled!")}
            else if (TextUtils.isEmpty(judul_ebook.text.toString()))
            {judul_ebook.setError("Judul Must be Filled!")}
            else if (TextUtils.isEmpty(penulis.text.toString()))
            {penulis.setError("Penulis Must be Filled!")}
            else if (TextUtils.isEmpty(penerbit.text.toString()))
            {penerbit.setError("Penerbit Must be Filled!")}
            else if (TextUtils.isEmpty(terbit.text.toString()))
            {terbit.setError("Terbit Must be Filled!")}
            else if (TextUtils.isEmpty(jenis.text.toString()))
            {jenis.setError("Jenis Must be Filled!")}
            else if (TextUtils.isEmpty(coverpdf.text.toString()))
            {coverpdf.setError("Cover Must be Filled!")}
            else if (TextUtils.isEmpty(linkpdf.text.toString()))
            {linkpdf.setError("Link PDF Must be Filled!")}
            else {
                insertData()
            }

        })


        return viewroot
    }

    private fun insertData() {
        val alertDialogBuilder = AlertDialog.Builder(context)

        // set title dialog
        alertDialogBuilder.setTitle("Insert eBooks")

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Data sudah benar? , Klik simpan untuk menyimpanya")
                .setCancelable(false)
                .setPositiveButton("Ya") { dialog, id ->

                    val call = ServiceGenerator.createService(MyService::class.java).insertEbooks(
                            isbn.text.toString(),
                            judul_ebook.text.toString(),
                            penulis.text.toString(),
                            penerbit.text.toString(),
                            jenis.text.toString(),
                            terbit.text.toString(),
                            linkpdf.text.toString(),
                            coverpdf.text.toString()
                    )
                    call.enqueue(object : Callback<Value> {
                        override fun onFailure(call: Call<Value>?, t: Throwable?) {
                            Toast.makeText(context,"Koneksi Bermasalah",Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(call: Call<Value>?, response: Response<Value>?) {
                            val value = response!!.body()!!.value
                            val pesan = response!!.body()!!.result
                            if (value == "1") {
                                Toast.makeText(context,pesan,Toast.LENGTH_SHORT).show()
                            } else Toast.makeText(context,pesan,Toast.LENGTH_SHORT).show()

                        }

                    })

                }
                .setNegativeButton("Tidak") { dialog, id -> dialog.cancel() }

        // membuat alert dialog dari builder
        val alertDialog = alertDialogBuilder.create()

        // menampilkan alert dialog
        alertDialog.show()
    }


}
