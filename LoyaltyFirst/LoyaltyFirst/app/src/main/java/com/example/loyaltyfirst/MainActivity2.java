package com.example.loyaltyfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String eid = intent.getStringExtra("eid");

        TextView textView3 = findViewById(R.id.textView3);
        TextView textView6 = findViewById(R.id.textView6);
        ImageView profilePic = findViewById(R.id.imageView);

        RequestQueue queue= Volley.newRequestQueue(this);
        //loyaltyfirst/Info.jsp?cid=certaincid
        String url="http://10.0.2.2:8080/loyaltyfirst/Info.jsp?cid="+eid;
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String[] result=s.trim().split("#");
                String[] Name=result[0].split(",");
                // String points=result[1];

                textView3.setText(Name[0]);
                textView6.setText(Name[1]);
            }
        },null);
        queue.add(request);
        String url2="http://10.0.2.2:8080/loyaltyfirst/images/"+eid+".jpeg";
        ImageRequest request2=new ImageRequest(url2, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                profilePic.setImageBitmap(bitmap);
            }
        },0,0,null,null);
        queue.add(request2);

        // Button Functionalities now
        // ALL TXNS
        Button allTxns = findViewById(R.id.button2);
        allTxns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity2.this,MainActivity3.class);
                intent.putExtra("eid",eid);
                startActivity(intent);
            }
        });

        // TXN DETAIL
        Button txnDetail = findViewById(R.id.button3);
        txnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity4.class);
                intent.putExtra("eid",eid);
                startActivity(intent);
            }
        });

        // RED Detail
        Button redDetail = findViewById(R.id.button4);
        redDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity5.class);
                intent.putExtra("eid",eid);
                startActivity(intent);
            }
        });

        // Add % to family
        Button addPercent = findViewById(R.id.button5);
        addPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity6.class);
                intent.putExtra("eid",eid);
                startActivity(intent);
            }
        });

        Button exitNow = findViewById(R.id.button6);
        exitNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }
}