package com.example.dowloaexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dowloaexample.Interfaz.DownloadAdapter;
import com.example.dowloaexample.Interfaz.InterfacePdf;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public ArrayList<model> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerList);
        items = new ArrayList<>();
        retrofit();
    }

   private String url ="https://my-json-server.typicode.com/eguarangao/GsonData/";
    private void retrofit(){
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(url).
                addConverterFactory(
                        GsonConverterFactory.create()).build();
        InterfacePdf pdf = retrofit.create(InterfacePdf.class);
        Call<List<model>> callModel = pdf.getInterfazModel();
        callModel.enqueue(new Callback<List<model>>() {
            @Override
            public void onResponse(Call<List<model>> call, Response<List<model>> response) {
                List<model> listModel = response.body();
                for(model lista:listModel ){
                   items.add(lista);
                }
                PutDataIntoRecyclerView(items);
            }

            @Override
            public void onFailure(Call<List<model>> call, Throwable t) {
               System.load(t.toString());
            }
        });
    }
    private void PutDataIntoRecyclerView(List<model> listaModel) {

        recyclerView.setHasFixedSize(true);
        DownloadAdapter adaptadorRecycler = new DownloadAdapter(this, (ArrayList<model>) listaModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptadorRecycler);

    }

}