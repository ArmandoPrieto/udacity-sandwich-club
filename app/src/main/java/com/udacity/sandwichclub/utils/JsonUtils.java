package com.udacity.sandwichclub.utils;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Optional;


public class JsonUtils {


    public static Sandwich parseSandwichJson(String json, String defaultValue) {

        Sandwich sandwich = new Sandwich();

        try {

            JSONObject jo = new JSONObject(json);

            JSONObject name = jo.getJSONObject("name");
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");

            ArrayList<String> alsoKnownAsList = new ArrayList<>();
            for(int i = 0; i< alsoKnownAs.length(); i++){
                alsoKnownAsList.add(alsoKnownAs.getString(i));
            }
            if(alsoKnownAsList.size()==0)
                alsoKnownAsList.add(defaultValue);

            JSONArray ingredients = jo.getJSONArray("ingredients");
            ArrayList<String> ingredientsList = new ArrayList<>();
            for(int i = 0; i< ingredients.length(); i++){
                ingredientsList.add(ingredients.getString(i));
            }
            if(ingredientsList.size()==0)
                ingredientsList.add(defaultValue);

            sandwich.setImage(jo.getString("image"));

            sandwich.setMainName(name.getString("mainName").isEmpty()
                    ?defaultValue:name.getString("mainName"));

            sandwich.setDescription(jo.getString("description").isEmpty()
                    ?defaultValue:jo.getString("description"));

            sandwich.setPlaceOfOrigin(jo.getString("placeOfOrigin").isEmpty()
                    ?defaultValue:jo.getString("placeOfOrigin"));

            sandwich.setAlsoKnownAs(alsoKnownAsList);
            sandwich.setIngredients(ingredientsList);

        } catch (JSONException e) {

            e.printStackTrace();

            sandwich = null;
        }

        return sandwich;
    }
}
