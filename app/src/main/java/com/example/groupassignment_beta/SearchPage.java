package com.example.groupassignment_beta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment_beta.Adapters.RandomRecipeAdapter;
import com.example.groupassignment_beta.Listener.RandomRecipeResponseListener;
import com.example.groupassignment_beta.Listener.RecipeClickListener;
import com.example.groupassignment_beta.Models.RandomRecipeApiResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AppCompatActivity {
    private BottomNavigationView bottomNav;

    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter randomRecipeAdapter;
    RecyclerView recyclerView;
    Spinner spinner;
    List<String> tags = new ArrayList<>();
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        getSupportActionBar().hide();

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading ....");


        searchView = findViewById(R.id.searchView_home);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                tags.clear();
                tags.add(query);
                manager.getRandomRecipes(randomRecipeResponseListener, tags);
                dialog.show();

                return true;
            }



            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        spinner = findViewById(R.id.spinner_tags);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.tags,
                R.layout.spinner_text
        );

        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(spinnerSelectedListener);

        manager = new RequestManager(this);
        //manager.getRandomRecipes(randomRecipeResponseListener);
        //dialog.show();

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.search_new_icon);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.home_icon:
                        // Start the ProfileActivity
                        startActivity(new Intent(SearchPage.this, MainActivity.class));
                        break;
                    case R.id.bookmark_icon:
                        // Start the ProfileActivity
                        startActivity(new Intent(SearchPage.this, Bookmark.class));
                        break;

                    case R.id.cart_icon:
                        // Start the ProfileActivity
                        startActivity(new Intent(SearchPage.this, Shopping_cart.class));
                        break;
                    case R.id.settings_icon:
                        // Start the ProfileActivity
                        startActivity(new Intent(SearchPage.this, Settings.class));
                        break;
                }
                return true;
            }
        });

    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();
            recyclerView = findViewById(R.id. recyler_random);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(SearchPage.this,1));
            randomRecipeAdapter = new RandomRecipeAdapter(SearchPage.this,response.recipes, recipeClickListener);
            recyclerView.setAdapter(randomRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(SearchPage.this, message, Toast.LENGTH_SHORT).show();
        }
    };
    private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            tags.clear();
            tags.add(adapterView.getSelectedItem().toString());
            manager.getRandomRecipes(randomRecipeResponseListener, tags);
            dialog.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView){

        }
    };

    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(SearchPage.this, RecipeDetails.class)
                    .putExtra("id",id));
        }
    };

}