package com.example.exercise4;

/* Based on https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private JSONArray mData;
    private LayoutInflater mInflater;

    Adapter(Context context, JSONArray data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JSONObject object = null;
        try {
            object = mData.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            holder.idTextView.setText(object.getString("id"));
            holder.nameTextView.setText(object.getString("name"));
            holder.addressTextView.setText(object.getString("address"));
            holder.ipTextView.setText(object.getString("ip"));
            holder.portTextView.setText(object.getString("port"));
        } catch (JSONException e) {
            holder.idTextView.setText("ERROR");
            holder.nameTextView.setText("ERROR");
            holder.addressTextView.setText("ERROR");
            holder.ipTextView.setText("ERROR");
            holder.portTextView.setText("ERROR");
        }
    }

    @Override
    public int getItemCount() {
        return mData.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView;
        TextView nameTextView;
        TextView addressTextView;
        TextView ipTextView;
        TextView portTextView;

        ViewHolder(View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.idTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            ipTextView = itemView.findViewById(R.id.ipTextView);
            portTextView = itemView.findViewById(R.id.portTextView);
        }
    }
}