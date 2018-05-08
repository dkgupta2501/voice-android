package mobtexting.com.voiceandroid;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Interface {
    @FormUrlEncoded
    @POST("/v1/sms/")
    Call<ServerResponse> post(
            @Field("api_key") String api_key,
            @Field("method") String method,
            @Field("pilot_number") String pilot_number,
            @Field("caller") String caller,
            @Field("receiver") String receiver
    );
}
