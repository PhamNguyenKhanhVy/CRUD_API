package com.example.crud_api.api;

import com.example.crud_api.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_Interface {
    @POST("get_list.php")
    Call<List<Student>> getPets();

        @FormUrlEncoded
        @POST("and_student.php")
        Call<Student> insertStudent(
                @Field("key") String key,
                @Field("name") String name,
                @Field("student_code") String student_code,
                @Field("grade") String grade,
                @Field("major") String major,
                @Field("date") String date,
                @Field("image") String image);

//        @FormUrlEncoded
//        @POST("update_pet.php")
//        Call<Student> updatePet(
//                @Field("key") String key,
//                @Field("id") int id,
//                @Field("name") String name,
//                @Field("species") String species,
//                @Field("breed") String breed,
//                @Field("gender") int gender,
//                @Field("birth") String birth,
//                @Field("picture") String picture);
//
//        @FormUrlEncoded
//        @POST("delete_pet.php")
//        Call<Student> deletePet(
//                @Field("key") String key,
//                @Field("id") int id,
//                @Field("picture") String picture);
//
//        @FormUrlEncoded
//        @POST("update_love.php")
//        Call<Student> updateLove(
//                @Field("key") String key,
//                @Field("id") int id,
//                @Field("love") boolean love);
}
