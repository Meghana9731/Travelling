package com.antixiansoftware.travelling.Travel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.antixiansoftware.travelling.R;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TravelVehicalsActivity extends AppCompatActivity {

    private TextView driver_name, vehical_name, seating_capacity, rate_per_km;
    private Button book;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_vehicals );

        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        driver_name = findViewById( R.id.textView1 );
        vehical_name = findViewById( R.id.textView2 );
        seating_capacity = findViewById( R.id.textView3 );
        rate_per_km = findViewById( R.id.textView4 );
        book = findViewById( R.id.button44 );


        book.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();

            }
        } );





        ImageSlider imageSlider = findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://www.suvdrive.com/sites/default/files/public/45%20angle%20view/45%20angle%20view%20Toyota%20Land%20Cruiser%20Prado%20GX%202017.jpg" , ""));
        slideModels.add(new SlideModel("https://cars.usnews.com/static/images/Auto/izmo/i2314350/2016_toyota_land_cruiser_dashboard.jpg" , ""));
        slideModels.add(new SlideModel("https://www.carkhabri.com/Gallery/toyota/toyota-land-cruiser/interior/large/13.jpg" , ""));
        imageSlider.setImageList(slideModels,true);
    }

    private void insertData() {

        final String drivername = driver_name.getText().toString().trim();
        final String vehicalname = vehical_name.getText().toString().trim();
        final String seatingcapacity = seating_capacity.getText().toString().trim();
        final String rateperkm = rate_per_km.getText().toString().trim();
        final ProgressDialog progressDialog = new ProgressDialog( this );
        progressDialog.setMessage("Loading....");

            progressDialog.show();
            StringRequest request = new StringRequest( Request.Method.POST, "https://vampoo.000webhostapp.com/TravelVehicals.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                                Toast.makeText(  TravelVehicalsActivity.this,"Successfully Done",Toast.LENGTH_SHORT ).show();
                                progressDialog.dismiss();
                                 Intent intent1 = new Intent( TravelVehicalsActivity.this, TrvBooksActivity.class);
                                startActivity(intent1);
                                finish();
                            }


                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText( TravelVehicalsActivity.this,error.getMessage(),Toast.LENGTH_SHORT ).show();
                    progressDialog.dismiss();

                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<String, String>();

                    params.put( "driver_name" , drivername );
                    params.put( "vehical_name" , vehicalname );
                    params.put( "seating_capacity" , seatingcapacity );
                    params.put( "rate_per_km" , rateperkm );


                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue( TravelVehicalsActivity.this );
            requestQueue.add( request );
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
