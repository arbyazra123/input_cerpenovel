package com.zracoder13.smeandigitallibrary.activity

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.zracoder13.smeandigitallibrary.R
import com.zracoder13.smeandigitallibrary.model.Ebook_list
import com.zracoder13.smeandigitallibrary.model.Value
import com.zracoder13.smeandigitallibrary.network.MyService
import com.zracoder13.smeandigitallibrary.network.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Edit_ebooks_result_edit : AppCompatActivity() {
    lateinit var isbn : EditText
    lateinit var judul_ebook : EditText
    lateinit var penulis : EditText
    lateinit var penerbit : EditText
    lateinit var terbit : EditText
    lateinit var jenis : EditText
    lateinit var coverpdf : EditText
    lateinit var linkpdf : EditText
    lateinit var btnins : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_ebooks_result_edit)

        //declare input ebook
        isbn = findViewById(R.id.isbn_ins_ebooks_edit)
        judul_ebook = findViewById(R.id.judul_buku_Ins_et_ebooks_edit)
        penulis = findViewById(R.id.penulis_ins_ebooks_edit)
        penerbit = findViewById(R.id.penerbit_ins_et_ebooks_edit)
        terbit = findViewById(R.id.terbit_ins_ebooks_edit)
        jenis = findViewById(R.id.jenis_ins_et_ebooks_edit)
        coverpdf = findViewById(R.id.cover_ins_et_ebooks_edit)
        linkpdf = findViewById(R.id.link_ins_et_ebooks_edit)

        btnins = findViewById(R.id.btn_Ins_bt_edit)


        setTextFunc()

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
    }

    private fun insertData() {
        val alertDialogBuilder = AlertDialog.Builder(this@Edit_ebooks_result_edit)

        // set title dialog
        alertDialogBuilder.setTitle("Insert eBooks")

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Data sudah benar? , Klik simpan untuk mengubahnya")
                .setCancelable(false)
                .setPositiveButton("Ya") { dialog, id ->

                    val call = ServiceGenerator.createService(MyService::class.java).editeBooks(
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
                            Toast.makeText(this@Edit_ebooks_result_edit,"Koneksi Bermasalah", Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(call: Call<Value>?, response: Response<Value>?) {
                            val value = response!!.body()!!.value
                            val pesan = response!!.body()!!.result
                            if (value == "1") {
                                Toast.makeText(this@Edit_ebooks_result_edit,pesan, Toast.LENGTH_LONG).show()
                                onBackPressed()
                            } else Toast.makeText(this@Edit_ebooks_result_edit,pesan, Toast.LENGTH_SHORT).show()

                        }

                    })

                }
                .setNegativeButton("Tidak") { dialog, id -> dialog.cancel() }

        // membuat alert dialog dari builder
        val alertDialog = alertDialogBuilder.create()

        // menampilkan alert dialog
        alertDialog.show()    }

    private fun setTextFunc() {
        val detailEdit : Ebook_list
        detailEdit = GsonBuilder().create().
                fromJson<Ebook_list>(intent.
                        getStringExtra("intent_BukuEdit"), Ebook_list::class.java!!)
        isbn.setText(detailEdit.kode_buku)
        judul_ebook.setText(detailEdit.judul_buku)
        penulis.setText(detailEdit.penulis_buku)
        jenis.setText(detailEdit.genre_buku)
        coverpdf.setText(detailEdit.foto_buku)
        linkpdf.setText(detailEdit.link_buku)
        penerbit.setText(detailEdit.penerbit_buku)
        terbit.setText(detailEdit.terbit_buku)

    }
}
