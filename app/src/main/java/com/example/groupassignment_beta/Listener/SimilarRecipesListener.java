package com.example.groupassignment_beta.Listener;

import com.example.groupassignment_beta.Models.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipesListener {

    void didFetch(List<SimilarRecipeResponse> response, String message);
    void didError(String message);
}
