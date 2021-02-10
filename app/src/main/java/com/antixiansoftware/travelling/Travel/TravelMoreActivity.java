package com.antixiansoftware.travelling.Travel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.antixiansoftware.travelling.R;

public class TravelMoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_travel_more );


        ImageView im3 = (ImageView) findViewById(R.id.imageView3);
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent( TravelMoreActivity.this, TravelVehicalsActivity.class);
                startActivity(intent1);
                finish();
            }
        });


        ImageView im4 = (ImageView) findViewById(R.id.imageView4);
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent( TravelMoreActivity.this, TravelVehicalsActivity.class);
                startActivity(intent1);
                finish();
            }
        });


        ImageView im5 = (ImageView) findViewById(R.id.imageView5);
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent( TravelMoreActivity.this, TravelVehicalsActivity.class);
                startActivity(intent1);
                finish();
            }
        });


        ImageView im6 = (ImageView) findViewById(R.id.imageView6);
        im6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent( TravelMoreActivity.this, TravelVehicalsActivity.class);
                startActivity(intent1);
                finish();
            }
        });



        ImageView im7 = (ImageView) findViewById(R.id.imageView7);
        im7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent( TravelMoreActivity.this, TravelVehicalsActivity.class);
                startActivity(intent1);
                finish();
            }
        });


        ImageView im8 = (ImageView) findViewById(R.id.imageView8);
        im8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent( TravelMoreActivity.this, TravelVehicalsActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }



    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(), Trvcollections.class );
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