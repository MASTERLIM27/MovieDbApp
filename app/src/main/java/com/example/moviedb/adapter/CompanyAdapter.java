package com.example.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CardViewViewHolder>{

    private Context context;
    public CompanyAdapter(Context context){
        this.context = context;
    }
    private List<Movies.ProductionCompanies> listCompany;
    private List<Movies.ProductionCompanies>getListCompany(){
        return listCompany;
    }
    public void setListCompany(List<Movies.ProductionCompanies> listCompany){
        this.listCompany = listCompany;
    }

    @NonNull
    @Override
    public CompanyAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_company, parent,false);
        return new CompanyAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyAdapter.CardViewViewHolder holder, int position) {
        final Movies.ProductionCompanies movieCompany = getListCompany().get(position);
        holder.textView_card_company.setText(movieCompany.getName());
        Glide.with(context).load(Const.IMG_URL + movieCompany.getLogo_path()).into(holder.img_card_company);

        holder.cv_card_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, movieCompany.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListCompany().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView img_card_company;
        TextView textView_card_company;
        CardView cv_card_company;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            img_card_company = itemView.findViewById(R.id.img_card_company);
            textView_card_company = itemView.findViewById(R.id.textView_card_company);
            cv_card_company = itemView.findViewById(R.id.cv_card_company);
        }
    }
}
