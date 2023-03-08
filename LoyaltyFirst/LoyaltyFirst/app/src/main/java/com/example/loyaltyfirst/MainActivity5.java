package com.example.loyaltyfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        Intent intent = getIntent();
        String eid = intent.getStringExtra("eid");

        TextView prizeDesc = findViewById(R.id.textView12);
        Spinner spinner = findViewById(R.id.spinner10);
        ArrayList<String> list = new ArrayList<String>();

        RequestQueue queue = Volley.newRequestQueue(this);
        // loyaltyfirst/Info.jsp?cid=certaincid
        // http://127.0.0.1:8080/loyaltyfirst/PrizeIds.jsp?cid=11004
        String url = "http://10.0.2.2:8080/loyaltyfirst/PrizeIds.jsp?cid=" + eid;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s.indexOf("#") != -1)
                {
                    String[] result = s.trim().split("#");
                    //String[] Name = result[0].split(",");
                    for (String x : result) {
                        list.add(x);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity5.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
                    spinner.setAdapter(adapter);
                    // String han = "";

                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            // MainActivity4.this.han = new String();
                            String name = adapterView.getSelectedItem().toString();

                            /// http://127.0.0.1:8080/loyaltyfirst/RedemptionDetails.jsp?prizeid=certainprizeid&cid=certaincid
                            String url2 = "http://10.0.2.2:8080/loyaltyfirst/RedemptionDetails.jsp?prizeid=" + name + "&cid=" + eid;
                            StringRequest req2 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String s)
                                {
                                    // Toast.makeText(MainActivity4.this,s,Toast.LENGTH_LONG).show();

                                    String[] fall = s.split("#");
                                    String[] result = fall[0].split(",");
                                    String output = "Prize Description\n";
                                    output += result[0];
                                    output += "\n" + "Points Needed\n";
                                    output += result[1];
                                    prizeDesc.setText(output);


                                    //table
                                    TableLayout table = findViewById(R.id.table4);
                                    TableRow tr0 = new TableRow(MainActivity5.this);
                                    // alpha
                                    int count = table.getChildCount();

                                    for (int i = 0; i < count; i++) {
                                        View child = table.getChildAt(i);
                                        if (child instanceof TableRow)
                                            ((ViewGroup) child).removeAllViews();
                                    }
                                    // alpha

                                    TextView tv0 = new TextView(MainActivity5.this);
                                    tv0.setText("Redemption Date");
                                    tv0.setTextColor(Color.BLACK);
                                    tr0.addView(tv0);
                                    TextView tv1 = new TextView(MainActivity5.this);
                                    tv1.setText("Exchange Center");
                                    tv1.setTextColor(Color.BLACK);
                                    tr0.addView(tv1);
                                    table.addView(tr0);



                                    TableRow tr1 = new TableRow(MainActivity5.this);

                                    TextView tv4 = new TextView(MainActivity5.this);
                                    tv4.setText("-----------------------------------------------");
                                    tv4.setTextColor(Color.BLACK);
                                    tr1.addView(tv4);
                                    TextView tv5 = new TextView(MainActivity5.this);
                                    tv5.setText("-----------------------------------------------");
                                    tv5.setTextColor(Color.BLACK);
                                    tr1.addView(tv5);


                                    table.addView(tr1);

                                    for (String x : fall) {
                                        String[] temp = x.trim().split(",");
                                        if (temp.length == 4) {
                                            TableRow tbrow = new TableRow(MainActivity5.this);
                                            TextView t1v = new TextView(MainActivity5.this);

                                            t1v.setText(temp[2]);
                                            t1v.setTextColor(Color.BLACK);
                                            // t1v.setGravity(Gravity.CENTER);
                                            tbrow.addView(t1v);
                                            TextView t2v = new TextView(MainActivity5.this);
                                            //String[] temp2 = temp[1].trim().split(" ");
                                            t2v.setText(temp[3]);
                                            t2v.setTextColor(Color.BLACK);
                                            // t2v.setGravity(Gravity.CENTER);
                                            tbrow.addView(t2v);
                                            table.addView(tbrow);
                                        }
                                    }
                                    TableRow tr3 = new TableRow(MainActivity5.this);

                                    TextView tv43 = new TextView(MainActivity5.this);
                                    tv43.setText("-----------------------------------------------");
                                    tv43.setTextColor(Color.CYAN);
                                    tr3.addView(tv43);
                                    TextView tv15 = new TextView(MainActivity5.this);
                                    tv15.setText("-----------------------------------------------");
                                    tv15.setTextColor(Color.CYAN);
                                    tr3.addView(tv15);


                                    table.addView(tr3);
                                    // ends
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