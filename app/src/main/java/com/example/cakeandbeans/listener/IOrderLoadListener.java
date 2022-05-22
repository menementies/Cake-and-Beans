package com.example.cakeandbeans.listener;

import com.example.cakeandbeans.Orders;

import java.util.List;

public interface IOrderLoadListener {
    void onOrderLoadLSuccess (List<Orders> orderList);
    void onOrderLoadFailed (String message);
}
