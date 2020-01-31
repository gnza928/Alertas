/*
 * Copyright (c) @author Gonzalo Nieto Berríos
 */

package cl.ucn.disc.dsm.alertas.services.alertasapi;

import cl.ucn.disc.dsm.alertas.model.Alerta;
import cl.ucn.disc.dsm.alertas.services.AlertaService;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlertaApiService implements AlertaService {
  private static final Logger log = LoggerFactory.getLogger(AlertaApiService.class);

  private final AlertaApi alertaApi;


  public AlertaApiService() {

    // Logging with slf4j
    final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(log::debug)
        .setLevel(Level.BODY);

    // Web Client
    final OkHttpClient httpClient = new Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .callTimeout(10, TimeUnit.SECONDS)
        .addNetworkInterceptor(loggingInterceptor)
        .build();

    // https://futurestud.io/tutorials/retrofit-getting-started-and-android-client
    this.alertaApi = new Retrofit.Builder()
        // The main URL
        .baseUrl(AlertaApi.BASE_URL)
        // JSON to POJO
        .addConverterFactory(GsonConverterFactory.create())
        // Validate the interface
        .validateEagerly(true)
        // The client
        .client(httpClient)
        // Build the Retrofit ..
        .build()
        // .. get the NewsApi.
        .create(AlertaApi.class);
  }

  private static List<Alerta> getAlertasFromCall(final Call<AlertaApiResult> theCall) {

    try {

      // Get the result from the call
      final Response<AlertaApiResult> response = theCall.execute();

      // UnSuccessful !
      if (!response.isSuccessful()) {

        // Error!
        throw new AlertaAPIException(
            "Can't get the AlertaApiResult, code: " + response.code(),
            new HttpException(response)
        );

      }

      final AlertaApiResult theResult = response.body();

      // No body
      if (theResult == null) {
        throw new AlertaAPIException("AlertaResult was null");
      }

      // No articles
      if (theResult.alertas == null) {
        throw new AlertaAPIException("Alertas in AlertasResult was null");
      }

      return theResult.alertas;

    }  catch (final IOException ex) {
      throw new AlertaAPIException("Can't get the AlertaResult", ex);
    }

  }

  public List<Alerta> getAlertas(final int pageSize) {

    // the Call
    final Call<AlertaApiResult> theCall = this.alertaApi.getUltimasAlertas("ultimos_sismos");

    // Process the Call.
    return getAlertasFromCall(theCall);
  }

  public static final class AlertaAPIException extends RuntimeException {

    public AlertaAPIException(final String message) {
      super(message);
    }

    public AlertaAPIException(final String message, final Throwable cause) {
      super(message, cause);
    }

  }
}
