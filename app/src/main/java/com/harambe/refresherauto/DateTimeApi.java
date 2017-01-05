package com.harambe.refresherauto;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Sandeep on 05-01-2017.
 */

public interface DateTimeApi {

    @GET("/")
    Call<DateItem> getTime();
}
