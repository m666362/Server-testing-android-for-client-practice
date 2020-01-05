package org.richit.androidservertesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        AndroidNetworking.initialize( getApplicationContext() );

        AndroidNetworking.post( "URL" )
                .addBodyParameter( "name", "Fayaz" )
                .addHeaders( "authorization", "TOKEN" )
                .build()
                .getAsString( new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                } );
    }
}
