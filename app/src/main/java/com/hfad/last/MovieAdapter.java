package com.hfad.last;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter <MovieAdapter.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView ;
        public ViewHolder (CardView v){
            super(v);
            cardView = v;
        }
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        CardView cardView = holder.cardView;
        TextView name = (TextView) cardView.findViewById(R.id.movie_name);
        TextView realised_data = (TextView) cardView.findViewById(R.id.movie_realised_data);
        TextView vote_average = (TextView) cardView.findViewById(R.id.movie_vote);
        name.setText(MainActivity.myMovie.getMyList().get(position).getTitle());
        realised_data.setText(MainActivity.myMovie.getMyList().get(position).getReleaseDate());
        Picasso.get().load("https://image.tmdb.org/t/p/w185" + MainActivity.myMovie.getMyList().get(position).getPosterPath()).into(holder.cardView.<ImageView>findViewById(R.id.movie_image));
        vote_average.setText(MainActivity.myMovie.getMyList().get(position).getVoteAverage().toString());
        if (position>=(MainActivity.pos*10*2-5)){
            MainActivity.pos +=1;
        }

       holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Details_Activity.class);
                intent.putExtra("id",MainActivity.myMovie.getMyList().get(position).getId());
                intent.putExtra("name",MainActivity.myMovie.getMyList().get(position).getTitle());
                v.getContext().startActivity(intent);
            }

        });


    }

    @Override
    public int getItemCount() {
        return MainActivity.myMovie.getMyList().size();
    }

   }
