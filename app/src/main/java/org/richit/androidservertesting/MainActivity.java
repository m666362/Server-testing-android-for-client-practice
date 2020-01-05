package org.richit.androidservertesting;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.Visibility;
import android.os.Bundle;
import android.text.InputType;
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
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String TAG = this.getClass().getSimpleName();

    public StringBuilder url = new StringBuilder();
    EditText nameSlot;
    EditText addressSlot;
    EditText idSlot;
    TextView nameTv;
    TextView addressTv;
    TextView idTv;
    TextView responseTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        url.append( "http://192.168.0.104:3000/infos/" );
        initializeObject();

        AndroidNetworking.initialize( getApplicationContext() );
    }

    private void initializeObject() {
        nameSlot = findViewById( R.id.nameEditTextxml );
        nameSlot.setInputType( InputType.TYPE_CLASS_TEXT );

        addressSlot = findViewById( R.id.addressEditTextxml );
        addressSlot.setInputType( InputType.TYPE_CLASS_TEXT );
        idSlot = findViewById( R.id.objectIdEt );

        nameTv = findViewById( R.id.nameFromJson );
        addressTv = findViewById( R.id.addressFromJson );
        idTv = findViewById( R.id.idFromJson );
        responseTv = findViewById( R.id.responseFromJson );
    }

    public void postbutton(View view) {
        AndroidNetworking.post( url.toString() )
                .addBodyParameter( "name", nameSlot.getText().toString() )
                .addBodyParameter( "address", addressSlot.getText().toString() )
                .setTag( "test" )
                .setPriority( Priority.MEDIUM )
                .build()
                .getAsJSONObject( new JSONObjectRequestListener() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText( getApplicationContext(), "done", Toast.LENGTH_SHORT ).show();
                            nameTv.setVisibility( View.VISIBLE );
                            addressTv.setVisibility( View.VISIBLE );
                            idTv.setVisibility( View.VISIBLE );
                            responseTv.setVisibility( View.VISIBLE );
                            nameTv.setText( "Name : " + response.getString( "name" ) );
                            addressTv.setText( "Address : " + response.getString( "address" ) );
                            idTv.setText( "Id : " + response.getString( "_id" ) );
                            responseTv.setText( "Response : " + "200 ok" );

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

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
                            Toast.makeText( getApplicationContext(), "done", Toast.LENGTH_SHORT ).show();
                            nameTv.setVisibility( View.VISIBLE );
                            addressTv.setVisibility( View.VISIBLE );
                            idTv.setVisibility( View.VISIBLE );
                            responseTv.setVisibility( View.VISIBLE );
                            nameTv.setText( "Name : " + response.getJSONObject( 0 ).getString( "name" ) );
                            addressTv.setText( "Address : " + response.getJSONObject( 0 ).getString( "address" ) );
                            idTv.setText( "Id : " + response.getJSONObject( 0 ).getString( "_id" ) );
                            responseTv.setText( "Response : " + "200 ok" );

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
        AndroidNetworking.put( url.toString() + idSlot.getText().toString() )
                .addBodyParameter( "name", nameSlot.getText().toString() )
                .addBodyParameter( "address", addressSlot.getText().toString() )
                .setPriority( Priority.LOW )
                .build()
                .getAsJSONObject( new JSONObjectRequestListener() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText( getApplicationContext(), "done", Toast.LENGTH_SHORT ).show();
                            nameTv.setVisibility( View.VISIBLE );
                            addressTv.setVisibility( View.VISIBLE );
                            idTv.setVisibility( View.VISIBLE );
                            responseTv.setVisibility( View.VISIBLE );
                            nameTv.setText( "Name : " + response.getString( "name" ) );
                            addressTv.setText( "Address : " + response.getString( "address" ) );
                            idTv.setText( "Id : " + response.getString( "_id" ) );
                            responseTv.setText( "Response : " + "200 ok" );

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                } );
    }

    public void deletebutton(View view) {
        AndroidNetworking.delete( url.toString() + idSlot.getText().toString() )
                .addPathParameter( "pageNumber", "0" )
                .build()
                .getAsString( new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        responseTv.setText( "Response : " + response );
                        nameTv.setVisibility( View.GONE );
                        addressTv.setVisibility( View.GONE );
                        idTv.setVisibility( View.GONE );
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                } );
    }
}
