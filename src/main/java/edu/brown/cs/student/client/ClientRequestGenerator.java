package edu.brown.cs.student.client;

import edu.brown.cs.student.client.ClientAuth;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * This class generates the HttpRequests that are then used to make requests from the ApiClient.
 */
public class ClientRequestGenerator {


  /**
   * Similar to the introductory GET request, but restricted to api key holders only. Try calling it without the API
   * Key configured and see what happens!
   *
   * @return an HttpRequest object for accessing the secured resource.
   */

  public static HttpRequest getSecuredRequest(String filepath) {

    // TODO get the secret API key by using the ClientAuth class.
    ClientAuth clientAuth = new ClientAuth();
    String apiKey = clientAuth.getApiKey();
    // UserName is in index 0 , Key is in index 1
    String[] ApiKeyArray = apiKey.split(" ");
    String reqUri = "https://runwayapi.herokuapp.com/reviews-one";
      HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(reqUri))
              .header("x-api-key", "MgBStIP")
              .build();
    return request;
  }
}
