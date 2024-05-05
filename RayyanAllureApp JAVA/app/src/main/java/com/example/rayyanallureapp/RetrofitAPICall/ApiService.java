// File: ApiService.java
package com.example.rayyanallureapp.RetrofitAPICall;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/users")
    Call<EmployeeGet> getAllEmployeeData(@Query("page") int id);

    @FormUrlEncoded
    @POST("api/users")
    Call<EmployeePost> createNewUser(@Field("name") String name, @Field("job") String job);
}
