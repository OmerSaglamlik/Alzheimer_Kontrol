package com.example.alzheimerkontrol;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class FeedActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<String> userNameFromFb;
    ArrayList<String> userLastNameFromFB;
    ArrayList<String> userImageFromFB;
    ArrayList<String> userBirthDateFromFB;
    ArrayList<String> userInfoFromFB;
    ArrayList<String> userCityFromFB;
    ArrayList<String> userFamilyFromFB;
    FeedRecyclerAdapter feedRecyclerAdapter;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.upload){
            Intent intentToUpload = new Intent(FeedActivity.this,UploadActivity.class);
            startActivity(intentToUpload);
            System.out.println("Upload");
        } else if (item.getItemId()==R.id.signout){
            firebaseAuth.signOut();
            Intent intentSignOut = new Intent(FeedActivity.this, SignUpActivity.class);
            startActivity(intentSignOut);
            System.out.println("SignOut");
            finish();
        }
        else if (item.getItemId()==R.id.Harita){
            Intent intentToMaps = new Intent(FeedActivity.this, MapsActivity.class);
            startActivity(intentToMaps);
        }
        else if (item.getItemId()==R.id.Kullanici){
            Intent intentToMaps = new Intent(FeedActivity.this, UsersListActivity.class);
            startActivity(intentToMaps);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        System.out.println("onCreate");
        userBirthDateFromFB = new ArrayList<>();
        userInfoFromFB = new ArrayList<>();
        userCityFromFB = new ArrayList<>();
        userFamilyFromFB = new ArrayList<>();
        userImageFromFB = new ArrayList<>();
        userLastNameFromFB = new ArrayList<>();
        userNameFromFb = new ArrayList<>();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        getDataFromFireStore();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        feedRecyclerAdapter = new FeedRecyclerAdapter(userBirthDateFromFB, userInfoFromFB, userCityFromFB, userFamilyFromFB, userNameFromFb, userLastNameFromFB, userImageFromFB);
        recyclerView.setAdapter(feedRecyclerAdapter);
    }


    public void getDataFromFireStore(){
        System.out.println("FireStore");
        CollectionReference collectionReference = firebaseFirestore.collection("Post");
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null){
                    Toast.makeText(FeedActivity.this,error.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }
                if (value != null){
                    for (DocumentSnapshot snapshot : value.getDocuments()){
                        System.out.println("FireStore veri var");
                        Map<String,Object> data = snapshot.getData();
                        String name = (String) data.get("isim");
                        String lastName = (String) data.get("soyisim");
                        String downloadUrl = (String) data.get("downloadurl");
                        String birthDateData = (String) data.get("birthdate");
                        String infoData = (String) data.get("info");
                        String familyData = (String) data.get("familymember");
                        String cityData = (String) data.get("city");
                        userImageFromFB.add(downloadUrl);
                        userLastNameFromFB.add(lastName);
                        userNameFromFb.add(name);
                        userBirthDateFromFB.add(birthDateData);
                        userCityFromFB.add(cityData);
                        userInfoFromFB.add(infoData);
                        userFamilyFromFB.add(familyData);
                        feedRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }


}