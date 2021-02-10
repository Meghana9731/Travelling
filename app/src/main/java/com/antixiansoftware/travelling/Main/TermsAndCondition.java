package com.antixiansoftware.travelling.Main;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.antixiansoftware.travelling.R;

public class TermsAndCondition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_and_condition);
        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView titleWindow = (TextView) findViewById(R.id.titleWindow);
        TextView messageWindow = (TextView) findViewById(R.id.messageWindow);

        titleWindow.setText("Terms And Condition");

        StringBuilder stringBuilder = new StringBuilder();

        String someMessage = "......";

        messageWindow.setText(stringBuilder.toString());


    }
}