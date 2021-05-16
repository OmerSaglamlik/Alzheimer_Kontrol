package com.example.alzheimerkontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserActivty extends AppCompatActivity {
    EditText KullaniciIsim, KullaniciSoyisim, KullaniciYas;
    Button Kaydet;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseFirestore= FirebaseFirestore.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_activty);
        KullaniciIsim = findViewById(R.id.KullaniciAdi);
        KullaniciSoyisim=findViewById(R.id.KullaniciSoyAdi);
        KullaniciYas=findViewById(R.id.KullaniciYas);
        Kaydet = findViewById(R.id.KullaniciButtonKaydet);

        Kaydet.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                firebaseAuth = FirebaseAuth.getInstance();
                String userName = KullaniciIsim.getText().toString();
                String userLastName= KullaniciSoyisim.getText().toString();
                String userAge = KullaniciYas.getText().toString();

                Map<String, Object> postUserData = new HashMap<>();
                postUserData.put("KullaniciAdi", userName);
                postUserData.put("KullaniciSoyadi", userLastName);
                postUserData.put("KullaniciYasi", userAge);

                firebaseFirestore.collection("Users").add(postUserData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Intent intent =new Intent(UserActivty.this, FeedActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserActivty.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }


}