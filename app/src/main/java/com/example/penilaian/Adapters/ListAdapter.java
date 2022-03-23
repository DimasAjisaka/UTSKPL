package com.example.penilaian.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.penilaian.R;
import com.example.penilaian.models.MahasiswaViewModel;
import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.listViewHolder> {

    ArrayList<MahasiswaViewModel> list;
    public ListAdapter(ArrayList<MahasiswaViewModel> list) {
        this.list =list;
    }

    @NonNull
    @Override
    public ListAdapter.listViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_view,parent,false);
        return new listViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.listViewHolder holder, int position) {
        holder.nama.setText(list.get(position).getNama());
        holder.nim.setText(String.valueOf(list.get(position).getNim()));
        holder.mk.setText(list.get(position).getMk());
        String score = (removeZeroAfterDot(String.valueOf(list.get(position).getNumberScore()))+" ("+list.get(position).getCharScore()+")");
        //String score = (removeZeroAfterDot(String.valueOf(Math.round(list.get(position).getNumberScore())))+" ("+list.get(position).getCharScore()+")");
        holder.nilai.setText(score);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class listViewHolder extends RecyclerView.ViewHolder {
        TextView nama,nim,mk,nilai;
        public listViewHolder(@NonNull View itemView) {
            super(itemView);

            //get id dari layout
            nama = itemView.findViewById(R.id.getNama);
            nim = itemView.findViewById(R.id.getNim);
            mk = itemView.findViewById(R.id.getMk);
            nilai = itemView.findViewById(R.id.getNilai);
        }
    }

    private String removeZeroAfterDot(String value) {
        String result = value;
        if (value.contains(".0")) {
            result = value.substring(0,result.length()-2);
        }
        return result;
    }
}
