package com.example.exercise3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private ArrayList<AdapterData> mDataset;

    public Adapter(ArrayList<AdapterData> myDataset) {
        mDataset = myDataset;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView teams;
        public TextView score;
        public String home;
        public String guest;
        public String homescore;
        public String guestscore;
        public MyViewHolder(View v) {
            super(v);
            teams = (TextView) itemView.findViewById(R.id.teamsTextView);
            score = (TextView) itemView.findViewById(R.id.scoreTextView);
        }
    }

    @Override
    public Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View vh = inflater.inflate(R.layout.adapter_view, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(vh);
        vh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), GameActivity.class);
                intent.putExtra("home", mDataset.get(viewHolder.getAdapterPosition()).getHome());
                intent.putExtra("homescore",mDataset.get(viewHolder.getAdapterPosition()).getHomescore());
                intent.putExtra("guest",mDataset.get(viewHolder.getAdapterPosition()).getGuest());
                intent.putExtra("guestscore",mDataset.get(viewHolder.getAdapterPosition()).getGuestscore());
                view.getContext().startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.teams.setText(mDataset.get(position).getTeams());
        holder.score.setText(mDataset.get(position).getScore());
        holder.home = mDataset.get(position).getHome();
        holder.homescore = mDataset.get(position).getHomescore();
        holder.guest = mDataset.get(position).getGuest();
        holder.guestscore = mDataset.get(position).getGuestscore();
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}