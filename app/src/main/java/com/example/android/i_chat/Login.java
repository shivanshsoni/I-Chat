package com.example.android.i_chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by shivansh on 18/3/18.
 */

public class Login extends AppCompatActivity {

    EditText username,password;
    Button submit,register;
    String name,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = (EditText)findViewById(R.id.loginemail);
        password = (EditText)findViewById(R.id.loginpassword);
        submit = (Button)findViewById(R.id.submitlogin);
        register = (Button)findViewById(R.id.registerlogin);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this , MainActivity.class));
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = username.getText().toString();
                pass = password.getText().toString();

                if(name.isEmpty() || name.length() < 5) {
                    username.setError("username doesn't exist");
                }
                else{

                }
            }
        });

    }
}
