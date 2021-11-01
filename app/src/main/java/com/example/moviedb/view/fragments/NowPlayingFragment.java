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
import android.widget.ProgressBar;

import com.example.moviedb.R;
import com.example.moviedb.adapter.NowPlayingAdapter;
import com.example.moviedb.helper.ItemClickSupport;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.view.activities.NowPlayingActivity;
import com.example.moviedb.viewmodel.MovieViewModel;

public class NowPlayingFragment extends Fragment {

    private RecyclerView rv_now_playing_fragment;
    private MovieViewModel view_model;
    private ProgressBar progressBar_now_playing_fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);

        rv_now_playing_fragment = view.findViewById(R.id.rv_now_playing_fragment);
        progressBar_now_playing_fragment = view.findViewById(R.id.progressBar_now_playing_fragment);

        progressBar_now_playing_fragment.setVisibility(View.VISIBLE);
        view_model = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        view_model.getNowPlaying();
        view_model.getResultNowPlaying().observe(getActivity(),showNowPlaying);


        return view;
    }

    private Observer<NowPlaying> showNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {
            rv_now_playing_fragment.setLayoutManager(new LinearLayoutManager(getActivity()));
            NowPlayingAdapter adapter = new NowPlayingAdapter(getActivity());
            adapter.setListNowPlaying(nowPlaying.getResults());
            rv_now_playing_fragment.setAdapter(adapter);
            progressBar_now_playing_fragment.setVisibility(View.GONE);

            ItemClickSupport.addTo(rv_now_playing_fragment).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {

                    return false;
                }
            });
            ItemClickSupport.addTo(rv_now_playing_fragment).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("movieId", ""+nowPlaying.getResults().get(position).getId());
                    bundle.putString("name", ""+nowPlaying.getResults().get(position).getTitle());
                    bundle.putString("language", ""+nowPlaying.getResults().get(position).getOriginal_language());
                    bundle.putString("release", ""+nowPlaying.getResults().get(position).getRelease_date());
                    bundle.putString("overview", ""+nowPlaying.getResults().get(position).getOverview());
                    bundle.putString("rating", ""+nowPlaying.getResults().get(position).getVote_average());
                    bundle.putString("genres", ""+nowPlaying.getResults().get(position).getGenre_ids());
                    bundle.putString("img_path", ""+nowPlaying.getResults().get(position).getPoster_path());
                    bundle.putString("backdrop", ""+nowPlaying.getResults().get(position).getBackdrop_path());
                    bundle.putString("FragmentAwal", "NowPlaying");
                    Navigation.findNavController(v).navigate(R.id.action_nowPlayingFragment_to_MovieDetailsFragment, bundle);
                }
            });
        }
    };
}