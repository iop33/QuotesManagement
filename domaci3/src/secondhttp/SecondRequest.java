package secondhttp;

import http.HttpMethod;

public class SecondRequest {
    private final SecondHttpMethod httpMethod;

    private final String path;

    public SecondRequest(SecondHttpMethod httpMethod, String path) {
        this.httpMethod = httpMethod;
        this.path = path;
    }

    public SecondHttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }
}
