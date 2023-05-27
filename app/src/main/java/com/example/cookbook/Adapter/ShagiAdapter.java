package com.example.cookbook.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbook.R;

import java.util.List;

public class ShagiAdapter extends RecyclerView.Adapter<Shagi> {

    List<String> items;

    public ShagiAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public Shagi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_activity, parent, false);
        return new Shagi(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull Shagi holder, int position) {
        holder.textView.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class Shagi extends RecyclerView.ViewHolder{

    TextView textView;
    private ShagiAdapter adapter;

    public Shagi(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.text_shag);
        itemView.findViewById(R.id.Photo_cook_add_shag);
        itemView.findViewById(R.id.image_camera_icon_shag);
        itemView.findViewById(R.id.image_gallery_icon_shag);
        itemView.findViewById(R.id.opisanie_cook_shag);
        adapter.items.remove(getAdapterPosition());
        adapter.notifyItemRemoved(getAdapterPosition());
    }

    public Shagi linkAdapter(ShagiAdapter adapter)
    {
        this.adapter = adapter;
        return this;
    }
}
