package com.example.myapplication.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.myapplication.Domain.ItemDomain;
import com.example.myapplication.Interface.ChangeNumberItemsListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertItem(ItemDomain item) {
        ArrayList<ItemDomain> listItem = getListCart();
        boolean existAlready = false;
        int n = 0;
        for(int i = 0;i < listItem.size();i++) {
            if(listItem.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if(existAlready) {
            listItem.get(n).setNumberInCart(item.getNumberInCart());
        }else {
            listItem.add(item);
        }
        tinyDB.putListObject("CartList",listItem);
        Toast.makeText(context, "Sepete Eklendi ", Toast.LENGTH_SHORT).show();

    }

    public ArrayList<ItemDomain> getListCart() {
        return tinyDB.getListObject("CartList");
    }

    public void plusNumberItem(ArrayList<ItemDomain> listItem, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listItem.get(position).setNumberInCart(listItem.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CartList",listItem);
        changeNumberItemsListener.changed();
    }


    public void minusNumberItem(ArrayList<ItemDomain> listItem, int position, ChangeNumberItemsListener changeNumberItemsListener) {

        if(listItem.get(position).getNumberInCart() == 1) {
            listItem.remove(position);
        }else {
            listItem.get(position).setNumberInCart(listItem.get(position).getNumberInCart() - 1);
        }

        tinyDB.putListObject("CartList",listItem);
        changeNumberItemsListener.changed();
    }

    public Double getTotalFee() {
        ArrayList<ItemDomain> listItem = getListCart();
        double fee = 0;
        for(int i =0;i <listItem.size();i++) {
            fee = fee + (listItem.get(i).getFee()*listItem.get(i).getNumberInCart());
        }
        return fee;
    }
}
