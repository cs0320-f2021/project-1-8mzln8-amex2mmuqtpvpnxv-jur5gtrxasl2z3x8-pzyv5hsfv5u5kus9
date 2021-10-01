package edu.brown.cs.student.main;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Api {
    Object data;
    public void createObject(String response) {
        Gson gson = new Gson();
        if(response.equals("{\"message\": \"Your API call failed due to a malicious error by the course staff\"}")) return;
        Type type = new TypeToken<List<Review>>(){}.getType();
        List<Review> review = gson.fromJson(response,type);
    }
}
