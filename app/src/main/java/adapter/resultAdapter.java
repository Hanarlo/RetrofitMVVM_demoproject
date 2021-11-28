package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitmvvmdemoproject.R;
import model.Result;
import com.example.retrofitmvvmdemoproject.movie_info;

import java.util.ArrayList;

public class resultAdapter extends RecyclerView.Adapter<resultAdapter.ResultViewHolder> {
    private Context context;
    private ArrayList<Result> result;

    public resultAdapter(Context context, ArrayList<Result> result) {
        this.context = context;
        this.result = result;
    }


    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resultlistitem,parent,false);

        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        holder.titleTV.setText(result.get(position).getOriginalTitle());
        holder.popularityTV.setText(result.get(position).getPopularity().toString());
        String imagePath = "https://image.tmdb.org/t/p/w500/" + result.get(position).getPosterPath();
        Glide.with(context).load(imagePath).placeholder(R.drawable.progress_circle).into(holder.movieIV);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder{

        public TextView titleTV;
        public TextView popularityTV;
        public ImageView movieIV;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTV = itemView.findViewById(R.id.TitleTV);

            popularityTV = itemView.findViewById(R.id.popularityTV);

            movieIV = itemView.findViewById(R.id.MovieImageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        Result result2 = result.get(position);
                        Intent intent = new Intent(context, movie_info.class).putExtra("movie_data", result2);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
