package com.liebersonsantos.kanamobitest.model.service;

import com.liebersonsantos.kanamobitest.BuildConfig;
import com.liebersonsantos.kanamobitest.model.RepositoryResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Api {

    @GET("/search/repositories")
    @Headers({"Accept:application/vnd.github.v3+json",
            "Authorization:" + BuildConfig.TOKEN_1 + BuildConfig.TOKEN_2})
    Single<RepositoryResponse> getRepositories(@Query("q") String language, @Query("sort") String stars, @Query("page") int page );


}
