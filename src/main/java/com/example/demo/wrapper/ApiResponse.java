package com.example.demo.wrapper;

public record ApiResponse<T>(
        boolean succes,
        String message,
        T data) {

}
