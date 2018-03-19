package com.example.android.i_chat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

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
                else if(pass.length() < 6){
                    password.setError("Invalid pasword");
                }
                else {
                    String url = "https://ichat-81fb0.firebaseio.com/users.json";
                    final ProgressDialog pd = new ProgressDialog(Login.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            if (s.equals("null")) {
                                Toast.makeText(Login.this, "user not found", Toast.LENGTH_LONG).show();
                            } else {
                                try {
                                    JSONObject obj = new JSONObject(s);

                                    if (!obj.has(name)) {
                                        Toast.makeText(Login.this, "user not found", Toast.LENGTH_LONG).show();
                                    } else if (obj.getJSONObject(name).getString("password").equals(pass)) {
                                        UserDetails.username = name;
                                        UserDetails.password = pass;
                                        startActivity(new Intent(Login.this, Users.class));
                                    } else {
                                        Toast.makeText(Login.this, "incorrect password", Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            pd.dismiss();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println("" + volleyError);
                            pd.dismiss();
                        }
                    });

                    RequestQueue rQueue = Volley.newRequestQueue(Login.this);
                    rQueue.add(request);


                }
            }
        });

    }
}
