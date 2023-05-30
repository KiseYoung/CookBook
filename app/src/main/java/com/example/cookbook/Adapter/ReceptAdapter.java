package com.example.cookbook.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbook.Model.Recept;
import com.example.cookbook.R;
import com.example.cookbook.ReceptPage;

import java.util.List;

public class ReceptAdapter extends RecyclerView.Adapter<ReceptAdapter.ReceptViewHolder> {

    Context context;
    List<Recept> recepts;

    public ReceptAdapter(Context context, List<Recept> recepts) {
        this.context = context;
        this.recepts = recepts;
    }

    @NonNull
    @Override
    public ReceptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View receptItems = LayoutInflater.from(context).inflate(R.layout.recept_item, parent, false);
        return new ReceptAdapter.ReceptViewHolder(receptItems);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceptViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.receptBG.setBackgroundColor(Color.parseColor(recepts.get(position).getColor()));

        int imageId = context.getResources().getIdentifier(recepts.get(position).getImg(), "drawable", context.getPackageName());
        holder.sendvi4Image.setImageResource(imageId);

        holder.sendvi4Title.setText(recepts.get(position).getName());
        holder.sendvi4Opisanie.setText(recepts.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ReceptPage.class);

                intent.putExtra("receptBG", Color.parseColor(recepts.get(position).getColor()));
                intent.putExtra("sendvi4Image", imageId);
                intent.putExtra("sendvi4Title", recepts.get(position).getName());
                intent.putExtra("sendvi4Opisanie", recepts.get(position).getTitle());
                intent.putExtra("receptText", recepts.get(position).getText());

                context.startActivities(new Intent[]{intent});
            }
        });
    }

    @Override
    public int getItemCount() {
        return recepts.size();
    }

    public static final class ReceptViewHolder extends RecyclerView.ViewHolder {

        LinearLayout receptBG;
        ImageView sendvi4Image;
        TextView sendvi4Title, sendvi4Opisanie;

        public ReceptViewHolder(@NonNull View itemView) {
            super(itemView);

            receptBG = itemView.findViewById(R.id.receptBG);
            sendvi4Image = itemView.findViewById(R.id.sendvi4_image);
            sendvi4Title = itemView.findViewById(R.id.sendvi4_Title);
            sendvi4Opisanie = itemView.findViewById(R.id.opisanie_sendvi4);
        }
    }
}
