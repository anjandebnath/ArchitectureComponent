package com.anjan.architecturecomponent.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anjan.architecturecomponent.MoviesDatabase;
import com.anjan.architecturecomponent.R;
import com.anjan.architecturecomponent.entity.DirectorEntity;
import com.anjan.architecturecomponent.entity.MovieEntity;

import java.util.List;

/**
 * Created by Anjan Debnath on 8/17/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MoviesViewHolder> {
    private LayoutInflater layoutInflater;
    private List<MovieEntity> movieList;
    private Context context;

    public MoviesListAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setMovieList(List<MovieEntity> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = layoutInflater.inflate(R.layout.item_list_movie, parent, false);
        return new MoviesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        if (movieList == null) {
            return;
        }

        final MovieEntity movie = movieList.get(position);
        if (movie != null) {

             // this will replace U+ with 0x
             // U+1F601 will be presented as 0x1F601
             String emoji = replaceUwith0xfromUnicode(movie.movieName);

             // we need to use decode method to convert String decimal to integer DecimalNumeral
            int decimal_emoji = Integer.decode(emoji);
            System.out.println("Hex value is " + decimal_emoji);


            holder.emoji.setText(getEmoji(decimal_emoji));

            final DirectorEntity director = MoviesDatabase.getDatabase(context).directorDao().findDirectorById(movie.directorId);
            final String directorFullName;
            if (director != null) {



                holder.emojiDetail.setText(director.fullName);
                directorFullName = director.fullName;
            } else {
                directorFullName = "";
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (movieList == null) {
            return 0;
        } else {
            return movieList.size();
        }
    }

    static class MoviesViewHolder extends RecyclerView.ViewHolder {
        private TextView emojiDetail;
        private TextView emoji;

        public MoviesViewHolder(View itemView) {
            super(itemView);

            emojiDetail = itemView.findViewById(R.id.tv_emoji_detail);
            emoji = itemView.findViewById(R.id.tv_emoji);
        }
    }

    private static String replaceUwith0xfromUnicode(String content) {
        content = content.replaceAll("U\\+", "0x");
        String keyword = "0x";

        int index = content.indexOf(keyword);
        int spaceIndex;

        while (index >=0){
            spaceIndex = content.indexOf(" ", index);

            if(spaceIndex > index) {
                String emoji = content.substring(index, spaceIndex);
                content = content.replaceAll(emoji, getEmoji(Integer.decode(emoji)));
            }
            index = content.indexOf(keyword, index+keyword.length());
        }

        return content;
    }


    public static String getEmoji(int unicode){
        return new String(Character.toChars(unicode));
    }
}
