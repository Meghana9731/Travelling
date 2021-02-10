package com.antixiansoftware.travelling.Goods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.antixiansoftware.travelling.Main.MainActivity;
import com.antixiansoftware.travelling.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class GoodsBookActivity extends AppCompatActivity {

    private boolean startDateOrEndDAte = true;
    TextInputEditText txname, txphoneno ;
    EditText txstjourney, txendjourney;
    String strname, strphoneno, strstjourney, strendjourney;
    Button submit;
    DatePickerDialog picker;
    DatePickerDialog picker1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_goods_books );

        txname = findViewById( R.id.txname);
        txphoneno = findViewById( R.id.txphno);
        txstjourney = findViewById( R.id.textView1);
        txendjourney = findViewById( R.id.textView2);
        submit = findViewById( R.id.button6 );




        Toolbar toolbar = findViewById( R.id.toolbar1 );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );




        submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                travelBookingDetail();
            }

        } );


        EditText start = (EditText) findViewById( R.id.textView1 );
        start.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog( GoodsBookActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            EditText start = (EditText) findViewById( R.id.textView1 );
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                start.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.getDatePicker().setMinDate(System.currentTimeMillis());
                picker.show();
            }
        });


        EditText end = (EditText) findViewById( R.id.textView2 );
        end.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker1 = new DatePickerDialog( GoodsBookActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            EditText end = (EditText) findViewById( R.id.textView2 );
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                end.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker1.getDatePicker().setMinDate(System.currentTimeMillis());
                picker1.show();
            }
        });

    }

    private  void travelBookingDetail() {
        strname = txname.getText().toString().trim();
        strphoneno = txphoneno.getText().toString().trim();
        strstjourney = txstjourney.getText().toString().trim();
        strendjourney = txendjourney.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog( this );
        progressDialog.setMessage( "Loading...." );

        if (txname.getText().toString().equals( "" )) {
            Toast.makeText( this, "Enter name", Toast.LENGTH_SHORT ).show();
            return;
        }
        if (txphoneno.getText().toString().equals( "" )) {
            Toast.makeText( this, "Enter Phone No.", Toast.LENGTH_SHORT ).show();
            return;
        }
        if (txstjourney.getText().toString().equals( "" )) {
            Toast.makeText( this, "Enter date", Toast.LENGTH_SHORT ).show();
            return;
        }
        if (txendjourney.getText().toString().equals( "" )) {
            Toast.makeText( this, "Enter date", Toast.LENGTH_SHORT ).show();
            return;
        }
        else {
            progressDialog.show();
            StringRequest request = new StringRequest( Request.Method.POST, "http://atixiansoftwares.com/traveller/TravelBookingDetail.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//                            if (response.equalsIgnoreCase( "Done" )) {
                            progressDialog.dismiss();
                            txname.setText( "" );
                            txphoneno.setText( "" );
                            txstjourney.setText( "" );
                            txendjourney.setText( "" );
                            startActivity( new Intent( getApplicationContext(), MainActivity.class ) );
                            Toast.makeText( GoodsBookActivity.this, " Successfully submitted", Toast.LENGTH_SHORT ).show();
                            finish();

//                            }

//                             else {
//                                Toast.makeText( SignUpActivity.this, response, Toast.LENGTH_SHORT ).show();
//                                progressDialog.dismiss();

//                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText( GoodsBookActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT ).show();
                    progressDialog.dismiss();

                }
            }
            ) {


                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<String, String>();

                    params.put( "name", strname );
                    params.put( "phone_no", strphoneno );
                    params.put( "starting_journey", strstjourney );
                    params.put( "ending_journey", strendjourney );

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue( GoodsBookActivity.this );
            requestQueue.add (request);
        }
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

