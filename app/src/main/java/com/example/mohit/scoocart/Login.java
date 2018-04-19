package com.example.mohit.scoocart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mohit on 3/1/2018.
 */

public class Login extends AppCompatActivity {
 EditText username,password;
 TextView signup;
 Button login;
 TextInputLayout lay1,lay2;
 String user,pass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        lay1=(TextInputLayout)findViewById(R.id.lay1);
        lay2=(TextInputLayout)findViewById(R.id.lay2);
        login=(Button)findViewById(R.id.login);
        signup=(TextView)findViewById(R.id.signup);
         user=lay1.getEditText().getText().toString();
         pass=lay2.getEditText().getText().toString();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                         Intent intent;
                         intent = new Intent(Login.this,navigation.class);
                Bundle bundle = new Bundle();
                bundle.putString("user_id", user);
                 Home myObj = new Home();


                 myObj.setArguments(bundle);
                         startActivity(intent);

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(Login.this,Signup.class);
                startActivity(intent2);

            }
        });

    }
}
