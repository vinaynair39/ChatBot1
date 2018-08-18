import Conversation.*;
import TwilioFunc.TwilioFunc;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class ChatBot{

    public static void main(String arg[]) {
        Conversation Zieana = new Conversation();
        TwilioFunc twilio = new TwilioFunc();
        Scanner s = new Scanner(System.in);
        System.out.println("Hello?");
        while (true) {

            System.out.print(">>");
            String input = s.nextLine();

            if(!input.isEmpty()){
                Map data = Zieana.convo(input);
                String intent = data.get("intent").toString();
                String output = data.get("output").toString();

                if(intent.equals("send_sms")){
                    System.out.println(output);
                    String person = Zieana.context.get("person").toString();
                    System.out.println(person);
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
                else{
                    System.out.println(output);
                }
            }
        }
    }
}