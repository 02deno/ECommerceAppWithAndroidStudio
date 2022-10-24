package com.example.myapplication.Activity;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Domain.ItemDomain;
import com.example.myapplication.Helper.ManagementCart;
import com.example.myapplication.R;


public class ShowDetailsActivity extends AppCompatActivity {

    private TextView sepet;
    private TextView titleText,priceText,description,number;
    private ImageView plus,minus,picItem;
    private ItemDomain object;
    int numberOrder = 1;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        managementCart = new ManagementCart(this);
        initView();
        getBundle();

    }

    private void getBundle() {
        object = (ItemDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId = this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picItem);

        titleText.setText(object.getTitle());
        priceText.setText("£ "+object.getFee());
        description.setText(object.getDescription());
        number.setText(String.valueOf(numberOrder));

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOrder = numberOrder + 1;
                number.setText(String.valueOf(numberOrder));

            }

        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numberOrder > 1 ) {
                    numberOrder = numberOrder - 1;
                }
                number.setText(String.valueOf(numberOrder));
            }

        });

        sepet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(numberOrder);
                managementCart.insertItem(object);
            }

        });
    }

    private void initView() {
        sepet = findViewById(R.id.sepet);
        titleText= findViewById(R.id.titleText);
        priceText = findViewById(R.id.priceText);
        description = findViewById(R.id.description);
        number = findViewById(R.id.number);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        picItem = findViewById(R.id.picItem);
    }
}
