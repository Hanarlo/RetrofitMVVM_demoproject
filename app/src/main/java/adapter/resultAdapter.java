package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitmvvmdemoproject.R;
import com.example.retrofitmvvmdemoproject.databinding.ResultlistitemBinding;
import com.example.retrofitmvvmdemoproject.model.Result;
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
        ResultlistitemBinding resultlistitemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                ,R.layout.resultlistitem,parent,false);

        return new ResultViewHolder(resultlistitemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {

        Result result2 = result.get(position);


        holder.resultlistitemBinding.setResult(result2);

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder{

        private ResultlistitemBinding resultlistitemBinding;


        public ResultViewHolder(@NonNull ResultlistitemBinding resultlistitemBinding) {
            super(resultlistitemBinding.getRoot());
            this.resultlistitemBinding = resultlistitemBinding;

            resultlistitemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
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
