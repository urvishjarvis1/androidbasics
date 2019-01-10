package com.example.urvish.assignment7.services;

import android.util.Log;

import com.example.urvish.assignment7.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by urvish on 16/2/18.
 * database operation
 */

public class RetrofitService {
    private RetrofitArrayAPI service;
    private Retrofit retrofit;
    private String data;
    static int sId;
    public RetrofitService() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.53")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(RetrofitArrayAPI.class);
        sId=1;
    }

    /**
     * geting data from database in json form
     * @param dataListener=intface's instance for communication between presenter and model
     * @param query=the query passed by user through activity
     */
    public void getData(final DataListener dataListener, String query) {
        Call<List<Student>> call = service.getData(query);

        call.enqueue(new Callback<List<Student>>() {

            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if (response. body().size()>0) {
                    for(int i=0;i<response.body().size();i++) {
                        data = response.body().get(i).getId()+"\n"
                                        +response.body().get(i).getName()+"\n"
                                        +response.body().get(i).getEmail()+"\n";
                    }
                    dataListener.onSuccess(data);
                } else {
                    dataListener.onSuccess("null");
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.d("error", t.toString());
            }
        });

    }

    /**
     * setting data to database
     * @param dataListener=interface's instance for communication with presenter and model
     * @param name=name
     * @param email=email address
     */

    public void setData(final DataListener dataListener, String name,String email){
        int id=sId;

        System.out.println("id"+id);
        Call<Void> call=service.setData(String.valueOf(id),name,email);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code()==200)
                    dataListener.onSuccess("success");
                else
                    dataListener.onSuccess("there could be some problem with data");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                dataListener.onSuccess("there could be some problem with service");
            }
        });
        //auto increment of id.
        sId++;

    }

    public interface DataListener {
        void onSuccess(String data);
    }
}
