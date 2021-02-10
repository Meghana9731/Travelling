package com.antixiansoftware.travelling.Travel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.antixiansoftware.travelling.Adapter.Trvcollectionsadapter;
import com.antixiansoftware.travelling.Main.MainActivity;
import com.antixiansoftware.travelling.Model.Trvcollection;
import com.antixiansoftware.travelling.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class Trvcollections extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private List<Trvcollection> trvcollectionList = new ArrayList<>();
    private Toolbar toolbar;
    private Trvcollectionsadapter mAdapter;
    Context context;
    private Button car_image;
    TextView driver_name,vehical_name,seating_capacity,km;
    String stxdriver_name,stxvehical_name,stxseating_capacity,stxkm;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trvcollections);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler1);
        swipeRefreshLayout = findViewById(R.id.refresh);

        FloatingActionButton more = (FloatingActionButton) findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent( Trvcollections.this, TravelMoreActivity.class);
                startActivity(intent1);
                finish();
            }
        });



        vehical_name = findViewById(R.id.edtp1);
        seating_capacity = findViewById(R.id.edtp2);
        km = findViewById(R.id.edtp3);
        car_image = findViewById(R.id.button1);

//
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication().getBaseContext());
//            recyclerView.setLayoutManager(linearLayoutManager);
//            recyclerView.setHasFixedSize(true);
//            recyclerView.setItemViewCacheSize(20);
//            recyclerView.setDrawingCacheEnabled(true);
//            recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
//
//
//        trvcollectionList = new ArrayList<>();



//
//            ApiInterface apiInterface= ApiClient.getApiClient().create( ApiInterface.class);
//            Call<List<Trvcollection>> call=apiInterface.getTr();
//            call.enqueue(new Callback<List<Trvcollection>>() {
//                @Override
//                public void onResponse(Call<List<Trvcollection>> call, Response<List<Trvcollection>> response) {
//                    trvcollectionList =response.body();
//                    mAdapter = new Trvcollectionsadapter(trvcollectionList, getApplication());
//                    mAdapter.notifyDataSetChanged();
//                    recyclerView.setAdapter( mAdapter );


//                }
//
//                @Override
//                public void onFailure(Call<List<Trvcollection>> call, Throwable t) {
//
//                }
//            });
//
//            return ;




//
//        Button bt1 = (Button) findViewById(R.id.button1);
//        bt1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(Trvcollections.this, TravelVehicalsActivity.class);
//                startActivity(intent1);
//                finish();
//            }
//        });
//
//
//
//        Button bt2 = (Button) findViewById(R.id.button2);
//        bt2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(Trvcollections.this, TravelVehicalsActivity.class);
//                startActivity(intent1);
//                finish();
//            }
//        });
//
//
//        Button bt3 = (Button) findViewById(R.id.button3);
//        bt3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(Trvcollections.this, TravelVehicalsActivity.class);
//                startActivity(intent1);
//                finish();
//            }
//        });
//
//
//        Button bt4 = (Button) findViewById(R.id.button4);
//        bt4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(Trvcollections.this, TravelVehicalsActivity.class);
//                startActivity(intent1);
//                finish();
//            }
//        });
//
//
//        Button bt5 = (Button) findViewById(R.id.button5);
//        bt5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(Trvcollections.this, TravelVehicalsActivity.class);
//                startActivity(intent1);
//                finish();
//            }
//        });
//
//
//        Button bt6 = (Button) findViewById(R.id.button6);
//        bt6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(Trvcollections.this, TravelVehicalsActivity.class);
//                startActivity(intent1);
//                finish();
//            }
//        });
//
//
//        Button bt7 = (Button) findViewById(R.id.button7);
//        bt7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(Trvcollections.this, TravelVehicalsActivity.class);
//                startActivity(intent1);
//                finish();
//            }
//        });
//
//
//        Button bt8 = (Button) findViewById(R.id.button8);
//        bt8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(Trvcollections.this, TravelVehicalsActivity.class);
//                startActivity(intent1);
//                finish();
//            }
//        });
//
//        Button bt9 = (Button) findViewById(R.id.button9);
//        bt9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(Trvcollections.this, TravelVehicalsActivity.class);
//                startActivity(intent1);
//                finish();
//            }
//        });
//
//
//        Button bt10 = (Button) findViewById(R.id.button10);
//        bt10.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(Trvcollections.this, TravelVehicalsActivity.class);
//                startActivity(intent1);
//                finish();
//            }
//        });


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
