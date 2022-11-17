package com.app.recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.ItemClickListener {
    DataBaseHelper myDbHelper;
    ArrayList<RecipeData> recipeData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            myDbHelper = new DataBaseHelper(MainActivity.this);

        } catch (IOException e) {
            e.printStackTrace();
        }


        EditText jae01 = findViewById(R.id.jae01);
        EditText jae02 = findViewById(R.id.jae02);
        EditText jae03 = findViewById(R.id.jae03);
        EditText jae04 = findViewById(R.id.jae04);
        EditText jae05 = findViewById(R.id.jae05);

        TextView search_recipe = findViewById(R.id.search_recipe);
        search_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jae01.getText().toString().equals("") && jae02.getText().toString().equals("")&& jae03.getText().toString().equals("")&&jae04.getText().toString().equals("")
                        && jae05.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this , "재료를 한가지 이상 입력하세요." ,Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MainActivity.this , SearchActivity.class);
                    intent.putExtra("keyword1" , jae01.getText().toString());
                    intent.putExtra("keyword2" , jae02.getText().toString());
                    intent.putExtra("keyword3" , jae03.getText().toString());
                    intent.putExtra("keyword4" , jae04.getText().toString());
                    intent.putExtra("keyword5" , jae05.getText().toString());

                    startActivity(intent);
                }
            }
        });

        getList();

    }


    private void getList(){

        SQLiteDatabase database = myDbHelper.getReadableDatabase();
        String sql = "";
        sql = "select *  from recipe_list where 1=1 order by RCMM_CNT limit 0,30" ;

        Cursor cursors = database.rawQuery(sql, null);
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


            recipeData.add(new RecipeData(data01,data02,data03,data04,data05,data06,data07,data08,data09,data10,data11,data12,data13
                    ,data14,data15,data16,data17,data18));

        }
        setList();

    }

    private void setList(){
        RecyclerView  recylerview = (RecyclerView) findViewById(R.id.recylerview);
        recylerview.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recylerview.setLayoutManager(layoutManager);

        RecipeAdapter songAdapter = new RecipeAdapter(MainActivity.this,
                R.layout.list_recipe, recipeData, "");// 리스트
        songAdapter.setHasStableIds(true);
        songAdapter.setClick(this);
        recylerview.setAdapter(songAdapter);


    }

    @Override
    public void itemClick(RecipeData recipeData) {
        Intent gt= new Intent(MainActivity.this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("list" , recipeData);
        gt.putExtras(bundle);
        startActivity(gt);

    }
}
