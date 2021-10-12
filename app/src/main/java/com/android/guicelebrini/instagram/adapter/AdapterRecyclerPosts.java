package com.android.guicelebrini.instagram.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.guicelebrini.instagram.R;
import com.android.guicelebrini.instagram.model.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerPosts extends RecyclerView.Adapter<AdapterRecyclerPosts.MyViewHolder> {

    private List<Post> postsList = new ArrayList<>();

    public AdapterRecyclerPosts(List<Post> postsList){
        this.postsList = postsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterRecyclerPosts.MyViewHolder holder, int position) {
        Post post = postsList.get(position);
        holder.set(post);
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textUser;
        private ImageView imagePost;

        public MyViewHolder(View itemView) {
            super(itemView);
            textUser = itemView.findViewById(R.id.tv_post_user);
            imagePost = itemView.findViewById(R.id.iv_post_image);

        }

        private void set(Post post){
            this.textUser.setText(post.getUser());
            Picasso.get().load(post.getImageUrl()).into(this.imagePost);
        }
    }
}
