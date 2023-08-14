package http;

import app.RequestHandler;
import http.response.Response;

import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class ServerThread implements Runnable{

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private String payload;
    private String contentLength;

    public ServerThread(Socket sock) {
        this.client = sock;

        try {
            //inicijalizacija ulaznog toka
            in = new BufferedReader(
                    new InputStreamReader(
                            client.getInputStream()));

            //inicijalizacija izlaznog sistema
            out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    client.getOutputStream())), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            // uzimamo samo prvu liniju zahteva, iz koje dobijamo HTTP method i putanju
            String requestLine = in.readLine();

            StringTokenizer stringTokenizer = new StringTokenizer(requestLine);

            String method = stringTokenizer.nextToken();
            String path = stringTokenizer.nextToken();

            System.out.println("\nHTTP ZAHTEV KLIJENTA:\n");
            do {
                System.out.println(requestLine);
                requestLine = in.readLine();
                if (requestLine.startsWith("Content-Length:")) {
                    contentLength = requestLine.substring(16, 18);
                }
            } while (!requestLine.trim().equals(""));

            if (method.equals(HttpMethod.POST.toString())) {
                char[] buffer = new char[Integer.parseInt(contentLength)];
                in.read(buffer);
                String parameters = new String(buffer);
                System.out.println(parameters);
                String[] array = parameters.split("&");
                String ime="";
                String citat="";
                for(String key: array) {
                    String parameterOne = key.split("=")[0];
                    String parameterTwo = key.split("=")[1];
                   if(ime=="")ime=parameterTwo.replace("+"," ");//URLDecoder.decode(parameterTwo, StandardCharsets.UTF_8.name());
                   else citat= parameterTwo.replace("+"," ");//URLDecoder.decode(parameterTwo, StandardCharsets.UTF_8.name());
                }
                payload=ime+": "+" \""+citat+"\"";
            }

            Request request = new Request(HttpMethod.valueOf(method), path, payload);

            RequestHandler requestHandler = new RequestHandler();
            Response response = requestHandler.handle(request);

            System.out.println("\nHTTP odgovor:\n");
            System.out.println(response.getResponseString());

            out.println(response.getResponseString());

            in.close();
            out.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
