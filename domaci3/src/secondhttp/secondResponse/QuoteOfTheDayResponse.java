package secondhttp.secondResponse;

import com.google.gson.Gson;

public class QuoteOfTheDayResponse extends SecondResponse {
    private final String html;
    private Gson gson = new Gson();


    public QuoteOfTheDayResponse(String html) {
        this.html = html;
    }

    @Override
    public String getResponseString() {
        String response = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n " + "\r\n" + gson.toJson(html, String.class);
        return response;
    }
}
