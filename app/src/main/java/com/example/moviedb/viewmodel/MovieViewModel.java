package com.example.moviedb.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.model.Movies;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.model.UpComing;
import com.example.moviedb.repositories.MovieRepository;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository repository;

    public MovieViewModel(@NonNull Application application){
        super(application);
        repository = MovieRepository.getInstance();
    }

    //Begin Of ViewModel Get Movie by ID
    private MutableLiveData<Movies> resultGetMovieById = new MutableLiveData<>();

    public void getMovieById(String movieId){
        resultGetMovieById = repository.getMovieData(movieId);
    }

    public LiveData<Movies> getResultGetMovieById(){
        return  resultGetMovieById;
    }
    //End Of ViewModel Get Movie by ID



    //Begin Of ViewModel Get NowPlaying
    private MutableLiveData<NowPlaying> resultGetNowPlaying = new MutableLiveData<>();

    public void getNowPlaying (){
        resultGetNowPlaying = repository.getNowPlayingData();
    }

    public LiveData<NowPlaying> getResultNowPlaying(){
        return  resultGetNowPlaying;
    }
    //End Of ViewModel Get NowPlaying



    //Begin Of ViewModel Get UpComing
    private MutableLiveData<UpComing> resultGetUpComing = new MutableLiveData<>();

    public void getUpComing (){
        resultGetUpComing = repository.getUpComingData();
    }

    public LiveData<UpComing> getResultUpComing(){
        return  resultGetUpComing;
    }
    //End Of ViewModel Get UpComing
}
