package app.agus.data.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.agus.data.R;
import app.agus.data.model.Kendaraan;

/**
 * Created by ghost on 30/11/17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.RecyclerViewHolder>{
    private Context context;
    private List<Kendaraan> itemList;

    public HomeAdapter(Context context, List<Kendaraan> kendaraanList) {
        this.context = context;
        this.itemList = kendaraanList;
    }

    @Override
    public HomeAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kendaraan,parent,false);
        HomeAdapter.RecyclerViewHolder holder = new HomeAdapter.RecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Kendaraan kendaraan = itemList.get(position);
        holder.nama.setText("Nama : " + kendaraan.getNama());
        holder.no_polisi.setText("No Polisi : " + kendaraan.getNo_polisi());
        holder.no_hp.setText("No HP : " + kendaraan.getNo_telepon());
        holder.alamat.setText("Alamat : " + kendaraan.getAlamat());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView nama, no_polisi, no_hp, alamat;
        RecyclerViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            no_polisi = itemView.findViewById(R.id.no_polisi);
            no_hp = itemView.findViewById(R.id.no_hp);
            alamat = itemView.findViewById(R.id.alamat);
        }
    }
}
