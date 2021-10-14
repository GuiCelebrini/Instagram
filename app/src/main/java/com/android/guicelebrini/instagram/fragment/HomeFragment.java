package com.android.guicelebrini.instagram.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.guicelebrini.instagram.R;
import com.android.guicelebrini.instagram.adapter.AdapterRecyclerPosts;
import com.android.guicelebrini.instagram.helper.Preferences;
import com.android.guicelebrini.instagram.model.Post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {

    private View view;

    private RecyclerView recyclerPosts;
    private AdapterRecyclerPosts adapter;
    private List<Post> postsList = new ArrayList<>();

    private Preferences preferences;

    private FirebaseFirestore db;
    private String userId = "";

    public HomeFragment(){
        // Required empty public constructor
    }

    public HomeFragment(String firestoreId){
        this.userId = firestoreId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        findViewsById();
        preferences = new Preferences(view.getContext());

        isFromLoggedUser(userId);

        createPostsList();

        configureRecyclerPosts();

        return view;
    }

    private void findViewsById(){
        recyclerPosts = view.findViewById(R.id.recycler_posts);
    }

    private void isFromLoggedUser(String userId){
        if (userId.equals("")){
            this.userId = preferences.getUserId();
        }
    }

    private void createPostsList(){
        db = FirebaseFirestore.getInstance();

        db.collection("users").document(userId).collection("posts")
                .orderBy("createdAt", Query.Direction.DESCENDING).get()
                .addOnCompleteListener(task -> {

                    postsList.clear();

                    for (DocumentSnapshot snapshot : task.getResult()) {

                        String user = snapshot.getString("user");
                        String imageUrl = snapshot.getString("imageUrl");
                        Post post = new Post();
                        post.setUser(user);
                        post.setImageUrl(imageUrl);

                        postsList.add(post);
                    }

                    adapter.notifyDataSetChanged();
                });

    }

    private void configureRecyclerPosts(){
        adapter = new AdapterRecyclerPosts(postsList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerPosts.setLayoutManager(layoutManager);
        recyclerPosts.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayout.VERTICAL));

        recyclerPosts.setAdapter(adapter);
    }
}