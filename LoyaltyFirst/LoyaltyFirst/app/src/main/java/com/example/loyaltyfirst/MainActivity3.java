package com.example.loyaltyfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextClock;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();
        String eid = intent.getStringExtra("eid");

        TableLayout table = findViewById(R.id.tableMain);
        TableRow tr0 = new TableRow(this);

        TextView tv0 = new TextView(this);
        tv0.setText("TXN REF.");
        tv0.setTextColor(Color.BLACK);
        tr0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText("Date");
        tv1.setTextColor(Color.BLACK);
        tr0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText("Points");
        tv2.setTextColor(Color.BLACK);
        tr0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText("Total");
        tv3.setTextColor(Color.BLACK);
        tr0.addView(tv3);

        table.addView(tr0);

        //dc
        TableRow tr1 = new TableRow(this);

        TextView tv4 = new TextView(this);
        tv4.setText("--------------------------------");
        tv4.setTextColor(Color.BLACK);
        tr1.addView(tv4);
        TextView tv5 = new TextView(this);
        tv5.setText("--------------------------------");
        tv5.setTextColor(Color.BLACK);
        tr1.addView(tv5);
        TextView tv6 = new TextView(this);
        tv6.setText("--------------------------------");
        tv6.setTextColor(Color.BLACK);
        tr1.addView(tv6);
        TextView tv7 = new TextView(this);
        tv7.setText("--------------------------------");
        tv7.setTextColor(Color.BLACK);
        tr1.addView(tv7);

        table.addView(tr1);
        //dc

        RequestQueue queue= Volley.newRequestQueue(this);
        //http://127.0.0.1:8080/loyaltyfirst/Transactions.jsp?cid=certaincid
        String url="http://10.0.2.2:8080/loyaltyfirst/Transactions.jsp?cid="+eid;
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s)
            {
                if (s.indexOf("#") != -1)
                {
                    String[] result = s.trim().split("#");

                    for (String x : result) {
                        String[] temp = x.trim().split(",");
                        TableRow tbrow = new TableRow(MainActivity3.this);
                        TextView t1v = new TextView(MainActivity3.this);

                        t1v.setText(temp[0]);
                        t1v.setTextColor(Color.BLACK);
                        // t1v.setGravity(Gravity.CENTER);
                        tbrow.addView(t1v);
                        TextView t2v = new TextView(MainActivity3.this);
                        String[] temp2 = temp[1].trim().split(" ");
                        t2v.setText(temp2[0]);
                        t2v.setTextColor(Color.BLACK);
                        // t2v.setGravity(Gravity.CENTER);
                        tbrow.addView(t2v);
                        TextView t3v = new TextView(MainActivity3.this);
                        t3v.setText(temp[2]);
                        t3v.setTextColor(Color.BLACK);
                        // t3v.setGravity(Gravity.CENTER);
                        tbrow.addView(t3v);
                        TextView t4v = new TextView(MainActivity3.this);
                        t4v.setText(temp[3]);
                        t4v.setTextColor(Color.BLACK);
                        // t4v.setGravity(Gravity.CENTER);
                        tbrow.addView(t4v);
                        table.addView(tbrow);
                    }

                    //dc
                    TableRow tr2 = new TableRow(MainActivity3.this);

                    TextView tv8 = new TextView(MainActivity3.this);
                    tv8.setText("--------------------------------");
                    tv8.setTextColor(Color.CYAN);
                    tr2.addView(tv8);
                    TextView tv11 = new TextView(MainActivity3.this);
                    tv11.setText("--------------------------------");
                    tv11.setTextColor(Color.CYAN);
                    tr2.addView(tv11);
                    TextView tv9 = new TextView(MainActivity3.this);
                    tv9.setText("--------------------------------");
                    tv9.setTextColor(Color.CYAN);
                    tr2.addView(tv9);
                    TextView tv10 = new TextView(MainActivity3.this);
                    tv10.setText("--------------------------------");
                    tv10.setTextColor(Color.CYAN);
                    tr2.addView(tv10);

                    table.addView(tr2);
                    //dc
                }
            }
        },null);
        queue.add(request);

    }
}