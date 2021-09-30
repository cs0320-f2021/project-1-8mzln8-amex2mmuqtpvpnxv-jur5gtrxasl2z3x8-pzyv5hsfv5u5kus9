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
   * The basic introductory GET request. You should fill it out so it calls our server at the given URL.
   *
   * @return an HttpRequest object for accessing the introductory resource.
   */
  public static HttpRequest getIntroGetRequest() {
    // The resource we want is hosted at https://cq2gahtw4j.execute-api.us-east-1.amazonaws.com/.
    String reqUri = "https://epb3u4xo11.execute-api.us-east-1.amazonaws.com/Prod/introResource/";
    // TODO build and return a new GET HttpRequest.
    // See https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpRequest.html and
    // https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpRequest.Builder.html

    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(reqUri))
            .build();
return request;
  }

  /**
   * Similar to the introductory GET request, but restricted to api key holders only. Try calling it without the API
   * Key configured and see what happens!
   *
   * @return an HttpRequest object for accessing the secured resource.
   */

  /**
   * This is another secured GET request that has an optional string parameter in the URL. Find out what the staff's
   * horoscopes are!
   *
   * @param filepath - the name of the staff member whose horoscope you want to find; an empty string here will indicate
   *              that the server should return a list of all staff members instead.
   * @return an HttpRequest object for accessing and posting to the secured resource.
   */
  public static HttpRequest getSecuredRequest(String filepath) {

    // TODO get the secret API key by using the ClientAuth class.
    ClientAuth clientAuth = new ClientAuth();
    String apiKey = clientAuth.getApiKey();
    // UserName is in index 0 , Key is in index 1
    String[] ApiKeyArray = apiKey.split(" ");
    String reqUri = filepath + "?auth=" + ApiKeyArray[0] + "&key=" + ApiKeyArray[1];
      HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(reqUri))
              .header("x-api-key", ApiKeyArray[1])
              .build();
    return request;
  }
}
