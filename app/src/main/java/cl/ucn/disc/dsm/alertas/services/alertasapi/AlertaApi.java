/*
 * Copyright (c) @author Gonzalo Nieto Berríos
 */

package cl.ucn.disc.dsm.alertas.services.alertasapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface AlertaApi {

  String BASE_URL = "https://chilealerta.com/api/query/";

  @GET("?user=gonzo")
  Call<AlertaApiResult> getUltimasAlertas(
      @Query("select") final String select);
}
