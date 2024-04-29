package testdata;

import java.util.HashMap;
import java.util.Map;

public class HomeworkTestData {
    public static Map<String,String> hwMap(String name, String job){
        Map<String,String> payLoad = new HashMap<>();

        payLoad.put("name",name);
        payLoad.put("job",job);

        return payLoad;
    }
}
