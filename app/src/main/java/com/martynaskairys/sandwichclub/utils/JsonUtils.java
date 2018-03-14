package com.martynaskairys.sandwichclub.utils;

import com.martynaskairys.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martynas_kairys on 3/14/18.
 */

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        try {
            JSONObject sandwichJsonObject = new JSONObject(json);

            JSONObject name = sandwichJsonObject.getJSONObject("name");
            String mainName = name.getString("mainName");
            JSONArray alsoKnownAsJsonArray = name.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = jsonArrayToStringList(alsoKnownAsJsonArray);

            String placeOfOrigin = sandwichJsonObject.getString("placeOfOrigin");
            String description = sandwichJsonObject.getString("description");
            String image = sandwichJsonObject.getString("image");

            JSONArray ingredientsJsonArray = sandwichJsonObject.getJSONArray("ingredients");
            List<String> ingredients = jsonArrayToStringList(ingredientsJsonArray);

            sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }

    private static List<String> jsonArrayToStringList(JSONArray jsonArray) throws JSONException {
        int arrayLength = jsonArray.length();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < arrayLength; i++) {
            result.add(jsonArray.getString(i));
        }
        return result;
    }
}