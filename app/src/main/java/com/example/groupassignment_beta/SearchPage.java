package com.example.groupassignment_beta;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment_beta.Adapters.RandomRecipeAdapter;
import com.example.groupassignment_beta.Listener.RandomRecipeResponseListener;
import com.example.groupassignment_beta.Models.RandomRecipeApiResponse;

public class SearchPage extends AppCompatActivity {

    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter randomRecipeAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_random_recipe);

        getSupportActionBar().hide();

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading ....");

        manager = new RequestManager(this);
        manager.getRandomRecipes(randomRecipeResponseListener);

        dialog.show();
    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();
            recyclerView = findViewById(R.id. recyler_random);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(SearchPage.this,1));
            randomRecipeAdapter = new RandomRecipeAdapter(SearchPage.this,response.recipes);
            recyclerView.setAdapter(randomRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(SearchPage.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}