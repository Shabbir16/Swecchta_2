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






public class DoesDontAdapter extends RecyclerView.Adapter<DoesDontAdapter.MyViewHolder>  {

    private List<String> postList;
    int i;



    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title,score;
        public ImageView imageView;
        public Button bt1,bt2;
        public MyViewHolder(View view){
            super(view);
            title = (TextView)view.findViewById(R.id.notify);

        }
    }

    public DoesDontAdapter(List<String> post){
        postList = post;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.doesdont,parent,false);

        final MyViewHolder myViewHolder = new MyViewHolder(view);
        return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        i=position;
        String post = postList.get(position);
//        String f = post.getFood_type();

        holder.title.setText(post);


    }

    @Override
    public int getItemCount() {
        return postList.size();
    }




}

