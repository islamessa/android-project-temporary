package com.example.buildstoryapp.ui;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buildstoryapp.R;
import com.example.buildstoryapp.model.Page;
import com.example.buildstoryapp.model.Story;

public class StoryActivity extends AppCompatActivity {

    private Context context;
    private Button butChoice1;
    private Button butChoice2;
    private ImageView imagePage;
    private TextView textPage;
    private Story story;
    private int currentPage = 0;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        context = this;
        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        this.name = name;
        butChoice1 = findViewById(R.id.buttonChoice1);
        butChoice2 = findViewById(R.id.buttonChoice2);
        imagePage =  findViewById(R.id.imageView3);
        textPage = findViewById(R.id.textView);

        story = new Story();
        fillPage(0);

        butChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Page Current_Page = story.getPage(currentPage);
                if(!Current_Page.isFinalPage())
                    currentPage = Current_Page.getChoice1().getNextPage();
                fillPage(currentPage);

            }
        });

        butChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Page Current_Page = story.getPage(currentPage);
                if(!Current_Page.isFinalPage())
                currentPage = Current_Page.getChoice2().getNextPage();
                  fillPage(currentPage);
                  if(Current_Page.isFinalPage()){
                      fillPage(0);
                      currentPage = 0;
                      fillPage(currentPage);
                  }
            }
        });
    }

    public void fillPage(int pageNum) {
        if (pageNum < 5) {
            butChoice1.setVisibility(View.VISIBLE);
            Page currentPage = story.getPage(pageNum);

            String pageText = getString(currentPage.getTextId());
            // Add name if placeholder included. Won't add if not
            pageText = String.format(pageText, name);
            textPage.setText(pageText);

            imagePage.setImageResource(currentPage.getImageId());
            butChoice1.setText(currentPage.getChoice1().getTextId());
            butChoice2.setText(currentPage.getChoice2().getTextId());
        }else{
            Page currentPage = story.getPage(pageNum);

            String pageText = getString(currentPage.getTextId());
            // Add name if placeholder included. Won't add if not
            pageText = String.format(pageText, name);
            textPage.setText(pageText);
            imagePage.setImageResource(currentPage.getImageId());
            butChoice1.setVisibility(View.INVISIBLE);
            butChoice2.setText("Play Again");
            currentPage.setFinalPage(true);
        }

    }
}
