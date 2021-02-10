package com.antixiansoftware.travelling.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.antixiansoftware.travelling.Model.Trvcollection;
import com.antixiansoftware.travelling.R;

import java.util.List;

public class Trvcollectionsadapter extends RecyclerView.Adapter<Trvcollectionsadapter.RecyclerViewAdapter> {

    private Context context;
    private List<Trvcollection> trvcollectionList;
    private ItemClickListner itemClickListner;

    public Trvcollectionsadapter(Context context, List<Trvcollection> trvcollectionList, ItemClickListner itemClickListner) {
        this.context = context;
        this.trvcollectionList = trvcollectionList;
        this.itemClickListner = itemClickListner;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vehicals,
                parent, false);

        return new RecyclerViewAdapter(view, itemClickListner);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        Trvcollection trvcollection = trvcollectionList.get(position);
        holder.vehical_name.setText(trvcollection.getVehical_name());
        holder.seating_capacity.setText(trvcollection.getSeating_capacity());
        holder.km.setText(trvcollection.getKm());


    }

    @Override
    public int getItemCount() {
        return trvcollectionList.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView vehical_name, seating_capacity, km;
        Button car_image;
        CardView card_item;
        ItemClickListner itemClickListner;

        RecyclerViewAdapter(@NonNull View itemView, ItemClickListner itemClickListner) {
            super(itemView);
            this.itemClickListner = itemClickListner;
            card_item.setOnClickListener(this);

            vehical_name = itemView.findViewById(R.id.edtp1);
            seating_capacity = itemView.findViewById(R.id.edtp2);
            km = itemView.findViewById(R.id.edtp3);
            car_image = itemView.findViewById(R.id.button1);
            card_item = itemView.findViewById(R.id.card1);
        }

        @Override
        public void onClick(View v) {


        }
    }
    public interface ItemClickListner{
        void onItemClick(View view , int position);
    }
}
