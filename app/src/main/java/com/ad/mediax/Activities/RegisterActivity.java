package com.ad.mediax.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ad.mediax.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private TextView signIn;
    private StringRequest stringRequest;
    private AutoCompleteTextView username, email, password;
    private String Username, Email, Password;
    private Button signUp;
    private SharedPreferences sharedPref;
    private String URL = "https://mediax-ad.000webhostapp.com/mediax/insertuser.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        init();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username = username.getText().toString();
                Email = email.getText().toString().trim();
                Password = password.getText().toString().trim();
                if (validateInput(Username, Email, Password)) {
                    sharedPref = getSharedPreferences("user", 0);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("username", Username);
                    editor.putString("email", Email);
                    editor.putString("password", Password);
                    editor.commit();
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                }
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        //web services

        stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                response.toString();
                Toast.makeText(RegisterActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<String, String>();
                parms.put("username", Username);
                parms.put("email", Email);
                parms.put("password", Password);
                return parms;
            }
        };


    }

    private void init() {
        username = findViewById(R.id.r_edt_unm);
        email = findViewById(R.id.r_edt_email);
        password = findViewById(R.id.r_edt_pwd);
        signUp = findViewById(R.id.r_btn_signUp);
        signIn = findViewById(R.id.r_txt_signIn);


    }

    public boolean validateInput(String inunm, String inemail, String inpassword) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (inunm.isEmpty()) {
            username.setError("Username field is empty.");
            return false;
        }
        if (!inemail.matches(emailPattern)) {
            email.setError("Email field is invalid.");
            return false;
        }
        if (inpassword.isEmpty()) {
            password.setError("Password is empty.");
            return false;
        }

        return true;
    }
}
