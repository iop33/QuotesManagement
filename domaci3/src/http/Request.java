package http;

public class Request {
    private final HttpMethod httpMethod;

    private final String path;
    private final String payload;

    public Request(HttpMethod httpMethod, String path, String payload) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.payload=payload;
    }

    public String getPayload() {
        return payload;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }
}
