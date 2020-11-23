package com.example.vadamar.Model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vadamar.R;
import com.example.vadamar.ui.detail;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    public List<ListItem> listItems;
    public Context context;

    public Adapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        final ListItem listItem = listItems.get(position);

        holder.textTitle.setText(listItem.getTitle());
        holder.textAuthor.setText(listItem.getAuthor());
        holder.textDate.setText(listItem.getDate());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"clicked " + listItem.getTitle(),Toast.LENGTH_SHORT).show();
                //Context context = v.getContext();
                Intent newIntent = new Intent(context, detail.class);
                context.startActivity(newIntent);
            }

        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle;
        public TextView textAuthor;
        public TextView textDate;
        public ImageView imageView;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = (TextView) itemView.findViewById(R.id.title);
            textAuthor = (TextView) itemView.findViewById(R.id.author);
            textDate = (TextView) itemView.findViewById(R.id.publishedAt);
            imageView = (ImageView) itemView.findViewById(R.id.img);
            cardView = (CardView) itemView.findViewById(R.id.card);

        }
    }
}
