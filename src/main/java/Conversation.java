import com.ibm.watson.developer_cloud.assistant.v1.*;
import com.ibm.watson.developer_cloud.assistant.v1.model.*;
import com.ibm.watson.developer_cloud.util.GsonSerializationHelper;

import java.util.*;

public class Conversation extends com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse  {
    private static final String workspaceId = "e380de5b-7f5b-4287-beb4-3843f449de30";
    private static final String userName = "d6cafd45-923f-4f68-9bea-65a1167973ff";
    private static final String password  = "GBvL0jyr2BzP";
    private List intent, entities, value1, value2, output;
    Object intent2, output2;
    private Context context = new Context();
    private Assistant assistant;
    private MessageResponse response;
    static Scanner s = new Scanner(System.in);

    Conversation() {

        this.assistant = new Assistant(
                "2018-02-16",
                userName,
                password
        );
        assistant.setEndPoint("https://gateway.watsonplatform.net/conversation/api");

    }

    private Object convo(String userQuery){
        InputData input = new InputData.Builder(userQuery).build();
        MessageOptions options = new MessageOptions.Builder(workspaceId)
                .input(input).context(context)
                .build();
        this.response = assistant.message(options).execute();

        if(!this.response.getIntents().isEmpty()){
            intent = this.response.getIntents();
            intent2 = intent.get(0);

        }
        else{
            intent = null;
        }

        if(!this.response.getOutput().getText().isEmpty()){
            output = this.response.getOutput().getText();
            output2 = output.get(0);
        }
        else{
            output2 = null;
        }

        try{
            entities = response.getEntities();
        }
        catch (Exception e){
            //
        }

        this.context = this.response.getContext();


        return(this.output2);
    }



    public static void main(String arg[]) {
        Conversation test = new Conversation();
            while (true){
                System.out.print(">>");
                String input = s.nextLine();
                System.out.println(test.convo(input));
            }
}
}