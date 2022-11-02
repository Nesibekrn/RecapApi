package test_data;

import java.util.HashMap;
import java.util.Map;

public class ReqresTestData {

    public Map<String , String> reqresUsersSetUp(String name, String job){

        Map<String, String> reqresUserMap = new HashMap<>();

        reqresUserMap.put("name",name);
        reqresUserMap.put("job",job);

        return reqresUserMap;
    }

    public Map<String , String> reqresUsersSetUpWithMissingKey(String name, String job){

        Map<String, String> reqresUserMap = new HashMap<>();

        if(name!=null){
            reqresUserMap.put("name",name);
        }
        if (job!=null){
            reqresUserMap.put("job",job);
        }

        return reqresUserMap;
    }

}