package com.zracoder13.smeandigitallibrary.network

import com.zracoder13.smeandigitallibrary.model.*

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyService {

    @FormUrlEncoded
    @POST("ebooks/insert.php")
    fun insertEbooks(@Field("kode_buku") kode_buku: String,
              @Field("judul_buku") judul_buku: String,
              @Field("penulis_buku") penulis_buku: String,
              @Field("penerbit_buku") penerbit_buku: String,
              @Field("genre_buku") genre_buku: String,
              @Field("terbit_buku") terbit_buku: String,
              @Field("link_buku") link_buku: String,
              @Field("foto_buku") foto_buku: String): Call<Value>


    @FormUrlEncoded
    @POST("ebooks/delete.php")
    fun deleteeBooks(@Field("kode_buku") kode_buku: String): Call<Value>

    @FormUrlEncoded
    @POST("ebooks/edit.php")
    fun editeBooks(@Field("kode_buku") kode_buku: String,
                  @Field("judul_buku") judul_buku: String,
                  @Field("penulis_buku") penulis_buku: String,
                  @Field("penerbit_buku") penerbit_buku: String,
                  @Field("genre_buku") genre_buku: String,
                  @Field("terbit_buku") terbit_buku: String,
                  @Field("link_buku") link_buku: String,
                  @Field("foto_buku") foto_buku: String): Call<Value>


    @FormUrlEncoded
    @POST("ebooks/search.php")
    fun searcheBooks(@Field("judul_buku") judul_buku : String) : Call<List<Ebook_list>>






}

