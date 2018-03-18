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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    /*
        Created by Shivansh Soni on 17th March
     */

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
        login=(Button)findViewById(R.id.login);
        register=(Button)findViewById(R.id.submit);

        Firebase.setAndroidContext(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = username.getText().toString();
                password = pass.getText().toString();
                email = emailid.getText().toString();

                if(name.equals("") || name.length() < 5){
                    username.setError("can't be blank or length cannot be less than 5");
                }
                else if(!name.matches("[A-Za-z0-9]+")){
                    username.setError("only alphabet or number allowed");
                }
                else if(email.equals("") || email.length() < 12)
                {
                    emailid.setError("wrong email id");
                }
                else if(password.equals("") || password.length() < 6){
                    pass.setError("can't be blank or length cannot be less than 6");
                }
                else {
                    final ProgressDialog pd = new ProgressDialog(MainActivity.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    String url = "https://ichat-81fb0.firebaseio.com/users.json";

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                        @Override
                        public void onResponse(String s) {
                            Firebase reference = new Firebase("https://ichat-81fb0.firebaseio.com/users");

                            if(s.equals("null")) {
                                reference.child(name).child("password").setValue(password);
                                Toast.makeText(MainActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                            }
                            else {
                                try {
                                    JSONObject obj = new JSONObject(s);

                                    if (!obj.has(name)) {
                                        reference.child(name).child("password").setValue(pass);
                                        Toast.makeText(MainActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "username already exists", Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            pd.dismiss();
                        }

                    },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println("" + volleyError );
                            pd.dismiss();
                        }
                    });
                    RequestQueue rqueue = Volley.newRequestQueue(MainActivity.this);
                    rqueue.add(request);
                }
            }
        });
    }
}
