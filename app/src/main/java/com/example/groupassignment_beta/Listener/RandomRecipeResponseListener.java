package com.example.groupassignment_beta.Listener;

import com.example.groupassignment_beta.Models.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {

    void didFetch(RandomRecipeApiResponse response, String message);
    void didError(String message);

}
