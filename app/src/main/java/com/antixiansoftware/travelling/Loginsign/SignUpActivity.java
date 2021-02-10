package com.antixiansoftware.travelling.Loginsign;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.antixiansoftware.travelling.R;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

//    implements View.OnClickListener


    EditText txusername, txpassword, txemail;
    Button signup;
    String strusername,strpassword,stremail;
    ProgressDialog progressDialog;


    private TextView textViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up );


        txusername =findViewById( R.id.editTextUsername );
        txpassword =findViewById( R.id.editTextPassword );
        txemail = findViewById( R.id.editTextEmail );

        signup = findViewById( R.id.buttonRegister );
        signup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();

            }
        } );

        TextView tx1 = (TextView) findViewById(R.id.login1);
        tx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });












    }

    private  void insertData() {
        strusername = txusername.getText().toString().trim();
        strpassword = txpassword.getText().toString().trim();
        stremail = txemail.getText().toString().trim();
        final ProgressDialog progressDialog = new ProgressDialog( this );
        progressDialog.setMessage( "Loading...." );

        if (txusername.getText().toString().equals( "" )) {
            Toast.makeText( this, "Enter Username", Toast.LENGTH_SHORT ).show();
            return;
        }
        if (txpassword.getText().toString().equals( "" )) {
            Toast.makeText( this, "Enter Password", Toast.LENGTH_SHORT ).show();
            return;
        }
        if (txemail.getText().toString().equals( "" )) {
            Toast.makeText( this, "Enter Email", Toast.LENGTH_SHORT ).show();


            return;
        } else {
            progressDialog.show();
            StringRequest request = new StringRequest( Request.Method.POST, "http://atixiansoftwares.com/traveller/SignUp.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equalsIgnoreCase( "Registration Successfully" )) {
                                progressDialog.dismiss();
                                txusername.setText( "" );
                                txpassword.setText( "" );
                                txemail.setText( "" );
                                startActivity( new Intent( getApplicationContext(), LoginActivity.class ) );
                                Toast.makeText( SignUpActivity.this, "Registration Successfully", Toast.LENGTH_SHORT ).show();
                                finish();

                            }

                             else {
                                Toast.makeText( SignUpActivity.this, response, Toast.LENGTH_SHORT ).show();
                                progressDialog.dismiss();

                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText( SignUpActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT ).show();
                    progressDialog.dismiss();

                }
            }
            ) {


                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<String, String>();

                    params.put( "username", strusername );
                    params.put( "password", strpassword );
                    params.put( "email", stremail );

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue( SignUpActivity.this );
            requestQueue.add (request);
        }
    }


    public void ShowHidePass(View view) {
        EditText password = (EditText) findViewById(R.id.editTextPassword);

        if(view.getId()==R.id.show_pass_btn){
            if(password.getTransformationMethod().equals( PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.visibility);
                //Show Password
                password.setTransformationMethod( HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.invisible);
                //Hide Password
                password.setTransformationMethod( PasswordTransformationMethod.getInstance());
            }
        }


    }



}


//            StringRequest stringRequest = new StringRequest(
//                Request.Method.POST,
//                Connect.URL_SIGNUP,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        progressDialog.dismiss();
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            if(!obj.getBoolean("error")){
//                                SharedPrefManager.getInstance(getApplicationContext())
//                                        .userLogin(
//                                                obj.getInt("id"),
//                                                obj.getString("username"),
//                                                obj.getString("email"),
//                                                        obj.getString("password")
//                                        );
//                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                                finish();
//                            }else{
//                                Toast.makeText(
//                                        getApplicationContext(),
//                                        obj.getString("message"),
//                                        Toast.LENGTH_LONG
//                                ).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.dismiss();
//
//                        Toast.makeText(
//                                getApplicationContext(),
//                                error.getMessage(),
//                                Toast.LENGTH_LONG
//                        ).show();
//                    }
//                }
//        ){
//