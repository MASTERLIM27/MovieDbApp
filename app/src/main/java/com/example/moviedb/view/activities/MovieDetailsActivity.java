package com.example.moviedb.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;
import com.example.moviedb.viewmodel.MovieViewModel;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView lbl_text,lbl_name,lbl_release, lbl_overview, lbl_rating, lbl_genre;
    private String tagline = "", name, release, img_path, overview, backdrop, rating, genres="[",id, language;
    private int genre;
    private ImageView img_movie_details, img_backdrop_movie_details;
    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

//        Intent intent = getIntent();
//        tagline = intent.getStringExtra("tagline");
//        img_path = Const.IMG_URL + intent.getStringExtra("img_path");
//        release = intent.getStringExtra("release_date");
//        name = intent.getStringExtra("name");
//        overview = intent.getStringExtra("overview");
//        backdrop = Const.IMG_URL + intent.getStringExtra("img_backdrop");
//        rating = intent.getStringExtra("rating");
//        genre = intent.getIntExtra("genre",-1);
//        genres = intent.getStringExtra("genre");

        Intent intent = getIntent();
        id = intent.getStringExtra("movieId");
        viewModel = new ViewModelProvider(MovieDetailsActivity.this).get(MovieViewModel.class);
        viewModel.getMovieById(id);
        viewModel.getResultGetMovieById().observe(MovieDetailsActivity.this,showResultMovies);


        lbl_text = findViewById(R.id.lbl_language_movie_details);
        lbl_name = findViewById(R.id.lbl_name_movie_details);
        lbl_release = findViewById(R.id.lbl_release_movie_details);
        img_movie_details = findViewById(R.id.img_movie_details);
        lbl_overview = findViewById(R.id.lbl_overview_movie_details);
        lbl_rating = findViewById(R.id.lbl_rating_movie_details);
        img_backdrop_movie_details = findViewById(R.id.img_backdrop_movie_details);
        lbl_genre = findViewById(R.id.lbl_tagline_movie_details);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private Observer<Movies> showResultMovies = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            name = movies.getTitle();
            for (int i = 0; i < movies.getGenres().size(); i++) {
                if(i == movies.getGenres().size()-1){
                    genres += movies.getGenres().get(i).getName() + "]";
                }else {
                    genres += movies.getGenres().get(i).getName() + ", ";
                }

            }
            img_path = Const.IMG_URL + movies.getPoster_path();
            release = movies.getRelease_date();
            overview = movies.getOverview();
            backdrop = Const.IMG_URL + movies.getBackdrop_path();
            rating = String.valueOf(movies.getVote_average());
            language = movies.getOriginal_language();


            Glide.with(getApplicationContext()).load(img_path).into(img_movie_details);
            Glide.with(getApplicationContext()).load(backdrop).into(img_backdrop_movie_details);
            lbl_name.setText(name);
            lbl_text.setText(language);
            lbl_release.setText(release);
            lbl_overview.setText(overview);
            lbl_rating.setText(rating);
            lbl_genre.setText(genres);
        }
    };
}