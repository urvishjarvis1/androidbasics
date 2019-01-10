package com.example.urvish.assignment7.services;

import com.example.urvish.assignment7.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by urvish on 16/2/18.
 * interface for retrofit to use database
 */

public interface RetrofitArrayAPI {
    @GET("scripts/getStudentsByName.php")
    Call<List<Student>> getData(
            @Query("ID") String id
    );
    @FormUrlEncoded
    @POST("scripts/insertStudent.php")
    Call<Void> setData(
            @Field("ID")String id,
            @Field("Name")String name,
            @Field("Email")String email);

}
