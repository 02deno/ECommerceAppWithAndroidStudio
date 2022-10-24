package com.example.myapplication.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.myapplication.Adapter.CategoryAdaptor;
import com.example.myapplication.Adapter.PopularAdaptor;
import com.example.myapplication.Domain.CategoryDomain;
import com.example.myapplication.Domain.ItemDomain;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

public class HomepageActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.cartButton);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageActivity.this,CartListActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageActivity.this,HomepageActivity.class));
            }
        });

    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView2);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add( new CategoryDomain("Gitar","gitar"));
        category.add( new CategoryDomain("Bıyık","biyik"));
        category.add( new CategoryDomain("Pantalon","pantul"));
        category.add( new CategoryDomain("Kolye","kolye"));
        category.add( new CategoryDomain("Yüzük","yuzuk"));

        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);

    }


    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList = findViewById(R.id.recyclerView);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<ItemDomain> itemList = new ArrayList<>();
        itemList.add(new ItemDomain("Siyah Gitar", "siyahgitar", "Bakırköydeki gitar",3.1));
        itemList.add(new ItemDomain("Beyaz Gitar", "beyazgitar", "İngilteredeki gitar",6.9));
        itemList.add(new ItemDomain("Pembe Gitar", "pembegitar", "Yeni alınmış gitar",2.1));
        itemList.add(new ItemDomain("Sivri Bıyık", "sivribiyik", "Uçları sivrileştirmiş",1.2));
        itemList.add(new ItemDomain("Normal Bıyık", "normalbiyik", "İdeal,uygun",5.1));
        itemList.add(new ItemDomain("Hiç Bıyık", "nobiyik", "Bebek gibi",3.4));
        itemList.add(new ItemDomain("Siyah Pantolon", "siyahpantul", "Birazcık küçüğü tam oldu aşiret",6.7));
        itemList.add(new ItemDomain("Krem Pantolon", "krempantul", "Bol oldu",2.5));
        itemList.add(new ItemDomain("Kolye","kolyepic","Yakışır",7.8));
        itemList.add(new ItemDomain("Yüzük","yuzukpic","Üç tane dar olabilir bazıları",9.9));
        itemList.add(new ItemDomain("Mikrofon","mic","Yanında hediye gelir",0.1));



        adapter2 = new PopularAdaptor(itemList);
        recyclerViewPopularList.setAdapter(adapter2);

    }
}
