package com.tdr.app.bakingapp.utils;

import com.tdr.app.bakingapp.model.Ingredient;
import com.tdr.app.bakingapp.model.Recipe;
import com.tdr.app.bakingapp.model.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.tdr.app.bakingapp.utils.Constants.ID_KEY;
import static com.tdr.app.bakingapp.utils.Constants.INGREDIENTS_KEY;
import static com.tdr.app.bakingapp.utils.Constants.INGREDIENT_DESCRIPTION_KEY;
import static com.tdr.app.bakingapp.utils.Constants.MEASUREMENT_KEY;
import static com.tdr.app.bakingapp.utils.Constants.NAME_KEY;
import static com.tdr.app.bakingapp.utils.Constants.QUANTITY_KEY;
import static com.tdr.app.bakingapp.utils.Constants.SERVINGS_KEY;
import static com.tdr.app.bakingapp.utils.Constants.STEPS_KEY;
import static com.tdr.app.bakingapp.utils.Constants.STEP_DESCRIPTION_KEY;
import static com.tdr.app.bakingapp.utils.Constants.STEP_ID_KEY;
import static com.tdr.app.bakingapp.utils.Constants.STEP_SHORT_DESCRIPTION_KEY;
import static com.tdr.app.bakingapp.utils.Constants.STEP_VIDEO_URL_KEY;


public class JSONUtils {

    private static final String TAG = "TROUBLESHOOTING";
    public static List<Recipe> extractRecipeJSON(String json) {

        List<Recipe> recipes = new ArrayList<>();

        try {
            JSONArray recipeArray = new JSONArray(json);

            for (int i = 0; i < recipeArray.length(); i++) {

                JSONObject recipeJSONObject = recipeArray.getJSONObject(i);

                int id = recipeJSONObject.getInt(ID_KEY);
                String name = recipeJSONObject.getString(NAME_KEY);
                int servings = recipeJSONObject.getInt(SERVINGS_KEY);
                List<Ingredient> ingredients = new ArrayList<>();
                JSONArray ingredientsArray = recipeJSONObject.getJSONArray(INGREDIENTS_KEY);

                for (int a = 0; a < ingredientsArray.length(); a++) {
                    JSONObject ingredient = ingredientsArray.getJSONObject(a);
                    String ingredientDescription = ingredient.getString(INGREDIENT_DESCRIPTION_KEY);
                    double quantity = ingredient.getDouble(QUANTITY_KEY);
                    String measurement = ingredient.getString(MEASUREMENT_KEY);

                    Ingredient currentIngredient = new Ingredient(quantity, measurement, ingredientDescription);
                    ingredients.add(currentIngredient);

                }

                JSONArray stepsArray = recipeJSONObject.getJSONArray(STEPS_KEY);
                List<Step> steps = new ArrayList<>();
                for (int j = 0; j < stepsArray.length(); j++) {
                    JSONObject step = stepsArray.getJSONObject(j);

                    int stepId = step.getInt(STEP_ID_KEY);
                    String shortDescription = step.getString(STEP_SHORT_DESCRIPTION_KEY);
                    String description = step.getString(STEP_DESCRIPTION_KEY);
                    String videoUrl = step.getString(STEP_VIDEO_URL_KEY);

                    Step currentStep = new Step(stepId, shortDescription, description, videoUrl);
                    steps.add(currentStep);

                }

                    Recipe recipe = new Recipe(id, name, servings, ingredients, steps);
                    recipes.add(recipe);
                }

                return recipes;

            } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }
}
