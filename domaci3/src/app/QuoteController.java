package app;

import com.google.gson.Gson;
import http.Request;
import http.Server;
import http.response.HtmlRedirectResponse;
import http.response.HtmlResponse;
import http.response.Response;

import java.io.*;
import java.net.Socket;

public class QuoteController extends Controller{
    private BufferedReader inputFromServer;
    private PrintWriter outputToServer;
    Gson gson = new Gson();
    String requestLine;
    String content;
    public QuoteController(Request request) {
        super(request);
    }

    @Override
    public Response doGet() {
        String htmlBody = "" +
                "<form method=\"POST\" action = \"/save-quote\">" +
                "<label>Author: </label><input name=\"author\" type=\"text\"><br><br>" +
                "<label>Quote: </label><input name=\"quote\" type=\"text\"><br><br>" +
                "<button type = \"submit\">Add quote</button>" +
                "</form>"; //+
               // "<form>" +
               // "<h1>Saved quotes</h1>";

        try {
            Socket socket = new Socket("localhost", 8081);
            inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputToServer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            outputToServer.println("GET /qod HTTP/1.1\n" + "Accept: application/json\r\n\r\n");
            requestLine = inputFromServer.readLine();
            do {
                System.out.println(requestLine);
                requestLine = inputFromServer.readLine();
            } while (!requestLine.trim().equals(""));

            String quoteOfTheDay = gson.fromJson(inputFromServer.readLine(), String.class);
            htmlBody += "<h1>Quote of the Day</h1>\n" +"<h2>" + quoteOfTheDay + "</h2>";
            htmlBody+="<form>" + "<h1>Saved quotes</h1>";
            for(String s:Server.sacuvani){
                htmlBody += "<br><br>" + s + "<br>";
            }
            htmlBody += "</form>";

        } catch (IOException e) {
            e.printStackTrace();
        }
        content = "<html><head><title>Odgovor servera</title></head>\n";
        content += "<body>" + htmlBody + "</body></html>";

        return new HtmlResponse(content);
    }

    @Override
    public Response doPost() {
        Server.sacuvani.add(request.getPayload());
        return new HtmlRedirectResponse();
    }
}
