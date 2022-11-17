package com.app.recipe;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements RecipeAdapter.ItemClickListener {
    DataBaseHelper myDbHelper;
    ArrayList<RecipeData> recipeData = new ArrayList<>();

    String keyword1 = "";
    String keyword2 = "";
    String keyword3 = "";
    String keyword4 = "";
    String keyword5 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        try {
            myDbHelper = new DataBaseHelper(SearchActivity.this);

        } catch (IOException e) {
            e.printStackTrace();
        }

        keyword1 = getIntent().getStringExtra("keyword1");
        keyword2 = getIntent().getStringExtra("keyword2");
        keyword3 = getIntent().getStringExtra("keyword3");
        keyword4 = getIntent().getStringExtra("keyword4");
        keyword5 = getIntent().getStringExtra("keyword5");

        TextView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        getList();

    }


    private void getList() {

        SQLiteDatabase database = myDbHelper.getReadableDatabase();
        String sql = "";
        sql = "select *  from recipe_list where 1=1 and (";

        String whereStr="";

        if(!keyword1.equals("")){
            whereStr += "  CKG_MTRL_CN like '%" + keyword1 + "%'";
        }

        if(!keyword2.equals("")){
            if(whereStr.equals("")){
                whereStr += "  CKG_MTRL_CN like '%" + keyword2 + "%'";
            }else{
                whereStr += " and CKG_MTRL_CN like '%" + keyword2 + "%'";
            }
        }

        if(!keyword3.equals("")){
            if(whereStr.equals("")){
                whereStr += "  CKG_MTRL_CN like '%" + keyword3 + "%'";
            }else{
                whereStr += " and CKG_MTRL_CN like '%" + keyword3 + "%'";
            }
        }

        if(!keyword4.equals("")){
            if(whereStr.equals("")){
                whereStr += "  CKG_MTRL_CN like '%" + keyword4 + "%'";
            }else{
                whereStr += " and CKG_MTRL_CN like '%" + keyword4 + "%'";
            }
        }

        if(!keyword5.equals("")){
            if(whereStr.equals("")){
                whereStr += "  CKG_MTRL_CN like '%" + keyword5 + "%'";
            }else{
                whereStr += " and CKG_MTRL_CN like '%" + keyword5 + "%'";
            }
        }


        String orderStr = ") order by RCMM_CNT limit 0,500";

        Log.d("myLog" , "recipe_list " + sql+whereStr+orderStr);

        Cursor cursors = database.rawQuery(sql+whereStr+orderStr, null);
        while (cursors.moveToNext()) {
            String data01 = cursors.getString(0);
            String data02 = cursors.getString(1);
            String data03 = cursors.getString(2);
            String data04 = cursors.getString(3);
            String data05 = cursors.getString(4);
            String data06 = cursors.getString(5);
            String data07 = cursors.getString(6);
            String data08 = cursors.getString(7);
            String data09 = cursors.getString(8);
            String data10 = cursors.getString(9);
            String data11 = cursors.getString(10);
            String data12 = cursors.getString(11);
            String data13 = cursors.getString(12);
            String data14 = cursors.getString(13);
            String data15 = cursors.getString(14);
            String data16 = cursors.getString(15);
            String data17 = cursors.getString(16);
            String data18 = cursors.getString(17);


            recipeData.add(new RecipeData(data01, data02, data03, data04, data05, data06, data07, data08, data09, data10, data11, data12, data13
                    , data14, data15, data16, data17, data18));

        }
        setList();

    }

    private void setList() {
        RecyclerView recylerview = (RecyclerView) findViewById(R.id.recylerview);
        TextView no_data = findViewById(R.id.no_data);
        recylerview.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false);
        recylerview.setLayoutManager(layoutManager);

        RecipeAdapter songAdapter = new RecipeAdapter(SearchActivity.this,
                R.layout.list_recipe, recipeData, "");// 리스트
        songAdapter.setHasStableIds(true);
        songAdapter.setClick(this::itemClick);
        recylerview.setAdapter(songAdapter);

        if (recipeData.size() == 0) {
            no_data.setVisibility(View.VISIBLE);
        } else {
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public void itemClick(RecipeData data) {
        Intent gt= new Intent(SearchActivity.this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("list" , data);
        gt.putExtras(bundle);
        startActivity(gt);
    }
}
