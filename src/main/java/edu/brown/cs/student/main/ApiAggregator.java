package edu.brown.cs.student.main;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import edu.brown.cs.student.client.ApiClient;
import edu.brown.cs.student.client.ClientRequestGenerator;

import java.lang.reflect.Type;
import java.util.List;

public class ApiAggregator {
    private final ApiClient client = new ApiClient();

    public List<Object> getData(String dataType) throws Exception {
        Gson gson = new Gson();
        Type type = setType(dataType);
        String filepath = "https://runwayapi.herokuapp.com/" + dataType + "-";
        String response1 = client.makeRequest(ClientRequestGenerator.getSecuredRequest(filepath + "one?auth="));
        String response2 = client.makeRequest(ClientRequestGenerator.getSecuredRequest(filepath + "three?auth="));
        response1 = generateExtras("one",filepath,response1);
        response2 = generateExtras("two",filepath,response2);
        String best_response = response1.length() > response2.length() ? response1 : response2;
        return gson.fromJson(best_response,type);
    }

    private String generateExtras(String server, String filepath, String response) {
        String error_message = "{\"message\": \"Your API call failed due to a malicious error by the course staff\"}";
        try{
        for(int i = 0; i < 5; i++){
            if(response.equals(error_message)){
                response = client.makeRequest(ClientRequestGenerator.getSecuredRequest(filepath + server + "?auth="));
            }else break;
        }
        }catch (Exception e) {}
        return response;
    }
    public Type setType(String dataType) throws Exception {
        Type type;
        if(dataType.equals("rent")){
            return new TypeToken<List<Rent>>(){}.getType();
        }else if(dataType.equals("reviews")){
            return new TypeToken<List<Review>>(){}.getType();
        }else if (dataType.equals("users")){
            return new TypeToken<List<Rent>>(){}.getType();
        }else {
            throw new Exception("The aggregator does not contain a content type called: " + dataType);
        }
    }

}
