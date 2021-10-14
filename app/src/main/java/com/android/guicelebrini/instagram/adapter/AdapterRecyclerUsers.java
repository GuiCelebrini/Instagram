package com.android.guicelebrini.instagram.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.guicelebrini.instagram.R;
import com.android.guicelebrini.instagram.model.User;

import java.util.List;

public class AdapterRecyclerUsers extends RecyclerView.Adapter<AdapterRecyclerUsers.MyViewHolder> {

    private List<User> usersList;

    public AdapterRecyclerUsers(List<User> usersList){
        this.usersList = usersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterRecyclerUsers.MyViewHolder holder, int position) {
        User user = usersList.get(position);
        holder.set(user);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textUserName;

        public MyViewHolder(View itemView) {
            super(itemView);

            textUserName = itemView.findViewById(R.id.tv_user_name);

        }

        private void set(User user){
            this.textUserName.setText(user.getName());
        }
    }
}
