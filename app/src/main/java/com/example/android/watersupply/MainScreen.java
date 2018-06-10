package com.example.android.watersupply;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {
    Button login, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_screen );


        login = (Button) findViewById( R.id.login );
        signup = (Button) findViewById( R.id.signup );

        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), Login.class );
                startActivity( intent );
                finish();
            }
        } );

        signup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), signup.class );
                startActivity( intent );
                finish();
            }
        } );
    }
}