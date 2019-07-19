package com.zracoder13.smeandigitallibrary.fragment


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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Delete_eBooks : Fragment() {
    lateinit var dtsearch : EditText
    lateinit var btsubmitsearch : Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val viewroot = inflater.inflate(R.layout.fragment_delete_e_books, container, false)
        dtsearch = viewroot.findViewById(R.id.delete_search_et_topdeleteebooks)
        btsubmitsearch = viewroot.findViewById(R.id.delete_search_bt_topdeleteebooks)

        btsubmitsearch.setOnClickListener(View.OnClickListener {
            if (TextUtils.isEmpty(dtsearch.text.toString())) {
                Toast.makeText(context,"Kolom search harus diisi", Toast.LENGTH_SHORT).show()
            } else if  (dtsearch.text.toString().equals(" ")) {
                Toast.makeText(context, "Kolom search harus diisi", Toast.LENGTH_SHORT).show()
            } else {
                loadData()
            }

        })
        return viewroot
    }

    private fun loadData() {
        val data = Bundle()
        data.putString("etsearch",dtsearch.text.toString())
        val fragmentManager = getFragmentManager()
        val transaction = fragmentManager!!.beginTransaction()
        val delete_eBooks_result = Delete_eBooks_result()
        delete_eBooks_result.setArguments(data)
        transaction.replace(R.id.delete_ebooks_fl, delete_eBooks_result)
        transaction.commit()    }


}
