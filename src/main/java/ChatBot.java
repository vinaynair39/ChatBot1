import Conversation.*;
import TwilioFunc.TwilioFunc;
import Dictionary.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class ChatBot{

    public static void main(String arg[]) throws Exception {
        Conversation Zieana = new Conversation();
        TwilioFunc twilio = new TwilioFunc();
        Dictionary dict = new Dictionary();
        Scanner s = new Scanner(System.in);
        System.out.println("Hello?");
        while (true) {

            System.out.print(">>");
            String input = s.nextLine();

            if(!input.isEmpty()){
                Map data = Zieana.convo(input);
                String intent = data.get("intent").toString();
                String output = data.get("output").toString();
                String value = data.get("value").toString();
                System.out.println(data);
                if(intent.equals("send_sms")){
                    System.out.println(output);
                    String person = Zieana.context.get("person").toString();
                    HashMap to = twilio.phoneBook();
                    input = s.nextLine();
                    data = Zieana.convo(input);
                    String message = Zieana.context.get("message").toString();
                    System.out.println(message);
                    twilio.send_sms(to.get(person).toString() , message);
                    output = data.get("output").toString();
                    System.out.print(output);
                }

                else if(intent.equals("place_call")){
                    System.out.println(output);
                    String person = Zieana.context.get("person").toString();
                    HashMap to = twilio.phoneBook();
                    twilio.place_call(to.get(person).toString());
                }
                else if(intent.equals("dictionary")){
                    if(value.equals("synonym")){
                        String word = Zieana.context.get("word").toString();
                        System.out.println(dict.synonyms(word));
                    }
                    if(value.equals("definition")){
                        String word = Zieana.context.get("word").toString();
                        System.out.println(dict.definition(word));
                    }
                    if(value.equals("antonym")){
                        String word = Zieana.context.get("word").toString();
                        System.out.println(dict.antonyms(word));
                    }
                }
                else if(intent.equals("quit_system")){
                    System.out.println("Okay! I'll be here if you need me. bye bye");
                    break;
                }
                else{
                    System.out.println(output);
                }
            }
        }
    }
}