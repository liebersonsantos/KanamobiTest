package com.liebersonsantos.kanamobitest.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.liebersonsantos.kanamobitest.model.pullRequests.PullResquestResponse;
import com.liebersonsantos.kanamobitest.model.repositories.Item;

import java.util.List;

public class Navigation {

    public static String OWNER = "OWNER";
    public static String REPOSITORY_NAME = "REPOSITORY_NAME";

    public Intent goToPullRequestActivity(Activity activity, Item item){
        Intent intent = new Intent(activity, PullRequestActivity.class);
        intent.putExtra(OWNER, item.getOwner().getLogin());
        intent.putExtra(REPOSITORY_NAME, item.getName());
        return intent;
    }
}