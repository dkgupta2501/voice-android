package mobtexting.com.voiceandroid;

import com.google.gson.annotations.SerializedName;

public class ServerResponse {

    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private boolean status;
    @SerializedName("response_code")
    private int responseCode;

    public ServerResponse(String message, boolean status, int responseCode) {
        this.message = message;
        this.status = status;
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
