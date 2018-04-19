package com.example.mohit.scoocart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by mohit on 3/1/2018.
 */

public class Signup extends AppCompatActivity {
    EditText firstname,lastname,email,phone,password;
    Button signupbutton;
    Spinner spin;
    RadioButton male,female;
    String[] parent={"Parent","Guest"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        firstname=(EditText)findViewById(R.id.firstname);
        lastname=(EditText)findViewById(R.id.lastname);
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phone);
        password=(EditText)findViewById(R.id.password);
        spin=(Spinner)findViewById(R.id.spin);

        male=(RadioButton)findViewById(R.id.radioButton);
        female=(RadioButton)findViewById(R.id.radioButton2);
        signupbutton=(Button)findViewById(R.id.signupbutton);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,parent);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);


signupbutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(Signup.this,"Registered",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(Signup.this,Login.class);
        startActivity(intent);
    }
});



    }
}
