package com.example.facebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;

public class Signup extends AppCompatActivity {
    public static int flag=0;
    EditText email,pass1,pass2;
    TextView login;
    Button res;

FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email=(EditText)findViewById(R.id.e);
        pass1=(EditText)findViewById(R.id.password);
        pass2=(EditText) findViewById(R.id.password1);
        res=(Button)findViewById(R.id.button);
        login=(TextView)findViewById(R.id.login);
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

        pass1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String c = pass1.getText().toString();
                if(hasFocus && c.equalsIgnoreCase("password"))
                {
                    pass1.setText("");
                    pass1.setTextColor(WHITE);
                    pass1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                else
                {
                    if(pass1.getText().toString().isEmpty())
                    {
                        pass1.setTextColor(RED);
                        pass1.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                        pass1.setText("Password");
                    }
                }
            }
        });

        pass2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String c = pass2.getText().toString();
                if(hasFocus && c.equalsIgnoreCase("Confirm Password"))
                {
                    pass2.setText("");
                    pass2.setTextColor(WHITE);
                    pass2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                else
                {
                    if(pass2.getText().toString().isEmpty())
                    {
                        pass2.setTextColor(RED);
                        pass2.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                        pass2.setText("Confirm Password");
                    }
                }
            }
        });
    }
    public void movetologin(View view)
    {
        Intent i = new Intent(Signup.this,MainActivity.class);
        startActivity(i);
        finish();
    }

    public void register(View view)
    {
        String p = pass1.getText().toString().trim();
        String p2 = pass2.getText().toString().trim();
        String e = email.getText().toString().trim();

        if(p.equals("Password") || e.equals("Email-ID") || e.isEmpty() || p.isEmpty())
        {
            Toast.makeText(Signup.this,"Fill in the credentials with approriate Information.", Toast.LENGTH_LONG).show();
        }
        else if(!p.equals(p2))
        {
            Toast.makeText(Signup.this,"Passwords don't match.", Toast.LENGTH_LONG).show();
        }
        else
        {

            auth.createUserWithEmailAndPassword(e,p).addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {

                        Toast.makeText(Signup.this,"You are Registered.", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(Signup.this,MainActivity.class);
                        flag=1;
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Signup.this,"Unable to Create an User!!",Toast.LENGTH_LONG).show();
                    }

                }
            });


        }
    }

}