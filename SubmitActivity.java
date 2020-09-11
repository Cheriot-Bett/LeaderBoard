package com.talentjoko.leadership2020;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.HashMap;
import java.util.Map;

public class SubmitActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    FloatingActionButton fab;
    EditText firstName, lastName,email,githubLink;
    Button submitToGoggleForm;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        fab = (FloatingActionButton) findViewById(R.id.fab);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.email);
        githubLink = (EditText) findViewById(R.id.githubLink);
        submitToGoggleForm = findViewById(R.id.submitToGoggleForm);


        // Initializing Queue for Volley
        queue = Volley.newRequestQueue(getApplicationContext());


        submitToGoggleForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sureDialog();

            }
        });


        ActionBar actionBar = getSupportActionBar(); if (actionBar != null) { actionBar.setDisplayHomeAsUpEnabled(true); actionBar.setHomeButtonEnabled(true); getSupportActionBar().setDisplayHomeAsUpEnabled(true); } 




    }

    private void sureDialog() {
        final Dialog dialog = new Dialog(SubmitActivity.this);

        dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
        dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_check);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Dialog Title");

        dialog.show();
        Button yes = (Button)dialog.findViewById(R.id.yesButton);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstName.getText().toString().trim().length() > 0 && lastName.getText().toString().trim().length() > 0
                        && email.getText().toString().trim().length() > 0 && githubLink.getText().toString().trim().length() > 0 ) {
                      postData(firstName.getText().toString().trim(), lastName.getText().toString().trim() ,email.getText().toString().trim(),githubLink.getText().toString().trim());
                } else {
                    dialog.dismiss();

                }

                Toast.makeText(getApplicationContext(),"Required Fields Missing!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void failureDialog() {
        Dialog dialog = new Dialog(SubmitActivity.this);

        dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
        dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_check);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.not_submitted);
        dialog.setTitle("Dialog Title");

        dialog.show();


    }

    private void successDialog() {
        Dialog dialog = new Dialog(SubmitActivity.this);

        dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
        dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_check);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.submitted);
        dialog.setTitle("Dialog Title");

        dialog.show();
    }


    public void postData(final String fname, final String lname, final String emailString, final String gitLink) {

        progressDialog.show();
        StringRequest request = new StringRequest(
                Request.Method.POST,
                Constants.url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", "Response: " + response);
                        if (response.length() > 0) {

                            successDialog();

                            firstName.setText(null);
                            lastName.setText(null);
                            email.setText(null);
                            githubLink.setText(null);
                        } else {

                            failureDialog();
                        }
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                failureDialog();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Constants.firstNameField, fname);
                params.put(Constants.lastNameField, lname);
                params.put(Constants.emailField, emailString);
                params.put(Constants.gitHubLinkField, gitLink);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return  true;
    }
}