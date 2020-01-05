package org.richit.androidservertesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public StringBuilder url = new StringBuilder(  ) ;
    EditText nameSlot;
    EditText addressSlot;
    TextView name;
    TextView address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        url.append( "http://192.168.0.104:3000/infos/");
        initializeObject();

        AndroidNetworking.initialize( getApplicationContext() );
    }

    private void initializeObject() {
        nameSlot = findViewById( R.id.nameEditTextxml );
        nameSlot.setInputType( InputType.TYPE_CLASS_TEXT );

        addressSlot = findViewById( R.id.addressEditTextxml );
        addressSlot.setInputType( InputType.TYPE_CLASS_TEXT );

        name = findViewById( R.id.nameFromJson );
        address = findViewById( R.id.addressFromJson );
    }

    public void postbutton(View view) {
        AndroidNetworking.post( url.toString() )
                .addBodyParameter( "name", nameSlot.getText().toString() )
                .addBodyParameter( "address", addressSlot.getText().toString())
                .setTag( "test" )
                .setPriority( Priority.MEDIUM )
                .build()
                .getAsString( new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText( getApplicationContext(), "200 Ok", Toast.LENGTH_SHORT ).show();
                    }

                    @Override
                    public void onError(ANError anError) {

                        Toast.makeText( getApplicationContext(), "404 not found", Toast.LENGTH_SHORT ).show();
                    }
                } );
    }

    public void getbutton(View view) {
        AndroidNetworking.get( url.toString() )
                .addPathParameter( "pageNumber", "0" )
                .addQueryParameter( "limit", "3" )
                .setTag( this )
                .setPriority( Priority.LOW )
                .build()
                .getAsJSONArray( new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            response.getJSONObject( 0 );
                            name.setText( "Name : " + response.getJSONObject( 0 ).getString( "name" ) );
                            address.setText( "Address : " + response.getJSONObject( 0 ).getString( "address" ) );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                } );
    }

    public void putbutton(View view) {

    }

    public void deletebutton(View view) {
    }
}
