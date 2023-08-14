package secondhttp.secondApp;

import secondhttp.SecondRequest;
import secondhttp.secondResponse.QuoteOfTheDayResponse;
import secondhttp.secondResponse.SecondResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SecondQuoteController extends SecondController{
    HashMap<Integer,String>citati=new HashMap<>();

    QuoteOfTheDayResponse response;
    public SecondQuoteController(SecondRequest request) {
        super(request);
        citati.put(1,"Oscar Wilde: "+ "Be yourself; everyone else is already taken");
        citati.put(2,"Frank Zappa: "+ "So many books, so little time");
        citati.put(3,"Marcus Tullius Cicero: "+ "A room without books is like a body without a soul");
        citati.put(4,"Mark Twain: "+ "If you tell the truth, you don't have to remember anything");
        citati.put(5,"Mahatma Gandhi: "+ "Live as if you were to die tomorrow. Learn as if you were to live forever");
        citati.put(6,"Friedrich Nietzsche, Twilight of the Idols: "+ "Without music, life would be a mistake");
    }

    @Override
    public SecondResponse doGet() {
        String quote = "";
        Random random = new Random();
        int num = random.nextInt(6);
        quote=citati.get(num+1);
        response = new QuoteOfTheDayResponse(quote);
        return response;
    }

    @Override
    public SecondResponse doPost() {
        return null;
    }
}
