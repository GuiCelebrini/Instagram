package com.android.guicelebrini.instagram.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.guicelebrini.instagram.R;
import com.android.guicelebrini.instagram.adapter.AdapterRecyclerPosts;
import com.android.guicelebrini.instagram.model.Post;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private View view;

    private RecyclerView recyclerPosts;
    private AdapterRecyclerPosts adapter;
    private List<Post> postsList = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        findViewsById();

        createPostsList();

        configureRecyclerPosts();

        return view;
    }

    private void findViewsById(){
        recyclerPosts = view.findViewById(R.id.recycler_posts);
    }

    private void createPostsList(){
        postsList.add(new Post("Guilherme", "https://img.ibxk.com.br/2020/12/11/11133657574178.jpg"));
        postsList.add(new Post("Luna", "https://i.pinimg.com/originals/91/9a/83/919a83ef60c4c733adf44f5f4a7db6d0.png"));
    }

    private void configureRecyclerPosts(){
        adapter = new AdapterRecyclerPosts(postsList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerPosts.setLayoutManager(layoutManager);
        recyclerPosts.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayout.VERTICAL));

        recyclerPosts.setAdapter(adapter);
    }
}