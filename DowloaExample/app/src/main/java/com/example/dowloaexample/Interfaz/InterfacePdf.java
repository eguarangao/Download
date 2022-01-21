package com.example.dowloaexample.Interfaz;

import com.example.dowloaexample.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfacePdf {
    @GET("ArchivosPdf")
    Call<List<model>> getInterfazModel();
}
