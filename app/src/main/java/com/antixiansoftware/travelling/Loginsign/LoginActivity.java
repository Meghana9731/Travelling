package com.antixiansoftware.travelling.Loginsign;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.antixiansoftware.travelling.Map.PermissionActivity;
import com.antixiansoftware.travelling.R;

public class LoginActivity extends AppCompatActivity  {

    Button loginbutton;
    String strusername, strpassword;
    EditText txusername, txpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        txusername = findViewById(R.id.editText1);
        txpassword = findViewById(R.id.edit_password);
        loginbutton = findViewById(R.id.buttonLogin);

        TextView tx1 = (TextView) findViewById(R.id.registerhere);
        tx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();

            }
        });


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();

            }
        });

    }

    public void Login() {
        if (txusername.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
        } else if (txpassword.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        } else {


            strusername = txusername.getText().toString().trim();
            strpassword = txpassword.getText().toString().trim();
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please Wait..");

            progressDialog.show();


            StringRequest request = new StringRequest(Request.Method.POST, "http://atixiansoftwares.com/traveller/Login.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();

//                            if (response.equalsIgnoreCase("Login Successfully")) {

                                txusername.setText("");
                                txpassword.setText("");
                                startActivity(new Intent(LoginActivity.this, PermissionActivity.class));
                                Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();


//                            } else {
//                                Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
//                                progressDialog.dismiss();
//                            }

                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", strusername);
                    params.put("password", strpassword);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            requestQueue.add(request);


        }
    }


    public void ShowHidePass(View view) {
        EditText password = (EditText) findViewById(R.id.edit_password);
        if (view.getId() == R.id.show_pass_btn) {
            if (password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) (view)).setImageResource(R.drawable.visibility);
                //Show Password
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                ((ImageView) (view)).setImageResource(R.drawable.invisible);
                //Hide Password
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }

}







//        loginbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                loginUser();
//            }
//        });
//
//
//    }
//
//
//
//    private void loginUser() {
//
//         strusername =txusername.getText().toString().trim();
//
//      strpassword = txpassword.getText().toString().trim();
//
//        final ProgressDialog progressDialog = new ProgressDialog( this );
//        progressDialog.setMessage( "Loading..." );
//
//        if (strpassword.isEmpty() ) {
//            txpassword.setError( "Enter Password" );
//            txpassword.requestFocus();
//            return;
//        }
////        else if ( !num.getEditText().getText().toString().matches
////                ( "(?:(?:\\+)91)?[6-9]\\d{9}$" ) ) {
////            num.setError( "Enter valid mobile number" );
////            return;
////        }
//        else {
//            progressDialog.show();
//            StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://atixiansoftwares.com/traveller/Login.php",
//                    new Response.Listener<String>() {
//
//                        @Override
//                        public void onResponse(String response) {
//                            if (response.equalsIgnoreCase( "Login Successful" )) {
////                                Toast.makeText( LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT ).show();
//                                progressDialog.dismiss();
//                                Intent intent = new Intent( getApplication(), PermissionActivity.class );
//                                startActivity( intent );
//
//                            }else {
//                                Toast.makeText( LoginActivity.this, response, Toast.LENGTH_SHORT ).show();
//                                progressDialog.dismiss();
//                            }
//
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText( LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT )
//                            .show();
//                    progressDialog.dismiss();
//                }
//            }
//
//            ){
//                @Override
//                protected Map<String, String> getParams() {
//                    Map<String, String> parms = new HashMap<String, String>();
//
//
//                    parms.put( "username", strusername);
//
//                    parms.put( "password", strpassword );
//
//                    return parms;
//                }
//            };
//            RequestQueue requestQueue = Volley.newRequestQueue( getApplicationContext() );
//            requestQueue.add( stringRequest );
//
//        }
//    }




