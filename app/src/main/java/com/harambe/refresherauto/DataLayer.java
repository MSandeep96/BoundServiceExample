package com.harambe.refresherauto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sandeep on 05-01-2017.
 */

public class DataLayer implements DataLayerInterface{

    private DateTimeApi dateTimeApi;

    public DataLayer(){
        Gson gson=new GsonBuilder().create();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://date.jsontest.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        dateTimeApi=retrofit.create(DateTimeApi.class);
    }


    @Override
    public void fetchTime(final ServiceInterface mService) {
        Call<DateItem> itemCall=dateTimeApi.getTime();
        itemCall.enqueue(new Callback<DateItem>() {
            @Override
            public void onResponse(Call<DateItem> call, Response<DateItem> response) {
                mService.gotResponse(response.body());
            }

            @Override
            public void onFailure(Call<DateItem> call, Throwable t) {

            }
        });
    }
}
