package edu.brown.cs.student.main;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.List;

public class Api {
    Object data;
    public Object createObject(String json) {
        Gson gson = new Gson();
        List<String> target = gson.fromJson(json, new TypeToken<List<String>>() {}.getType());
        return target;
    }
}
