package com.example.loyaltyfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Intent intent = getIntent();
        String eid = intent.getStringExtra("eid");

        TextView dateNew = findViewById(R.id.textView9);

        Spinner spinner = findViewById(R.id.spinner2);
        ArrayList<String> list = new ArrayList<String>();

        RequestQueue queue= Volley.newRequestQueue(this);

        String url = "http://10.0.2.2:8080/loyaltyfirst/Transactions.jsp?cid="+eid;
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s.indexOf("#") != -1)
                {
                    String[] result = s.trim().split("#");

                    for (String x : result) {
                        String[] temp = x.trim().split(",");
                        list.add(temp[0]);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity4.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
                    spinner.setAdapter(adapter);
                    // String han = "";
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                            String name = adapterView.getSelectedItem().toString();
                            if (!name.isEmpty()) {

                                //Toast.makeText(MainActivity4.this, name, Toast.LENGTH_LONG).show();
                                /// http://127.0.0.1:8080/loyaltyfirst/TransactionDetails.jsp?tref=T8
                                String url2 = "http://10.0.2.2:8080/loyaltyfirst/TransactionDetails.jsp?tref=" + name;
                                StringRequest req2 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String s) {


                                        String[] fall = s.split("#");
                                        String[] result = fall[0].split(",");
                                        String[] f = result[0].split(" "); // Date & Time
                                        dateNew.setText(f[0] + "                " + result[1] + " Points");


                                        //table
                                        TableLayout table = findViewById(R.id.table2);
                                        // alpha
                                        int count = table.getChildCount();
                                        for (int i = 0; i < count; i++) {
                                            View child = table.getChildAt(i);
                                            if (child instanceof TableRow)
                                                ((ViewGroup) child).removeAllViews();
                                        }
                                        // alpha
                                        TableRow tr0 = new TableRow(MainActivity4.this);

                                        TextView tv0 = new TextView(MainActivity4.this);
                                        tv0.setText("Prod Name");
                                        tv0.setTextColor(Color.BLACK);
                                        tr0.addView(tv0);
                                        TextView tv1 = new TextView(MainActivity4.this);
                                        tv1.setText("Quantity");
                                        tv1.setTextColor(Color.BLACK);
                                        tr0.addView(tv1);
                                        TextView tv2 = new TextView(MainActivity4.this);
                                        tv2.setText("Points");
                                        tv2.setTextColor(Color.BLACK);
                                        tr0.addView(tv2);

//


                                        table.addView(tr0);

                                        TableRow tr1 = new TableRow(MainActivity4.this);

                                        TextView tv4 = new TextView(MainActivity4.this);
                                        tv4.setText("--------------------------------");
                                        tv4.setTextColor(Color.BLACK);
                                        tr1.addView(tv4);
                                        TextView tv5 = new TextView(MainActivity4.this);
                                        tv5.setText("--------------------------------");
                                        tv5.setTextColor(Color.BLACK);
                                        tr1.addView(tv5);
                                        TextView tv6 = new TextView(MainActivity4.this);
                                        tv6.setText("--------------------------------");
                                        tv6.setTextColor(Color.BLACK);
                                        tr1.addView(tv6);


                                        table.addView(tr1);



//
                                        for (String x : fall) {
                                            //Toast.makeText(MainActivity4.this, x, Toast.LENGTH_LONG).show();
                                            if(!x.trim().isEmpty()){
                                            String[] temp = x.trim().split(",");
                                            //if (temp.length >= 4) {
                                                TableRow tbrow = new TableRow(MainActivity4.this);
                                                TextView t1v = new TextView(MainActivity4.this);

                                                t1v.setText(temp[2]);
                                                t1v.setTextColor(Color.BLACK);
                                                // t1v.setGravity(Gravity.CENTER);
                                                tbrow.addView(t1v);
                                                TextView t2v = new TextView(MainActivity4.this);
                                                //String[] temp2 = temp[1].trim().split(" ");
                                                t2v.setText(temp[4]);
                                                t2v.setTextColor(Color.BLACK);
                                                // t2v.setGravity(Gravity.CENTER);
                                                tbrow.addView(t2v);
                                                TextView t3v = new TextView(MainActivity4.this);
                                                t3v.setText(temp[3]);
                                                t3v.setTextColor(Color.BLACK);
                                                // t3v.setGravity(Gravity.CENTER);
                                                tbrow.addView(t3v);


                                                table.addView(tbrow);
                                                }
                                            }
                                        TableRow tr2 = new TableRow(MainActivity4.this);

                                        TextView tv8 = new TextView(MainActivity4.this);
                                        tv8.setText("--------------------------------");
                                        tv8.setTextColor(Color.CYAN);
                                        tr2.addView(tv8);
                                        TextView tv11 = new TextView(MainActivity4.this);
                                        tv11.setText("--------------------------------");
                                        tv11.setTextColor(Color.CYAN);
                                        tr2.addView(tv11);
                                        TextView tv9 = new TextView(MainActivity4.this);
                                        tv9.setText("--------------------------------");
                                        tv9.setTextColor(Color.CYAN);
                                        tr2.addView(tv9);


                                        table.addView(tr2);
                                            // ends
                                        }

                                }, null);
                                queue.add(req2);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });


                }
            }
        },null);
        queue.add(request);
    }
}