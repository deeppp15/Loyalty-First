package com.example.loyaltyfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText usernameField = findViewById(R.id.editText1);
        EditText passwordField = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.button);

        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                String username = usernameField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();
                //"http://127.0.0.1:8080/loyaltyfirst/login?user=certainuser&pass=certainpass"
                String url = "http://10.0.2.2:8080/loyaltyfirst/login?user="+username+"&pass="+password;
                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s)
                    {
                        if(s.trim().equals("No"))
                        {
                            Toast.makeText(MainActivity.this,"Invalid Username or Password",Toast.LENGTH_LONG).show();
                        }
                        else if (s.trim().equals("Please enter all values"))
                        {
                            Toast.makeText(MainActivity.this,"Input field cannot be empty",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            String[] result=s.trim().split(":");
                            String eid = result[1];
                            Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                             intent.putExtra("eid",eid);
                             startActivity(intent);
                            // Toast.makeText(MainActivity.this,"Successfull!",Toast.LENGTH_LONG).show();
                        }
                    }
                }, null);
                queue.add(request);

            }
        });

    }
}
