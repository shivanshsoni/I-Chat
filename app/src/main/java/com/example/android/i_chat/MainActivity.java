package com.example.android.i_chat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,pass,emailid;
    Button login,register;
    String name,password,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.editName);
        pass = (EditText)findViewById(R.id.editpassword);
        emailid=(EditText)findViewById(R.id.editemail);
        login=(Button)findViewById(R.id.submit);
        register=(Button)findViewById(R.id.login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = username.getText().toString();
                password = pass.getText().toString();

                if(name.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "username cannot be blank", Toast.LENGTH_SHORT).show();
                }
                else if(password.isEmpty() || password.length()<6)
                {
                    Toast.makeText(MainActivity.this, "password length can't be less than 6", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    final ProgressDialog pb = new ProgressDialog(MainActivity.this);
                    pb.setMessage("Loading...");
                }

            }
        });
    }
}
