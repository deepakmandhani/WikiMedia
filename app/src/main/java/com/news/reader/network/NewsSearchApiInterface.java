package com.news.reader.network;

import com.news.reader.models.NewsSearchAPIResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NewsSearchApiInterface {

    @Headers("X-Mashape-Key: fvHlKdY1h3mshGmqwXBB5Wo4DJuyp1M6tUujsnHuIrSLm2BO40")

    @GET("NewsSearchAPI?autocorrect=true&count=50&")
    Observable<NewsSearchAPIResponse> getNewsSearchResult(@Query("q") String s);
}
