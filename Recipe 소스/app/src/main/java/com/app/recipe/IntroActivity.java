package com.app.recipe;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.room.ColumnInfo;

import com.opencsv.CSVReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    DataBaseHelper myDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        new insertData().execute();

    }

    private class insertData extends AsyncTask<Void, Void, Void> {

        MyProgressDialog mProgressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = MyProgressDialog.show(IntroActivity.this, "", "", true, true, null);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                init();

            }  catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }



        protected void onPostExecute(Void result) {


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mProgressDialog.dismiss();
                    startActivity(new Intent(IntroActivity.this, MainActivity.class));
                    finish();
                }
            },2000);

        }
    }

    private void init() {

        try {
            myDbHelper = new DataBaseHelper(IntroActivity.this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}