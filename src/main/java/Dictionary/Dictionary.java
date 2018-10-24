package Dictionary;

import com.fasterxml.jackson.databind.DatabindContext;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.*;

import java.lang.reflect.Array;
import java.util.*;


public class Dictionary{
    public String synonyms(String word) throws Exception{
        HttpResponse<JsonNode> response = Unirest.get("https://wordsapiv1.p.mashape.com/words/"+word+"/synonyms")
                .header("X-Mashape-Key", "u2eXSrtP7VmshQZCxVeARui9MZqbp1ANN4XjsnaTGjmy2oiSB6")
                .header("Accept", "application/json")
                .asJson();
        try {
            String data = response.getBody().getObject().get("synonyms").toString();
            String[] dataArray = data.split(",");
            return "The synonym of " + word + " is " + dataArray[1].replace("[", "") + ".";
        }
        catch(Exception e){
            System.out.println("Check the spelling of your word, I Guess it might be wrong!");
        }
        return "Try Again!";
    }
    public String antonyms(String word) throws Exception{
        HttpResponse<JsonNode> response = Unirest.get("https://wordsapiv1.p.mashape.com/words/"+word+"/antonyms")
                .header("X-Mashape-Key", "u2eXSrtP7VmshQZCxVeARui9MZqbp1ANN4XjsnaTGjmy2oiSB6")
                .header("Accept", "application/json")
                .asJson();
        try {
            String data = response.getBody().getObject().get("antonyms").toString();

            String[] dataArray = data.split(",");
            String value = dataArray[0];
            return "The antonym of " + word + " is " + value.replace("[", "").replace("]", "") + ".";
        }
        catch(Exception e){
            System.out.println("Check the spelling of your word, I Guess it might be wrong!");
        }
        return "Try Again!";
    }
    public String definition(String word) throws Exception{
        HttpResponse<JsonNode> response = Unirest.get("https://wordsapiv1.p.mashape.com/words/"+word)
                .header("X-Mashape-Key", "u2eXSrtP7VmshQZCxVeARui9MZqbp1ANN4XjsnaTGjmy2oiSB6")
                .header("Accept", "application/json")
                .asJson();
        try {
            String data = response.getBody().getObject().toString();
            System.out.println(data);
            String[] dataArray = data.split(",");
            return "The Definition of " + word + " is " + dataArray[0] + ".";
        }
        catch(Exception e){
            System.out.println("Check the spelling of your word, I Guess it might be wrong!");
        }
        return "Try Again!";
    }

    public static void main(String arg[]) throws Exception{
        Dictionary dict = new Dictionary();
        String[] response = dict.synonyms("sex").split(",");
        System.out.print(response[0]);
    }
}