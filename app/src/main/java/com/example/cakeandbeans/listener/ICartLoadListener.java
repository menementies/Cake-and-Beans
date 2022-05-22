package com.example.cakeandbeans.listener;

import com.example.cakeandbeans.CartModel;

import java.util.List;

public interface ICartLoadListener {
    void onCartLoadLSuccess (List<CartModel> cartModelList);
    void onCartLoadFailed (String message);
}
