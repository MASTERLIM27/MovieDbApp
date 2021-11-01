package com.example.moviedb.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.moviedb.R;
import com.example.moviedb.adapter.CompanyAdapter;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;
import com.example.moviedb.viewmodel.MovieViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MovieDetailsFragment extends Fragment {

    private TextView lbl_text,lbl_name,lbl_release, lbl_overview, lbl_rating, lbl_genre, lbl_tagline;
    private String fragmentAwal, name, tagline, release, img_path, overview, backdrop, rating, genres="", id, language, company, companyLogo;
    private ImageView img_movie_details, img_backdrop_movie_details;
    private FloatingActionButton fab_back_movie_details_fragment;
    private MovieViewModel view_model;
    private RecyclerView rv_company_movie_details;
    private ProgressBar progressBar_movie_details_fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        lbl_text = view.findViewById(R.id.lbl_language_movie_details);
        lbl_name = view.findViewById(R.id.lbl_name_movie_details);
        lbl_release = view.findViewById(R.id.lbl_release_movie_details);
        img_movie_details = view.findViewById(R.id.img_movie_details);
        lbl_overview = view.findViewById(R.id.lbl_overview_movie_details);
        lbl_rating = view.findViewById(R.id.lbl_rating_movie_details);
        img_backdrop_movie_details = view.findViewById(R.id.img_backdrop_movie_details);
        lbl_tagline = view.findViewById(R.id.lbl_tagline_movie_details);
        lbl_genre = view.findViewById(R.id.lbl_genre_movie_details);
        fab_back_movie_details_fragment = view.findViewById(R.id.fab_back_movie_details_fragment);
        rv_company_movie_details = view.findViewById(R.id.rv_company_movie_details);
        progressBar_movie_details_fragment = view.findViewById(R.id.progressBar_movie_details_fragment);

        progressBar_movie_details_fragment.setVisibility(View.VISIBLE);
        fragmentAwal = getArguments().getString("FragmentAwal");
        id = getArguments().getString("movieId");
        name = getArguments().getString("name");
        language = getArguments().getString("language");
        release = getArguments().getString("release");
        overview = getArguments().getString("overview");
        rating = getArguments().getString("rating");
        img_path = Const.IMG_URL + getArguments().getString("img_path");
        backdrop = Const.IMG_URL + getArguments().getString("backdrop");

        view_model = new ViewModelProvider(MovieDetailsFragment.this).get(MovieViewModel.class);
        view_model.getMovieById(id);
        view_model.getResultGetMovieById().observe(MovieDetailsFragment.this,showResultMovies);

        Glide.with(getContext()).load(img_path).diskCacheStrategy(DiskCacheStrategy.ALL).into(img_movie_details);
        Glide.with(getContext()).load(backdrop).diskCacheStrategy(DiskCacheStrategy.ALL).into(img_backdrop_movie_details);
        lbl_name.setText(name);
        lbl_text.setText(language);
        lbl_release.setText(release);
        lbl_overview.setText(overview);
        lbl_rating.setText(rating);

        fab_back_movie_details_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                if(fragmentAwal.equalsIgnoreCase("NowPlaying")){
                    Navigation.findNavController(view).navigate(R.id.action_MovieDetailsFragment_to_nowPlayingFragment, bundle);
                }else if(fragmentAwal.equalsIgnoreCase("UpComing")){
                    Navigation.findNavController(view).navigate(R.id.action_MovieDetailsFragment_to_upComingFragment, bundle);
                }
            }
        });

        return view;
    }

    private Observer<Movies> showResultMovies = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            for (int i = 0; i < movies.getGenres().size(); i++) {
                if(i == movies.getGenres().size()-1){
                    genres += movies.getGenres().get(i).getName();
                }else {
                    genres += movies.getGenres().get(i).getName() + ", ";
                }
            }

            tagline = movies.getTagline();

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rv_company_movie_details.setLayoutManager(linearLayoutManager);

            CompanyAdapter adapter = new CompanyAdapter(getActivity());
            adapter.setListCompany(movies.getProduction_companies());
            rv_company_movie_details.setAdapter(adapter);

            lbl_tagline.setText(tagline);
            lbl_genre.setText(genres);
            progressBar_movie_details_fragment.setVisibility(View.GONE);
        }
    };
}