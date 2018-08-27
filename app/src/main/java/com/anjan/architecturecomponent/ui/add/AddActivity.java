package com.anjan.architecturecomponent.ui.add;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.anjan.architecturecomponent.R;
import com.anjan.architecturecomponent.entity.DirectorEntity;
import com.anjan.architecturecomponent.entity.MovieEntity;
import com.anjan.architecturecomponent.ui.main.MoviesViewModel;

public class AddActivity extends AppCompatActivity {

    EditText editTextMovie;
    EditText editTextDirector;
    Button buttonSave;

    AddMoviesViewModel addMoviesViewModel;
    int directorId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editTextMovie = findViewById(R.id.editText_movie);
        editTextDirector = findViewById(R.id.editText_director);
        buttonSave = findViewById(R.id.button_save);

        addMoviesViewModel = ViewModelProviders.of(this).get(AddMoviesViewModel.class);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String movie = editTextMovie.getEditableText().toString();
                String director = editTextDirector.getEditableText().toString();

                // to insert data to firebase
                addMoviesViewModel.insertToFirebaseCloud(movie, director);

                // to insert data to room
                /*DirectorEntity directorEntity = new DirectorEntity(director);

                //check duplicate id can not be inserted
                directorId = addMoviesViewModel.findDirector(director);
                if( directorId < 0){
                    directorId = (int) addMoviesViewModel.insertDirector(directorEntity);
                }
                MovieEntity movieEntity = new MovieEntity(movie, directorId);
                addMoviesViewModel.insertMovie(movieEntity);*/


                editTextMovie.setText("");
                editTextDirector.setText("");
            }
        });

    }

}
