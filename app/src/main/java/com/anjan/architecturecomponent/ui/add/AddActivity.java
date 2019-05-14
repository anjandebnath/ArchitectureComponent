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


import org.apache.commons.text.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

public class AddActivity extends AppCompatActivity {

    EditText editTextEmoji;
    EditText editTextEmojiText;
    Button buttonSave;

    AddMoviesViewModel addMoviesViewModel;
    int directorId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editTextEmoji = findViewById(R.id.editText_emoji);
        editTextEmojiText = findViewById(R.id.editText_emojival);
        buttonSave = findViewById(R.id.button_save);

        addMoviesViewModel = ViewModelProviders.of(this).get(AddMoviesViewModel.class);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String movie = StringEscapeUtils.escapeJava(editTextEmoji.getEditableText().toString());
                String director = editTextEmojiText.getEditableText().toString();


                String unicodeMovie = utf8decode(movie);
                DirectorEntity directorEntity = new DirectorEntity(director);

                //check duplicate id can not be inserted
                directorId = addMoviesViewModel.findDirector(director);
                if( directorId < 0){
                    directorId = (int) addMoviesViewModel.insertDirector(directorEntity);
                }
                MovieEntity movieEntity = new MovieEntity(unicodeMovie, directorId);
                addMoviesViewModel.insertMovie(movieEntity);


                editTextEmoji.setText("");
                editTextEmojiText.setText("");
            }
        });

    }



    public String utf8decode(String utf16String) {

        String text = new String(utf16String.getBytes(), StandardCharsets.UTF_8);

        int codepoint = text.codePointAt(0);

        String unicodeString ="U+"+Integer.toHexString(codepoint);

        return unicodeString;
    }

}
