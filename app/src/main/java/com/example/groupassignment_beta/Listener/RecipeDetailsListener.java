package com.example.groupassignment_beta.Listener;

import com.example.groupassignment_beta.Models.RecipeDetailsResponse;

public interface RecipeDetailsListener {

    void didFetch(RecipeDetailsResponse response, String message);
    void didError(String message);


}
