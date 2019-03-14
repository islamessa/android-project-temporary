package com.example.buildstoryapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.buildstoryapp.R;

public class MainActivity extends AppCompatActivity {

    private Button startStoryButton;
    private Context context;
    private EditText textName;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textName = findViewById(R.id.editText);
        context = this;
        startStoryButton = (Button)findViewById(R.id.startstorybut);
        startStoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = textName.getText().toString();
                Intent intent = new Intent(context, StoryActivity.class);
                intent.putExtra("username",name);
                startActivity(intent);
            }
        });

    }
}
