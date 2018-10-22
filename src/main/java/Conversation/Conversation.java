package Conversation;

import com.ibm.watson.developer_cloud.assistant.v1.*;
import com.ibm.watson.developer_cloud.assistant.v1.model.*;
import com.fasterxml.jackson.databind.*;
import java.util.*;


public class Conversation{
    private static final String workspaceId = "b38a4b50-8f6d-4f08-806a-89146e9a962b";
    private static final String userName = "d6cafd45-923f-4f68-9bea-65a1167973ff";
    private static final String password  = "GBvL0jyr2BzP";

    public List intent, entities, output;
    public Object intent2, output2 ,value;
    public String intentJson;
    public Context context = new Context();
    ObjectMapper objectMapper = new ObjectMapper();
    private Assistant assistant;
    private MessageResponse response;
    static Scanner s = new Scanner(System.in);
    Map mapper = new HashMap();






    public Conversation() {

        this.assistant = new Assistant(
                "2018-02-16",
                userName,
                password
        );
        assistant.setEndPoint("https://gateway.watsonplatform.net/conversation/api");

    }


    public Map convo(String userQuery){

        InputData input = new InputData.Builder(userQuery).build();
        MessageOptions options = new MessageOptions.Builder(workspaceId)
                .input(input).context(context)
                .build();
        this.response = assistant.message(options).execute();


        if(!this.response.getIntents().isEmpty()){
            intent = this.response.getIntents();
            intent2 = intent.get(0);
            try
            {

                intentJson = objectMapper.writeValueAsString(intent2);
                intentJson = intentJson.split(",")[1].split(":")[1].replace("\"}", "").replace("\"","");
                mapper.put("intent", intentJson);
            }
            catch(Exception ex)
            {
                System.out.println("Error while converting Intent object to Json");
                ex.printStackTrace();
            }
        }
        else{
            intent = null;
            intentJson = "";
            mapper.put("intent", intentJson);

        }
        if(!this.response.getOutput().getText().isEmpty()){
            output = this.response.getOutput().getText();
            output2 = output.get(0);
            mapper.put("output", output2);
        }
        else{
            output2 = "";
            mapper.put("output", output2);
        }

        if(!this.response.getEntities().isEmpty()){
            try {
                value = this.response.getEntities().get(1).getValue();
                mapper.put("value", value);
            }
            catch (Exception e) {
            }
            }
        else{
            value = "";
            mapper.put("value", value);
        }

        this.context = this.response.getContext();


        return mapper;


    }



    public static void main(String arg[]) {
//        Conversation test = new Conversation();
//            while (true){
//                System.out.print("Hello?");
//                System.out.print(">>");
//                String input = s.nextLine();
//                Map data = test.convo(input);
//                System.out.print(data.get("intent").toString() + data.get("output").toString());
//                if(data.get("intent").toString().equals("send_sms")){
//                    String person = test.context.get("person").toString();
//                    TwilioFunc twilioFunc = new TwilioFunc();
//                    twilioFunc.send_sms("9819585343" , "yolo");
//                }
//
//            }
}
}