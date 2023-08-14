package secondhttp.secondApp;

import http.Request;
import http.response.Response;
import secondhttp.SecondRequest;
import secondhttp.secondResponse.SecondResponse;

public abstract class SecondController {
    protected SecondRequest request;

    public SecondController(SecondRequest request) {
        this.request = request;
    }

    public abstract SecondResponse doGet();
    public abstract SecondResponse doPost();
}
