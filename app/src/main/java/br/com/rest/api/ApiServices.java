package br.com.rest.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by cubas on 06/10/17.
 */

public interface ApiServices {

    @GET("/ContatosWeb/ContatoServlet")
    Call<Contatos> findAll(@Header("Content-Type") String content_type);

}
