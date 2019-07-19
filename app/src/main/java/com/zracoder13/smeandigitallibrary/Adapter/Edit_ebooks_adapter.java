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

import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;
import com.zracoder13.smeandigitallibrary.R;
import com.zracoder13.smeandigitallibrary.activity.Edit_ebooks_result_edit;
import com.zracoder13.smeandigitallibrary.model.Ebook_list;

import java.util.List;

public class Edit_ebooks_adapter extends RecyclerView.Adapter<Edit_ebooks_adapter.ebooks_adapters> {
    Context context;
    public Edit_ebooks_adapter (Context context, List<Ebook_list> ebook_list) {
        this.context = context;
        Ebook_list = ebook_list;
    }

    List<Ebook_list> Ebook_list;

    @NonNull
    @Override
    public ebooks_adapters onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View homefragmentinflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_homefragment,parent,false);
        return new Edit_ebooks_adapter.ebooks_adapters(homefragmentinflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ebooks_adapters holder, final int position) {
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
                        .setMessage("Ingin Mengedit "+title_book+" ?")
                        .setIcon(R.mipmap.ic_launcher)
                        .setCancelable(false)
                        .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                Ebook_list data = Ebook_list.get(position);
                                Intent i = new Intent(holder.itemView.getContext(), Edit_ebooks_result_edit.class);
                                i.putExtra("intent_BukuEdit", new GsonBuilder().create().toJson(data));
                                holder.itemView.getContext().startActivity(i);
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


    class ebooks_adapters extends RecyclerView.ViewHolder {
        TextView judul_buku,penulis_buku,genre,terbit_buku;
        ImageView book_cover;
        public ebooks_adapters(View itemView) {
            super(itemView);
            book_cover = itemView.findViewById(R.id.book_cover_iv);
            judul_buku = itemView.findViewById(R.id.judul_buku_tv);
            penulis_buku = itemView.findViewById(R.id.penulis_buku_tv);
            genre = itemView.findViewById(R.id.genre_tv);
            terbit_buku = itemView.findViewById(R.id.tahun_terbit_tv);
        }
    }
}
