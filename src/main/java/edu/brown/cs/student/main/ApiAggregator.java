package edu.brown.cs.student.main;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import edu.brown.cs.student.client.ApiClient;
import edu.brown.cs.student.client.ClientRequestGenerator;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ApiAggregator {
    Object data;
    private final ApiClient client = new ApiClient();
    private final String error_message = "{\"message\": \"Your API call failed due to a malicious error by the course staff\"}";
    public List<Object> getData(String response, String dataType) throws Exception {
        Gson gson = new Gson();
        String filepath = "https://runwayapi.herokuapp.com/" + dataType + "-";
        if(dataType == "rent"){
            // Get the data
            // Verify that its correct and of the correct length and then return it
            String response1 = client.makeRequest(ClientRequestGenerator.getSecuredRequest(filepath + "three"));
            String response2 = client.makeRequest(ClientRequestGenerator.getSecuredRequest(filepath + "one"));
            Type type = new TypeToken<List<Rent>>(){}.getType();

        }else if(dataType == "review"){
            // Get the data
            // Verify that its correct and of the correct length and then return it



        }else if (dataType == "users"){
            // Get the data
            // Verify that its correct and of the correct length and then return it



        }else{
            throw new Exception("The aggregator does not contain a content type called: " + dataType);
        }

        Type type = new TypeToken<List<Review>>(){}.getType();
        List<Review> review = gson.fromJson(response,type);
    }

    public String generateExtras(int attempts, int server, String filepath) {
        String result = error_message;
        for(int i = 0; i < attempts; i++){
            if(result.equals(error_message)){
                result = client.makeRequest(ClientRequestGenerator.getSecuredRequest(filepath + "one"));
            }else break;
        }
        return result;
    }
}
