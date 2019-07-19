package com.zracoder13.smeandigitallibrary.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import com.zracoder13.smeandigitallibrary.Adapter.ViewPagerAdapter
import com.zracoder13.smeandigitallibrary.R
import com.zracoder13.smeandigitallibrary.fragment.*

class Ebooks_setting : AppCompatActivity() {

    private lateinit var bottomNavigationView : BottomNavigationView
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ebooks_setting)
        bottomNavigationView = findViewById(R.id.books_setting_bnv2)
        viewPager = findViewById(R.id.viewPager_books_set2)
        setupPager(viewPager)
        bottomNavigationView.setOnNavigationItemSelectedListener( { item ->
            when (item.itemId) {
                R.id.nav_menu_ins_ebook -> viewPager.setCurrentItem(0)
                R.id.nav_menu_edit_ebook -> viewPager.setCurrentItem(1)
                R.id.nav_menu_delete_ebook -> viewPager.setCurrentItem(2)
            }
            false
        })
    }
    private fun setupPager(viewPager: ViewPager?) {
        val adapter_pager = ViewPagerAdapter(supportFragmentManager)
        adapter_pager.addFragment(Insert_eBooks(),"Insert_eBooks")
        adapter_pager.addFragment(Edit_eBooks(),"Edit_eBooks")
        adapter_pager.addFragment(Delete_eBooks(),"Delete_eBooks")
        viewPager!!.adapter = adapter_pager
    }
}
