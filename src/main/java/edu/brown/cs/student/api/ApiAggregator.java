package edu.brown.cs.student.api;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import edu.brown.cs.student.api.client.ApiClient;
import edu.brown.cs.student.api.client.ClientAuth;
import edu.brown.cs.student.api.client.ClientRequestGenerator;
import edu.brown.cs.student.recommender.APIData;
import edu.brown.cs.student.runway.Rent;
import edu.brown.cs.student.runway.Review;
import edu.brown.cs.student.runway.User;
import edu.brown.cs.student.recommender.Item;

import java.lang.reflect.Type;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ApiAggregator {
    private final ApiClient client = new ApiClient();

    /**
     *
     * @param dataType - String representing the type of data being considered (Example: rent, review, users)
     * @return - List of the appropriate objects
     * @throws Exception
     */
    public List<Object> getData(String dataType) throws Exception {
        Gson gson = new Gson();
        Type type = setType(dataType);
        String filename = "https://runwayapi.herokuapp.com/" + dataType + "-";
        String response1 = client.makeRequest(ClientRequestGenerator.getSecuredRequest(filename + "one?auth="));
        String response2 = client.makeRequest(ClientRequestGenerator.getSecuredRequest(filename + "three?auth="));
        response1 = generateExtras("one",filename,response1);
        response2 = generateExtras("two",filename,response2);
        String best_response = response1.length() > response2.length() ? response1 : response2;
        return gson.fromJson(best_response,type);
    }

    /**
     * Creates request until the result is not an error message ( At most 5 times )
     * @param server - The number of the api that we want to call
     * @param filename - The name of the api we are referencing
     * @param response - The response generated on the first call
     * @return - The response as a string
     */
    private String generateExtras(String server, String filename, String response) {
        try{
        for(int i = 0; i < 5; i++) {
            String status[] = response.split(" ");
            if(status[0].equals("Status") && String.valueOf(status[1].charAt(0)).equals("5")) {
                response = client.makeRequest(ClientRequestGenerator.getSecuredRequest(filename + server + "?auth="));
            } else break;
        }
        }catch (Exception ignored) {}
        return response;
    }

    /**
     *
     * @param dataType - The type of data we are setting as a String
     * @return - A Type variable set to the desired type
     * @throws Exception
     */
    public Type setType(String dataType) throws Exception {
        Type type;
        if(dataType.equals("responses")) {
            return new TypeToken<List<APIData>>() {}.getType();
        }else if(dataType.equals("rent")){
            return new TypeToken<List<Rent>>(){}.getType();
        }else if(dataType.equals("reviews")){
            return new TypeToken<List<Review>>(){}.getType();
        }else if (dataType.equals("users")){
            return new TypeToken<List<User>>(){}.getType();
        }else {
            throw new Exception("The aggregator does not contain a content type called: " + dataType);
        }
    }

    public List<APIData> getIntegrationData() throws Exception {
        Gson gson = new Gson();
        String uri = "https://runwayapi.herokuapp.com/integration";
        String response = client.makeRequest(ClientRequestGenerator.getSecuredPostRequest(uri));
        return gson.fromJson(response, new TypeToken<List<APIData>>(){}.getType());
    }
}
