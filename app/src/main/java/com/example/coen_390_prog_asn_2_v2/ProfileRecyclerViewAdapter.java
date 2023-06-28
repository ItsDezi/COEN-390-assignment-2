package com.example.coen_390_prog_asn_2_v2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coen_390_prog_asn_2_v2.database.entity.Profile;

import java.util.List;

public class ProfileRecyclerViewAdapter extends RecyclerView.Adapter<ProfileRecyclerViewAdapter.ViewHolder> {
    private List<Profile> localDataSet;
    public boolean showByID;

    public ProfileRecyclerViewAdapter(List<Profile> localDataSet) {
        if(showByID == false) {
            localDataSet = sort_names(localDataSet);
        }
        else {
            localDataSet = sort_id_numbers(localDataSet);
        }
        this.localDataSet = localDataSet;
        this.showByID = false;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView firstName;
        private TextView lastName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.first_name);
            lastName = itemView.findViewById(R.id.last_name);
        }

        public TextView getFirstName() {
            return firstName;
        }
        public TextView getLastName() {
            return lastName;
        }
    }
    @NonNull
    @Override
    public ProfileRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileRecyclerViewAdapter.ViewHolder holder, int position) {
        if(showByID == false) {
            localDataSet = sort_names(localDataSet);
            holder.getFirstName().setText(localDataSet.get(position).first_name);
            holder.getLastName().setText(position + 1 + ". " + localDataSet.get(position).last_name + ", ");
        }
        else {
            localDataSet = sort_id_numbers(localDataSet);
            holder.getLastName().setText(position+1 + ". " + localDataSet.get(position).ProfileKey);
            holder.getFirstName().setText("");

        }
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }


    List<Profile> sort_names(List<Profile> profiles)
    {
        Profile temp;
        for (int i = 0; i < profiles.size(); i++) {
            for (int j = i + 1; j < profiles.size(); j++) {
                if (profiles.get(i).last_name.toLowerCase().compareTo(profiles.get(j).last_name.toLowerCase()) > 0) {
                    temp = profiles.get(i);
                    profiles.set(i,profiles.get(j));
                    profiles.set(j,temp);
                } else if (profiles.get(i).last_name.toLowerCase().compareTo(profiles.get(j).last_name.toLowerCase()) == 0) {
                    if(profiles.get(i).first_name.toLowerCase().compareTo(profiles.get(j).first_name.toLowerCase()) > 0)
                    {
                        temp = profiles.get(i);
                        profiles.set(i,profiles.get(j));
                        profiles.set(j,temp);
                    }

                }

            }
        }
        return profiles;
    }

    List<Profile> sort_id_numbers(List<Profile> profiles)
    {
        Profile temp;
        for(int i = 0; i < profiles.size(); i++)
        {
            for(int j = i+ 1; j < profiles.size(); j++)
            {
                if (profiles.get(i).ProfileKey > profiles.get(j).ProfileKey) {
                    temp = profiles.get(i);
                    profiles.set(i, profiles.get(j));
                    profiles.set(j, temp);
                }            }
        }
        return profiles;
    }
}
