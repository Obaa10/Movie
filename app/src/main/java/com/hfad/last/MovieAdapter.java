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
import java.util.ArrayList;


public class MovieAdapter extends RecyclerView.Adapter <MovieAdapter.ViewHolder> {

    private ArrayList<Movie> movies ;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView ;
        public ViewHolder (CardView v){
            super(v);
            cardView = v;
        }
    }
    public MovieAdapter (ArrayList<Movie> movies){
        this.movies=movies;
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
        ImageView image = (ImageView) cardView.findViewById(R.id.movie_image);
        name.setText(movies.get(position).getName());
        realised_data.setText(movies.get(position).getData());
        image.setImageBitmap(movies.get(position).getPoster_path());
        if (position>=(MainActivity.pos*10*2-5)){
            MainActivity.pos +=1;
        }

       holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),details.class);
                intent.putExtra("id",movies.get(position).getId());
                v.getContext().startActivity(intent);
            }

        });


    }



    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void update (ArrayList<Movie> moviess)
    {
        this.movies.addAll(moviess);

    }


}
