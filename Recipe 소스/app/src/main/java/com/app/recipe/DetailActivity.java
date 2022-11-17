package com.app.recipe;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    DataBaseHelper myDbHelper;


    RecipeData recipeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recipeData = (RecipeData)getIntent().getSerializableExtra("list");

        setList();
    }


    private void setList() {
        TextView title = findViewById(R.id.title);
        if(recipeData.getTitle().equals("")){
            title.setText(recipeData.getTitle_re());
        }else{
            title.setText(recipeData.getTitle());
        }

        TextView type = findViewById(R.id.type);
        type.setText(recipeData.getC_type());

        TextView contents = findViewById(R.id.contents);
        contents.setText(recipeData.getDescription());

        TextView joo = findViewById(R.id.joo);
        joo.setText(recipeData.getC_nick());


        TextView jae = findViewById(R.id.jae);
        jae.setText(recipeData.getC_material());

        TextView inbun = findViewById(R.id.inbun);
        inbun.setText(recipeData.getDiffculity());

        TextView times = findViewById(R.id.times);
        times.setText(recipeData.getC_times());


        TextView nan = findViewById(R.id.nan);
        nan.setText(recipeData.getPeople());

        new YoutubeAsyncTask().execute();

    }
    String api_key="AIzaSyAtbDmZjvvv13k_EfIWQdHlTax61CctWSs";
    ArrayList<SearchData> searchData = new ArrayList<>();

    private class YoutubeAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
                final JsonFactory JSON_FACTORY = new GsonFactory();
                final long NUMBER_OF_VIDEOS_RETURNED = 5;

                YouTube yy  = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) throws IOException {

                    }
                }).setApplicationName("yy-sampe").build();

                YouTube.Search.List search = yy.search().list("id,snippet");

                search.setKey(api_key);

                search.setQ(recipeData.getTitle()+"%20" +"레시피");
                search.setOrder("relevance"); //date relevance

                search.setType("video");

                search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
                search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
                SearchListResponse searchResponse = search.execute();

                List<SearchResult> searchResultList = searchResponse.getItems();

                if (searchResultList != null) {
                    prettyPrint(searchResultList.iterator(), recipeData.getTitle()+"%20" +"레시피");
                }
            } catch (GoogleJsonResponseException e) {
                System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                        + e.getDetails().getMessage());
                System.err.println("There was a service error 2: " + e.getLocalizedMessage() + " , " + e.toString());
            } catch (IOException e) {
                System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
            } catch (Throwable t) {
                t.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setList1();
        }

        public void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {
            if (!iteratorSearchResults.hasNext()) {
                System.out.println(" There aren't any results for your query.");
            }

            StringBuilder sb = new StringBuilder();

            while (iteratorSearchResults.hasNext()) {
                SearchResult singleVideo = iteratorSearchResults.next();
                ResourceId rId = singleVideo.getId();

                // Double checks the kind is video.
                if (rId.getKind().equals("youtube#video")) {
                    Thumbnail thumbnail = (Thumbnail) singleVideo.getSnippet().getThumbnails().get("default");
                    sb.append("ID : " + rId.getVideoId() + " , 제목 : " + singleVideo.getSnippet().getTitle() + " , 썸네일 주소 : " + thumbnail.getUrl());
                    sb.append("\n");

                    searchData.add(new SearchData( rId.getVideoId() ,singleVideo.getSnippet().getTitle() ,  thumbnail.getUrl(),""));
                }
            }

            String result = sb.toString();


            Log.d("myLog", "result" + result);
        }


    }


    private void setList1(){
        RecyclerView  recylerview = (RecyclerView) findViewById(R.id.recylerview);
        recylerview.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(DetailActivity.this, LinearLayoutManager.VERTICAL, false);
        recylerview.setLayoutManager(layoutManager);

        VideoAdapter songAdapter = new VideoAdapter(DetailActivity.this,
                R.layout.list_recipe, searchData, "");// 리스트
        songAdapter.setHasStableIds(true);

        recylerview.setAdapter(songAdapter);


    }

}


