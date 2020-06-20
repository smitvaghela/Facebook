package com.example.facebook;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        auth = FirebaseAuth.getInstance();
    }
    public void out(View view)
    {
        auth.signOut();
        Toast.makeText(Dashboard.this,"Logged Out",Toast.LENGTH_LONG).show();
        Intent i = new Intent(Dashboard.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}