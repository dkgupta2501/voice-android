package mobtexting.com.voiceandroid;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Mobtexting {
    private static final String TAG = "Communicator";
    private static final String SERVER_URL = "http://api.mobtexting.in";
    private Retrofit retrofit;
    private Context context;
    private String api_key;

    /**
     * @param context
     */
    public Mobtexting(Context context) {
        this.context = context;
    }

    /**
     * click to call
     * @param pilot_number
     * @param caller
     * @param receiver
     * @param mobtextingInterface
     */
    public void clickToCall(String pilot_number,String caller,String receiver,final MobtextingInterface mobtextingInterface) {
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            api_key = bundle.getString("mobtexting.api_key");
        } catch (Exception e) {
            mobtextingInterface.onError(new ServerResponse("Dear developer. Don't forget to configure <meta-data android:name=\"mobtexting.api_key\" android:value=\"testValue\"/> in your AndroidManifest.xml file.", false,100 ));
            return;
        }
        if (api_key != null && !api_key.equals("")) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);

            retrofit = new Retrofit.Builder()
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(SERVER_URL)
                    .build();

            Interface service = retrofit.create(Interface.class);

            Call<ServerResponse> call = service.post(api_key, "click2call", pilot_number, caller,receiver);

            call.enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    // response.isSuccessful() is true if the response code is 2xx
                    if (response.isSuccessful()) {
                        Log.e(TAG, response.body().toString());
                        mobtextingInterface.onResponse(response.body());
                    } else {
                        try {
                            Converter<ResponseBody, ServerResponse> errorConverter = retrofit.responseBodyConverter(ServerResponse.class, new Annotation[0]);
                            ServerResponse error = errorConverter.convert(response.errorBody());
                            mobtextingInterface.onError(error);
                        } catch (IOException e) {
                            e.printStackTrace();
                            mobtextingInterface.onError(new ServerResponse("Check your internet connection!/parsing Json exception", false,500 ));
                        }
                    }
                }
                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    mobtextingInterface.onError(new ServerResponse("Check your internet connection!/parsing Json exception", false,500 ));
                }
            });
        } else {
            mobtextingInterface.onError(new ServerResponse("Dear developer. Don't forget to configure <meta-data android:name=\"mobtexting.api_key\" android:value=\"testValue\"/> in your AndroidManifest.xml file.", false,100 ));
        }
    }
}
