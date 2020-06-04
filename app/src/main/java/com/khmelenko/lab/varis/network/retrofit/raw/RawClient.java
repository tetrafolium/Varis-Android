package com.khmelenko.lab.varis.network.retrofit.raw;

import com.khmelenko.lab.varis.storage.AppSettings;
import io.reactivex.Single;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Raw http client
 *
 * @author Dmytro Khmelenko (d.khmelenko@gmail.com)
 */
public class RawClient {

  private Retrofit mRetrofit;
  private final OkHttpClient mHttpClient;

  private RawApiService mRawApiService;

  public RawClient(final Retrofit retrofit, final OkHttpClient okHttpClient,
                   final AppSettings appSettings) {
    mRetrofit = retrofit;
    mHttpClient = okHttpClient;

    final String travisUrl = appSettings.getServerUrl();
    updateEndpoint(travisUrl);
  }

  /**
   * Updates Travis endpoint
   *
   * @param newEndpoint New endpoint
   */
  public void updateEndpoint(final String newEndpoint) {
    mRetrofit = new Retrofit.Builder()
                    .baseUrl(newEndpoint)
                    .addConverterFactory(new Converter.Factory() {
                      @Override
                      public Converter<ResponseBody, ?> responseBodyConverter(
                          final Type type, final Annotation[] annotations,
                          final Retrofit retrofit) {
                        if (String.class.equals(type)) {
                          return new Converter<ResponseBody, String>() {
                            @Override
                            public String convert(final ResponseBody value)
                                throws IOException {
                              return value.string();
                            }
                          };
                        }
                        return null;
                      }
                    })
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(mHttpClient)
                    .build();
    mRawApiService = mRetrofit.create(RawApiService.class);
  }

  /**
   * Gets Raw API service
   *
   * @return Raw API service
   */
  public RawApiService getApiService() { return mRawApiService; }

  /**
   * Executes single request
   *
   * @param url URL for request
   * @return Response
   */
  public Single<Response> singleRequest(final String url) {

    Request request = new Request.Builder().url(url).build();

    return Single.create(e -> {
      Response response = mHttpClient.newCall(request).execute();
      e.onSuccess(response);
    });
  }

  /**
   * Executes single request
   *
   * @param url URL for request
   * @return String
   */
  public Single<String> singleStringRequest(final String url) {

    Request request = new Request.Builder().url(url).build();

    return Single.create(e -> {
      Response response = mHttpClient.newCall(request).execute();
      e.onSuccess(response.body().string());
    });
  }

  public String getLogUrl(final Long jobId) {
    return String.format("%sjobs/%d/log", mRetrofit.baseUrl(), jobId);
  }
}
