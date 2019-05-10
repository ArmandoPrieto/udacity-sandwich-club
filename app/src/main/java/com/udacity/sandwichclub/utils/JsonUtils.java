package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = new Sandwich();

        try {

            JSONObject jo = new JSONObject(json);

            JSONObject name = jo.getJSONObject("name");
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");

            ArrayList<String> alsoKnownAsList = new ArrayList<>();
            for(int i = 0; i< alsoKnownAs.length(); i++){
                alsoKnownAsList.add(alsoKnownAs.getString(i));
            }

            JSONArray ingredients = jo.getJSONArray("ingredients");
            ArrayList<String> ingredientsList = new ArrayList<>();
            for(int i = 0; i< ingredients.length(); i++){
                ingredientsList.add(ingredients.getString(i));
            }

            sandwich.setMainName(name.getString("mainName"));
            sandwich.setAlsoKnownAs(alsoKnownAsList);
            sandwich.setIngredients(ingredientsList);
            sandwich.setDescription(jo.getString("description"));
            sandwich.setImage(jo.getString("image"));
            sandwich.setPlaceOfOrigin(jo.getString("placeOfOrigin"));

        } catch (JSONException e) {

            e.printStackTrace();

            sandwich = null;
        }

        return sandwich;
    }
}
