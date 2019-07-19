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
class Edit_eBooks : Fragment() {
    lateinit var etsearch : EditText
    lateinit var btnsearchsubmit : Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val viewroot =  inflater.inflate(R.layout.fragment_edit_e_books, container, false)
        etsearch = viewroot.findViewById(R.id.edit_search_et_top_ebooks)
        btnsearchsubmit = viewroot.findViewById(R.id.edit_search_bt_top_ebooks)

        btnsearchsubmit.setOnClickListener(View.OnClickListener {
            if (TextUtils.isEmpty(etsearch.text.toString())) {
                Toast.makeText(context,"Kolom search harus diisi", Toast.LENGTH_SHORT).show()
            } else if  (etsearch.text.toString().equals(" ")) {
                Toast.makeText(context, "Kolom search harus diisi", Toast.LENGTH_SHORT).show()
            } else {
                loadData()
            }

        })
        return viewroot
    }

    private fun loadData() {
        val data = Bundle()
        data.putString("etsearch",etsearch.text.toString())
        val fragmentManager = getFragmentManager()
        val transaction = fragmentManager!!.beginTransaction()
        val edit_ebooks_result = Edit_eBooks_searchResult()
        edit_ebooks_result.setArguments(data)
        transaction.replace(R.id.edit_ebooks_fl, edit_ebooks_result)
        transaction.commit()
    }


}
