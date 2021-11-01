package com.example.moviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.view.activities.MovieDetailsActivity;

import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.CardViewViewHolder> {

    private Context context;
    private List<NowPlaying.Results> listNowPlaying;
    private List<NowPlaying.Results>getListNowPlaying(){
        return listNowPlaying;
    }
    public void setListNowPlaying(List<NowPlaying.Results> listNowPlaying){
        this.listNowPlaying = listNowPlaying;
    }
    public NowPlayingAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public NowPlayingAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_now_playing, parent,false);
        return new NowPlayingAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NowPlayingAdapter.CardViewViewHolder holder, int position) {
        final NowPlaying.Results results = getListNowPlaying().get(position);
        holder.lbl_title.setText(results.getTitle());
        holder.lbl_overview.setText(results.getOverview());
        holder.lbl_release_date.setText(results.getRelease_date());
        Glide.with(context).load(Const.IMG_URL + results.getPoster_path()).into(holder.img_poster);
//        holder.cv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent(context, MovieDetailsActivity.class);
////                intent.putExtra("name", ""+ results.getTitle());
////                intent.putExtra("tagline", ""+ results.getOriginal_language());
////                intent.putExtra("release_date", ""+ results.getRelease_date());
////                intent.putExtra("img_path", ""+ results.getPoster_path());
////                intent.putExtra("overview", ""+ results.getOverview());
////                intent.putExtra("rating", ""+ results.getVote_average());
////                intent.putExtra("genre", ""+ results.getGenre_ids());
////                intent.putExtra("img_backdrop", ""+ results.getBackdrop_path());
////                intent.putExtra("movieId", ""+ results.getId());
////                List <Integer> listGenre = results.getGenre_ids();
////                for (int i = 0; i < listGenre.size(); i++) {
////                    Movies.Genres genreMovie = new Movies.Genres();
////                    genreMovie.setId(i);
////                    genreMovie.getName();
////                    String name = String.valueOf(listGenre.get(i));
////
////                }
////                context.startActivity(intent);
//                Bundle bundle = new Bundle();
//                bundle.putString("movieId", "" + results.getId());
//                Navigation.findNavController(view).navigate(R.id.action_nowPlayingFragment_to_MovieDetailsFragment, bundle);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return getListNowPlaying().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder{
        ImageView img_poster;
        TextView lbl_title, lbl_overview, lbl_release_date;
        CardView cv;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            img_poster = itemView.findViewById(R.id.img_poster_card_now_playing);
            lbl_title = itemView.findViewById(R.id.lbl_title_card_now_playing);
            lbl_overview = itemView.findViewById(R.id.lbl_overview_card_now_playing);
            lbl_release_date = itemView.findViewById(R.id.lbl_releasedate_card_now_playing);
            cv = itemView.findViewById(R.id.cv_card_now_playing);
        }
    }
}
