package com.android.guicelebrini.instagram.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.guicelebrini.instagram.R;
import com.android.guicelebrini.instagram.adapter.AdapterRecyclerUsers;
import com.android.guicelebrini.instagram.helper.Base64Custom;
import com.android.guicelebrini.instagram.helper.Preferences;
import com.android.guicelebrini.instagram.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class UsersFragment extends Fragment {

    private View view;

    private RecyclerView recyclerUsers;
    private AdapterRecyclerUsers adapter;
    private List<User> usersList = new ArrayList<>();

    private FirebaseFirestore db;

    private Preferences preferences;
    private String loggedUserEmail;

    public UsersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_users, container, false);

        getInfosFromPreferences();

        findViewsById();

        createUsersList();

        configureRecyclerUsers();

        return view;
    }

    private void getInfosFromPreferences(){
        preferences = new Preferences(view.getContext());
        String firebaseId = preferences.getUserId();

        loggedUserEmail = Base64Custom.decode(firebaseId);
    }

    private void findViewsById(){
        recyclerUsers = view.findViewById(R.id.recycler_users);
    }

    private void createUsersList(){
        db = FirebaseFirestore.getInstance();

        db.collection("users").whereNotEqualTo("email", loggedUserEmail)
                .get().addOnCompleteListener(task -> {

                    usersList.clear();

                    for (DocumentSnapshot snapshot : task.getResult()) {
                        User user = snapshot.toObject(User.class);
                        usersList.add(user);
                    }

                    adapter.notifyDataSetChanged();

                });

    }

    private void configureRecyclerUsers(){

        adapter = new AdapterRecyclerUsers(usersList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        recyclerUsers.setLayoutManager(layoutManager);
        recyclerUsers.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayout.VERTICAL));

        recyclerUsers.setAdapter(adapter);

    }
}