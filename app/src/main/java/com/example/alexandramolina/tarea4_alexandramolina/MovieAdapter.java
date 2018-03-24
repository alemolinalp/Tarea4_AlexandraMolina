package com.example.alexandramolina.tarea4_alexandramolina;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by alexandramolina on 23/3/18.
 */

public class MovieAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Movie> arrayList;

    public MovieAdapter(Context context, int layout, ArrayList<Movie> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView txt_name, txt_rating, txt_metascore;
        RatingBar ratingBar;
        ImageView imageView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;

        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout,null);
            viewHolder.txt_name = view.findViewById(R.id.txt_name);
            viewHolder.txt_rating = view.findViewById(R.id.txt_rating);
            viewHolder.txt_metascore = view.findViewById(R.id.txt_metascore);
            viewHolder.ratingBar = view.findViewById(R.id.ratingBar);
            viewHolder.imageView = view.findViewById(R.id.imageView);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        final Movie movie = arrayList.get(i);
        viewHolder.txt_name.setText(movie.getName());
        viewHolder.txt_rating.setText(Float.toString(movie.getRating()));
        //viewHolder.txt_metascore.setText(Float.toString(movie.getMetascore()));
        viewHolder.ratingBar.setRating(Float.parseFloat("2.0"));
        //viewHolder.imageView.setImageBitmap(movie.getImage());

        return view;
    }

}
