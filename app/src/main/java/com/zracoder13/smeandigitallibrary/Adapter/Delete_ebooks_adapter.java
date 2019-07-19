package com.zracoder13.smeandigitallibrary.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;
import com.zracoder13.smeandigitallibrary.R;
import com.zracoder13.smeandigitallibrary.activity.Edit_ebooks_result_edit;
import com.zracoder13.smeandigitallibrary.model.Ebook_list;
import com.zracoder13.smeandigitallibrary.model.Value;
import com.zracoder13.smeandigitallibrary.network.MyService;
import com.zracoder13.smeandigitallibrary.network.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Delete_ebooks_adapter extends RecyclerView.Adapter<Delete_ebooks_adapter.delete_adapters> {
    Context context;
    public  Delete_ebooks_adapter(Context context, List<Ebook_list> ebook_lists) {
        this.context = context;
        Ebook_list = ebook_lists;
    }
    List<Ebook_list> Ebook_list;

    @NonNull
    @Override
    public delete_adapters onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View homefragmentinflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_homefragment,parent,false);
        return new Delete_ebooks_adapter.delete_adapters(homefragmentinflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final delete_adapters holder, final int position) {
        holder.judul_buku.setText(Ebook_list.get(position).judul_buku);
        holder.penulis_buku.setText(Ebook_list.get(position).penulis_buku);
        holder.genre.setText(Ebook_list.get(position).genre_buku);
        holder.terbit_buku.setText(Ebook_list.get(position).terbit_buku);
        Glide.with(context).load(Ebook_list.get(position).foto_buku).into(holder.book_cover);
        final String title_book = Ebook_list.get(position).judul_buku;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();

            }

            private void showDialog() {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title dialog
                alertDialogBuilder.setTitle("Digital Library");

                // set pesan dari dialog
                alertDialogBuilder
                        .setMessage("Ingin Menghapus "+title_book+" ?")
                        .setIcon(R.mipmap.ic_launcher)
                        .setCancelable(false)
                        .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                Call<Value> call = ServiceGenerator.INSTANCE.createService(MyService.class)
                                        .deleteeBooks(Ebook_list.get(position).kode_buku);
                                call.enqueue(new Callback<Value>() {
                                    @Override
                                    public void onResponse(Call<Value> call, Response<Value> response) {
                                        String value = response.body().value;
                                        String pesan = response.body().pesan;
                                        if (value.equals("1")) {
                                            Toast.makeText(context,pesan,Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(context, pesan, Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Value> call, Throwable t) {
                                        Toast.makeText(context, "Koneksi Bermasalah", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });

                // membuat alert dialog dari builder
                AlertDialog alertDialog = alertDialogBuilder.create();

                // menampilkan alert dialog
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Ebook_list.size();
    }

    class delete_adapters extends RecyclerView.ViewHolder {
        TextView judul_buku,penulis_buku,genre,terbit_buku;
        ImageView book_cover;
        public delete_adapters(View itemView) {
            super(itemView);
            book_cover = itemView.findViewById(R.id.book_cover_iv);
            judul_buku = itemView.findViewById(R.id.judul_buku_tv);
            penulis_buku = itemView.findViewById(R.id.penulis_buku_tv);
            genre = itemView.findViewById(R.id.genre_tv);
            terbit_buku = itemView.findViewById(R.id.tahun_terbit_tv);
        }
    }
}
