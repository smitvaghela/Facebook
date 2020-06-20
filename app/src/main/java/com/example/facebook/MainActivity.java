package com.example.facebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.example.facebook.Signup;
import com.google.firebase.auth.FirebaseUser;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity {

    EditText email,pass;
    TextView signup;
    Button bt;
    FirebaseAuth auth;
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(Signup.flag==1)
        {
            currentUser = null;
            Signup.flag=0;
        }
        if(currentUser != null)
        {

            Intent i = new Intent(MainActivity.this,Dashboard.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.Password);
        signup=(TextView)findViewById(R.id.sign);
        bt=(Button)findViewById(R.id.login);
        auth = FirebaseAuth.getInstance();
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String c = email.getText().toString();
                if(hasFocus && c.equalsIgnoreCase("email-id"))
                {
                    email.setText("");
                    email.setTextColor(WHITE);
                }
                else
                {
                    if(email.getText().toString().isEmpty())
                    {
                        email.setTextColor(RED);
                        email.setText("Email-ID");
                    }
                }
            }
        });

        pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String c = pass.getText().toString();
                if(hasFocus && c.equalsIgnoreCase("password"))
                {
                    pass.setText("");
                    pass.setTextColor(WHITE);
                    pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                else
                    {
                        if(pass.getText().toString().isEmpty())
                        {
                            pass.setTextColor(RED);
                            pass.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                            pass.setText("Password");
                        }
                    }
            }
        });

    }
    public void loginUse(View view)
    {
        String p = pass.getText().toString().trim();
        String e = email.getText().toString().trim();

        if(p.equals("Password") || e.equals("Email-ID") || e.isEmpty() || p.isEmpty())
        {
            Toast.makeText(MainActivity.this,"Fill in the credentials with approriate Information.", Toast.LENGTH_LONG).show();
        }
        else
        {

            auth.signInWithEmailAndPassword(e,p).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this,"Logged in!",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(MainActivity.this,Dashboard.class);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Unable to Login please try again.",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void movetosignup(View view)
    {
        Intent i = new Intent(MainActivity.this,Signup.class);
        startActivity(i);
        finish();
    }

}