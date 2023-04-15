package com.example.android.doctorishere;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class requestAdapter extends RecyclerView.Adapter<requestAdapter.requestViewHolder> {

    Context context;
    ArrayList<request> requestArrayList;

    public requestAdapter(Context context, ArrayList<request> requestArrayList) {
        this.context = context;
        this.requestArrayList = requestArrayList;
    }

    @Override
    public requestAdapter.requestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new requestViewHolder(v);
    }

    @Override
    public void onBindViewHolder(requestAdapter.requestViewHolder holder, int position) {

        request Request = requestArrayList.get(position);

        holder.specialityName.setText(Request.speciality);
        holder.timeOfRequest.setText(Request.requestTime);
        holder.patientLocation.setText(Request.latitude);

    }

    @Override
    public int getItemCount() {
        return requestArrayList.size();
    }

    public static class requestViewHolder extends RecyclerView.ViewHolder {

        TextView specialityName, timeOfRequest, patientLocation;

        public requestViewHolder(View itemView) {

            super(itemView);

            specialityName = itemView.findViewById(R.id.specName);
            timeOfRequest = itemView.findViewById(R.id.timeReq);
            patientLocation = itemView.findViewById(R.id.patLocation);

        }
    }
}
