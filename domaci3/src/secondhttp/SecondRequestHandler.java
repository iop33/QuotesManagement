package secondhttp;

import secondhttp.secondApp.SecondQuoteController;
import secondhttp.secondResponse.SecondResponse;

public class SecondRequestHandler {
    public SecondResponse handle(SecondRequest request) throws Exception {
        if (request.getPath().equals("/qod") && request.getHttpMethod().equals(SecondHttpMethod.GET)) {
            return (new SecondQuoteController(request)).doGet();
        }
        throw new Exception("Page: " + request.getPath() + ". Method: " + request.getHttpMethod() + " not found!");
    }
}
