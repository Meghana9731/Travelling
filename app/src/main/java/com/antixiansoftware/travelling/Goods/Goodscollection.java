package com.antixiansoftware.travelling.Goods;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.antixiansoftware.travelling.Main.MainActivity;
import com.antixiansoftware.travelling.R;

public class Goodscollection extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goodscollection);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        TextView more = (TextView) findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Goodscollection.this, GoodsMoreActivity.class);
                startActivity(intent1);
                finish();
            }
        });


        Button bt1 = (Button) findViewById(R.id.button1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Goodscollection.this, GoodsVehicalsActivity.class);
                startActivity(intent1);
                finish();
            }
        });



        Button bt2 = (Button) findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Goodscollection.this, GoodsVehicalsActivity.class);
                startActivity(intent1);
                finish();
            }
        });


        Button bt3 = (Button) findViewById(R.id.button3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Goodscollection.this, GoodsVehicalsActivity.class);
                startActivity(intent1);
                finish();
            }
        });


        Button bt4 = (Button) findViewById(R.id.button4);
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Goodscollection.this, GoodsVehicalsActivity.class);
                startActivity(intent1);
                finish();
            }
        });


        Button bt5 = (Button) findViewById(R.id.button5);
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Goodscollection.this, GoodsVehicalsActivity.class);
                startActivity(intent1);
                finish();
            }
        });


        Button bt6 = (Button) findViewById(R.id.button6);
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Goodscollection.this, GoodsVehicalsActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        Button bt7 = (Button) findViewById(R.id.button7);
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Goodscollection.this, GoodsVehicalsActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        Button bt8 = (Button) findViewById(R.id.button8);
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Goodscollection.this, GoodsVehicalsActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        Button bt9 = (Button) findViewById(R.id.button9);
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Goodscollection.this, GoodsVehicalsActivity.class);
                startActivity(intent1);
                finish();
            }
        });


        Button bt10 = (Button) findViewById(R.id.button10);
        bt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Goodscollection.this, GoodsVehicalsActivity.class);
                startActivity(intent1);
                finish();
            }
        });




    }


    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(), MainActivity.class );
        startActivity( intent );
        finishAffinity();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
