package com.example.loyaltyfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        Intent intent = getIntent();
        String eid = intent.getStringExtra("eid");

        TextView Desc = findViewById(R.id.textView14);
        Spinner spinner = findViewById(R.id.spinner6);
        ArrayList<String> list = new ArrayList<String>();


        RequestQueue queue= Volley.newRequestQueue(this);

        String url="http://10.0.2.2:8080/loyaltyfirst/Transactions.jsp?cid="+eid;
        ArrayList<String> note = new ArrayList<String>();
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s)
            {


                if (!s.trim().isEmpty())
                {
                    String[] result = s.trim().split("#");

                    for (String x : result)
                    {
                        String[] Name = x.split(",");
                        list.add(Name[0]);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity6.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
                    spinner.setAdapter(adapter);

                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            String selected = adapterView.getSelectedItem().toString();
                            String url2 = "http://10.0.2.2:8080/loyaltyfirst/SupportFamilyIncrease.jsp?cid=" + eid + "&tref=" + selected;
                            StringRequest req2 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String s) {
                                    String[] remp = s.trim().split("#");
                                    String[] tor = remp[0].split(",");
                                    String output = "TXN Points:  ";
                                    output += tor[2] + "\nFamily ID:  ";
                                    output += tor[0] + "\nFamily Percent:  ";
                                    output += tor[1];
                                    Desc.setText(output);

                                    int points_to_be_added = (Integer.valueOf(tor[2])*30)/100;

                                    String url3 = "http://10.0.2.2:8080/loyaltyfirst/FamilyIncrease.jsp?fid=" + tor[0] + "&cid=" + eid + "&npoints=" + points_to_be_added;
                                    Button familyPointAddButton = findViewById(R.id.button7);

                                    if(note.contains(selected))
                                    {

                                        familyPointAddButton.setEnabled(false);
                                    }
                                    else{
                                        familyPointAddButton.setEnabled(true);
                                    }

                                    familyPointAddButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            note.add(selected);
                                            familyPointAddButton.setEnabled(false);



                                            StringRequest req3 = new StringRequest(Request.Method.GET, url3, new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String s) {

                                                    if(s.trim().equals("1"))
                                                    {
                                                        int poi = Integer.parseInt(tor[2]) * Integer.parseInt(tor[1]) / 100;
                                                        String showe = String.valueOf(poi) + " Points added to the members of the family ID " + tor[0];
                                                        Toast.makeText(MainActivity6.this, showe, Toast.LENGTH_LONG).show();
                                                    }
                                                    else
                                                    {
                                                        Toast.makeText(MainActivity6.this, "Failed to add points", Toast.LENGTH_LONG).show();
                                                        familyPointAddButton.setEnabled(true);
                                                    }

                                                }
                                            }, null);
                                            queue.add(req3);
                                        }
                                    });

//
                                }
                            }, null);
                            queue.add(req2);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }
        }, null);
                queue.add(request);

    }
}