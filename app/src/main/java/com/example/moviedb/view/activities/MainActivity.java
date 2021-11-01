package com.example.moviedb.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.moviedb.R;
import com.example.moviedb.model.Movies;
import com.example.moviedb.viewmodel.MovieViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private MovieViewModel viewModel;
    private Button btn_hit;
    private TextView txt_show;
    private TextInputLayout til_movie_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(MainActivity.this).get(MovieViewModel.class);

        btn_hit = findViewById(R.id.btn_submit_main);
        txt_show = findViewById(R.id.txt_show_main);
        til_movie_id = findViewById(R.id.til_movie_id_main);
        btn_hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movieId = til_movie_id.getEditText().getText().toString().trim();
                if(movieId.isEmpty()){
                    til_movie_id.setError("Please fill movie id field");
                }else{
                    til_movie_id.setError(null);
                    viewModel.getMovieById(movieId);
                    viewModel.getResultGetMovieById().observe(MainActivity.this,showResultMovies);
                }
            }
        });
    }

    private Observer<Movies> showResultMovies = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            if(movies==null){
                txt_show.setText("Movie id is not available");
            }
            String title = movies.getTitle();
            txt_show.setText(title);
        }
    };
}