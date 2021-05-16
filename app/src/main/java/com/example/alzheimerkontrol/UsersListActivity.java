package com.example.alzheimerkontrol;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class UsersListActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<String> userUserNameFromFb;
    ArrayList<String> userUserLastNameFromFB;
    ArrayList<String> userUserAgeFromFB;
    UsersRecyclerAdapter usersRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        userUserNameFromFb = new ArrayList<>();
        userUserLastNameFromFB = new ArrayList<>();
        userUserAgeFromFB = new ArrayList<>();

        firebaseFirestore = FirebaseFirestore.getInstance();
        getDataUserFromFireStore();

        RecyclerView recyclerView = findViewById(R.id.usersList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersRecyclerAdapter = new UsersRecyclerAdapter(userUserNameFromFb, userUserLastNameFromFB, userUserAgeFromFB);
        recyclerView.setAdapter(usersRecyclerAdapter);
    }

    public void getDataUserFromFireStore(){

        CollectionReference collectionReference = firebaseFirestore.collection("Users");
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null){
                    Toast.makeText(UsersListActivity.this,error.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }
                if (value != null){
                    for (DocumentSnapshot snapshot : value.getDocuments()){
                        System.out.println("FireStore Users var");
                        Map<String,Object> dataUser = snapshot.getData();
                        String Username = (String) dataUser.get("KullaniciAdi");
                        String UserlastName = (String) dataUser.get("KullaniciSoyadi");
                        String UserAge = (String) dataUser.get("KullaniciYasi");

                        userUserNameFromFb.add(Username);
                        userUserLastNameFromFB.add(UserlastName);
                        userUserAgeFromFB.add(UserAge);
                        usersRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }


}