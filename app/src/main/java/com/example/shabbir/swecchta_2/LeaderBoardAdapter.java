package com.example.shabbir.swecchta_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;



public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.MyViewHolder>  {

    private List<LeaderBoard> postList;
    int i;



    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title,score;
        public ImageView imageView;
        public Button bt1,bt2;
        public MyViewHolder(View view){
            super(view);
            title = (TextView)view.findViewById(R.id.name);
            score = (TextView)view.findViewById(R.id.score);
            imageView = (ImageView)view.findViewById(R.id.postimage);
//            foodType = (ImageView)view.findViewById(R.id.foodType);
//            Typeface custom_font = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/pacifico.ttf");
//            title.setTypeface(custom_font);

        }
    }

    public LeaderBoardAdapter(List<LeaderBoard> post){
        postList = post;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.leaderboard,parent,false);

        final MyViewHolder myViewHolder = new MyViewHolder(view);
        return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        i=position;
        LeaderBoard post = postList.get(position);
//        String f = post.getFood_type();

        holder.title.setText(post.getName());
        holder.score.setText("Score : "+ post.getScore());
        Picasso.get()
                .load(post.getImage())
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.error)
                .fit()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }




}

