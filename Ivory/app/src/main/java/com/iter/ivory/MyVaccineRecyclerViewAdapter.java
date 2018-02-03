package com.iter.ivory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.iter.ivory.VaccineFragment.OnListFragmentInteractionListener;
import java.util.ArrayList;

public class MyVaccineRecyclerViewAdapter extends RecyclerView.Adapter<MyVaccineRecyclerViewAdapter.ViewHolder> {

    private final OnListFragmentInteractionListener mListener;
    private ArrayList<Vaccines> vaccinations;


    public MyVaccineRecyclerViewAdapter(ArrayList<Vaccines> vaccinations, OnListFragmentInteractionListener listener) {
        this.vaccinations = vaccinations;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_vaccine, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //holder.mContentView.setText(mValues.get(position).content);
        holder.nameView.setText(vaccinations.get(position).getVaccineName());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return vaccinations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nameView;
        public final TextView lastView;
        public final ImageView vacicon;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            vacicon = view.findViewById(R.id.vacicon);
            nameView = view.findViewById(R.id.name);
            lastView = view.findViewById(R.id.lastvac);
        }
    }
}
