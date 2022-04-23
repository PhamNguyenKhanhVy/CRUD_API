package com.example.crud_api;


import static android.util.Log.println;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud_api.adapter.Api_Adapter;
import com.example.crud_api.api.ApiClient;
import com.example.crud_api.api.Api_Interface;
import com.example.crud_api.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcv;
    private RecyclerView.LayoutManager layoutManager;
    private Api_Adapter adapter;
    private List<Student> studentsList;
    private Api_Interface apiInterface;
    Api_Adapter.RecyclerViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getApiClient().create(Api_Interface.class);


        rcv = findViewById(R.id.rcv);

        layoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(layoutManager);
        getPets();

       Button btn = findViewById(R.id.button_next);
       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(MainActivity.this, Edit_API.class));
           }
       });

    }
    public void getPets(){

        Call<List<Student>> call = apiInterface.getPets();
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {

                studentsList = response.body();
                Log.i(MainActivity.class.getSimpleName(), response.body().toString());
                adapter = new Api_Adapter(studentsList, MainActivity.this);
                rcv.setAdapter(adapter);
                Log.e("List",String.valueOf(studentsList));
                adapter.notifyDataSetChanged();

            }



            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "rp :"+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        getPets();
    }
}