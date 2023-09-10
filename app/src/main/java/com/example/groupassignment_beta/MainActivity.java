package com.example.groupassignment_beta;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment_beta.Adapters.RandomRecipeAdapter;
import com.example.groupassignment_beta.Listener.RandomRecipeResponseListener;
import com.example.groupassignment_beta.Listener.RecipeClickListener;
import com.example.groupassignment_beta.Models.RandomRecipeApiResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    RandomRecipeAdapter randomRecipeAdapter;
    private ImageView peopleLogo;
    RecyclerView recyclerView;
    RequestManager manager;
    List<String> tags = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        manager = new RequestManager(this);
        manager.getRandomRecipes(randomRecipeResponseListener, tags);

        peopleLogo = findViewById(R.id.peopleLogo);

        peopleLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to the login page
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });


        FrameLayout searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to another page or perform search functionality
                Log.d("MainActivity", "Search button pressed");
                try {
                    Intent intent = new Intent(getApplicationContext(), SearchPage.class);
                    startActivity(intent);
                } catch (Exception e){
                    Log.d("MainActivity", "Search button pressed");
                }
            }
        });


        bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setSelectedItemId(R.id.home_icon);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.search_new_icon:
                        // Start the ProfileActivity
                        startActivity(new Intent(MainActivity.this, SearchPage.class));
                        break;
                    case R.id.bookmark_icon:
                        // Start the ProfileActivity
                        startActivity(new Intent(MainActivity.this, Bookmark.class));
                        break;
                    case R.id.cart_icon:
                        // Start the ProfileActivity
                        startActivity(new Intent(MainActivity.this, Shopping_cart.class));
                        break;
                    case R.id.settings_icon:
                        // Start the ProfileActivity
                        startActivity(new Intent(MainActivity.this, Settings.class));
                        break;
                }
                return true;
            }
        });
    }
    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            recyclerView = findViewById(R.id. recyler_random_home);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
            randomRecipeAdapter = new RandomRecipeAdapter(MainActivity.this,response.recipes, recipeClickListener);
            recyclerView.setAdapter(randomRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };


    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(MainActivity.this, RecipeDetails.class)
                    .putExtra("id",id));
        }
    };

}
