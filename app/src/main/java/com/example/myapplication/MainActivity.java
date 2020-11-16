package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.DnsResolver;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
//import okhttp3.Request;
import okhttp3.RequestBody;
//import okhttp3.Response;

import static android.provider.ContactsContract.CommonDataKinds.Email.ADDRESS;
import static android.provider.Telephony.Carriers.PORT;
import static java.net.Proxy.Type.HTTP;

public class MainActivity extends AppCompatActivity {

    private Button getApiBtn, postApiBtn;
    private TextView resultTextView;
    RequestQueue requestQueue;
    private static final String TAG = MainActivity.class.getSimpleName();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = (TextView) findViewById(R.id.resultTextView);
        getApiBtn = (Button) findViewById(R.id.getApiBtn);
        postApiBtn = (Button)findViewById(R.id.postApiBtn);

        // RequestQueue For Handle Network Request
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //Click Listner for GET JSONObject
        getApiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
        //Click Listner for POST JSONObject
        postApiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
            }
        });


    }

    // Post Request For JSONObject
    public void postData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        // Enter the correct url for your api service site
        String url="http://localhost:8080/users";
        System.out.println("URL: "+url);
        //String url = getResources().getString(R.string.url);

        com.example.myapplication.DbHelper dbHelper = new DbHelper(this);

        dbHelper.insertData("U001","P001", "Harini", "Female", "23");
        dbHelper.insertData("U002","P002", "Kaveesha", "Female", "26");
        dbHelper.insertData("U003","P003", "Shalini", "Female", "23");
        dbHelper.insertData("U004","P004", "Ransi", "Female", "24");

        Cursor cursor = dbHelper.getAllData();

        List<SubUser> users = new ArrayList<SubUser>();

        while (cursor.moveToNext()) {
            SubUser a = new SubUser(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            users.add(a);
        }

        dbHelper.close();
        GsonBuilder gsonBuilder = new GsonBuilder();

        // This is the main class for using Gson. Gson is typically used by first constructing a Gson instance and then invoking toJson(Object) or fromJson(String, Class) methods on it.
        // Gson instances are Thread-safe so you can reuse them freely across multiple threads.
        Gson gson = gsonBuilder.create();

        String JsObject = gson.toJson(users);
        log("\nConverted JSONObject ==> " + JsObject);

        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = prettyGson.toJson(users);
        System.out.println("232323");
        JSONArray array =  null;
        try {
            array = new JSONArray(JsObject);
            System.out.println("AAAAAAAAAAAAA");
            System.out.println(array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        JSONObject object = null;
//        try {
//            //input your API parameters
//           object = new JSONObject(JsObject);
//            System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
//            System.out.println(object);
//        } catch (JSONException e) {
//            System.out.println("HIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
//            e.printStackTrace();
//        }
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, array,
                new Response.Listener() {

                    @Override
                    public void onResponse(Object response) {
                        System.out.println("RESPONSE");
                        resultTextView.setText("String Response : " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                resultTextView.setText("Error getting response");
            }
    });
        requestQueue.add(jsonArrayRequest);
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        resultTextView.setText("String Response : "+ response.toString());
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                resultTextView.setText("Error getting response");
//            }
//        });
//        requestQueue.add(jsonObjectRequest);
    }

    // Get Request For JSONObject
    public void getData(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            //String url = getResources().getString(R.string.url);
            String url = "http://localhost:8081/";
            JSONArray object = new JSONArray();
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    resultTextView.setText("Resposne : " + response.toString());
                    Toast.makeText(getApplicationContext(), "I am OK !" + response.toString(), Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(jsonArrayRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void log(Object print) {
        System.out.println(print);

    }





}

