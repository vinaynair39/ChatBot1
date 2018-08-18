package Conversation;

import com.ibm.watson.developer_cloud.assistant.v1.*;
import com.ibm.watson.developer_cloud.assistant.v1.model.*;
import com.fasterxml.jackson.databind.*;
import java.util.*;


public class Conversation{
    private static final String workspaceId = "e380de5b-7f5b-4287-beb4-3843f449de30";
    private static final String userName = "d6cafd45-923f-4f68-9bea-65a1167973ff";
    private static final String password  = "GBvL0jyr2BzP";

    public List intent, entities, value1, value2, output;
    public Object intent2, output2;
    public String intentJson, entityJson;
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
        }


        if(!this.response.getOutput().getText().isEmpty()){
            output = this.response.getOutput().getText();
            output2 = output.get(0);
            mapper.put("output", output2);
        }
        else{
            output2 = null;
        }

        if(!this.response.getEntities().isEmpty()){
            entities = response.getEntities();
            String entity = entities.get(0).toString();
        }
        else{
        }

        this.context = this.response.getContext();


        return mapper;


    }



    public static void main(String arg[]) {
        Conversation test = new Conversation();
            while (true){
                System.out.print("Hello?");
                System.out.print(">>");
                String input = s.nextLine();
                Map data = test.convo(input);
//                System.out.print(data.get("intent").toString() + data.get("output").toString());
//                if(data.get("intent").toString().equals("send_sms")){
//                    String person = test.context.get("person").toString();
//                    TwilioFunc twilioFunc = new TwilioFunc();
//                    twilioFunc.send_sms("9819585343" , "yolo");
//                }

            }
}
}