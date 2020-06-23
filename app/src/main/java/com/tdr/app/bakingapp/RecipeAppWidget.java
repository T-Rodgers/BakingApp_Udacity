package com.tdr.app.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

import static com.tdr.app.bakingapp.utils.Constants.INGREDIENTS_KEY;
import static com.tdr.app.bakingapp.utils.Constants.NAME_KEY;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_app_widget);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        views.setTextViewText(R.id.widget_recipe_text, sharedPreferences.getString(NAME_KEY, ""));
        views.setTextViewText(R.id.widget_recipe_ingredients_text, sharedPreferences.getString(INGREDIENTS_KEY, ""));

        Intent openAppIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, openAppIntent, 0);
        views.setOnClickPendingIntent(R.id.widget_recipe_text, pendingIntent);
        views.setOnClickPendingIntent(R.id.widget_recipe_ingredients_text, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

