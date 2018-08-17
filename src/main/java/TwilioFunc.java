import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.HashMap;

public class TwilioFunc{
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "AC54ae2752e1452a934167476156168571";
    public static final String AUTH_TOKEN = "7264a522752fc9da26cee19bc7eb066d";
    HashMap<String, String> phoneLog = new HashMap<String, String>();


    public void send_sms(String to, String text){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber("+" + to),
                new PhoneNumber("+17177440557"),
                text).create();
    }

    public HashMap phoneBook(){
        phoneLog.put("mum", "919819585343");

        return phoneLog;
    }
    public static void main(String[] args) {
        TwilioFunc twilio = new TwilioFunc();
        String number = twilio.phoneBook().get("mum").toString();
        twilio.send_sms(number, "Zieana is back");
    }
}