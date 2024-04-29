package testdata;

import java.util.HashMap;
import java.util.Map;

public class PetStoreTestData {

    public static Map<String,Object> PetStoreMapper(String username
                                                   ,String firstname
                                                   ,String lastname
                                                   ,String email
                                                   ,String password
                                                   ,Integer phone){
        Map<String,Object> payLoad = new HashMap<>();
        payLoad.put("username",username);
        payLoad.put("firstName",firstname);
        payLoad.put("lastName",lastname);
        payLoad.put("email",email);
        payLoad.put("password",password);
        payLoad.put("phone",phone);

        return payLoad;

    }

}
