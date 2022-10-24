package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.CartListAdapter;
import com.example.myapplication.Helper.ManagementCart;
import com.example.myapplication.Interface.ChangeNumberItemsListener;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListActivity extends AppCompatActivity {

    private TextView onayla;

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView urun,kargo,vergi,toplam,bos;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        managementCart = new ManagementCart(this);
        initView();
        initList();
        CalculateCart();
        bottomNavigation();

        onayla = findViewById(R.id.onayla);

        onayla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CartListActivity.this, "Siparişiniz alındı. ", Toast.LENGTH_SHORT).show();
            }

        });
    }






    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.cartButton);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this,CartListActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this,HomepageActivity.class));
            }
        });

    }

    private void initView() {
        recyclerViewList = findViewById(R.id.recyclerView3);
        urun = findViewById(R.id.totalFeeText);
        kargo = findViewById(R.id.kargo);
        vergi = findViewById(R.id.vergi);
        toplam = findViewById(R.id.toplam);
        bos = findViewById(R.id.bos);
        scrollView = findViewById(R.id.scrollView3);
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if(managementCart.getListCart().isEmpty()) {
            bos.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            bos.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void CalculateCart() {
        double percentTax = 0.02;
        double delivery = 10;

        tax = Math.round((managementCart.getTotalFee()*percentTax)*100) / 100;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery)*100) / 100;
        double itemTotal = Math.round(managementCart.getTotalFee()*100) / 100;

        vergi.setText("£ " + tax);
        kargo.setText("£ " + delivery);
        toplam.setText("£ " + total);
        urun.setText("£ " + itemTotal);

    }
}
