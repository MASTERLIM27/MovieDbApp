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
import com.example.moviedb.adapter.UpComingAdapter;
import com.example.moviedb.helper.ItemClickSupport;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.model.UpComing;
import com.example.moviedb.viewmodel.MovieViewModel;

public class UpComingFragment extends Fragment {

    private RecyclerView RV_up_coming_fragment;
    private MovieViewModel movieViewModel;
    private ProgressBar progressBar_up_coming_fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_up_coming, container, false);

        RV_up_coming_fragment = view.findViewById(R.id.RV_up_coming_fragment);
        progressBar_up_coming_fragment = view.findViewById(R.id.progressBar_up_coming_fragment);

        progressBar_up_coming_fragment.setVisibility(View.VISIBLE);
        movieViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        movieViewModel.getUpComing();
        movieViewModel.getResultUpComing().observe(getActivity(), showUpComing);

        return view;
    }

    private Observer<UpComing> showUpComing = new Observer<UpComing>() {
        @Override
        public void onChanged(UpComing upComing) {
            RV_up_coming_fragment.setLayoutManager(new LinearLayoutManager(getActivity()));
            UpComingAdapter adapter = new UpComingAdapter(getActivity());
            adapter.setListUpComing(upComing.getResults());
            RV_up_coming_fragment.setAdapter(adapter);
            progressBar_up_coming_fragment.setVisibility(View.GONE);

            ItemClickSupport.addTo(RV_up_coming_fragment).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {

                    return false;
                }
            });
            ItemClickSupport.addTo(RV_up_coming_fragment).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("movieId", ""+upComing.getResults().get(position).getId());
                    bundle.putString("name", ""+upComing.getResults().get(position).getTitle());
                    bundle.putString("language", ""+upComing.getResults().get(position).getOriginal_language());
                    bundle.putString("release", ""+upComing.getResults().get(position).getRelease_date());
                    bundle.putString("overview", ""+upComing.getResults().get(position).getOverview());
                    bundle.putString("rating", ""+upComing.getResults().get(position).getVote_average());
                    bundle.putString("genres", ""+upComing.getResults().get(position).getGenre_ids());
                    bundle.putString("img_path", ""+upComing.getResults().get(position).getPoster_path());
                    bundle.putString("backdrop", ""+upComing.getResults().get(position).getBackdrop_path());
                    bundle.putString("FragmentAwal", "UpComing");

                    Navigation.findNavController(v).navigate(R.id.action_upComingFragment_to_MovieDetailsFragment, bundle);
                }
            });
        }
    };
}