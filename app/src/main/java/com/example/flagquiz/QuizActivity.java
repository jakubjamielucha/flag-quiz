package com.example.flagquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewCorrect, textViewWrong, textViewEmpty, textViewQuestion;
    private ImageView imageViewFlag, imageViewNext;
    private Button buttonA, buttonB, buttonC, buttonD;

    private FlagsDatabase fdatabase;
    private ArrayList<FlagsModel> questionsList;

    int correct = 0, wrong = 0, empty = 0, question = 0;

    private FlagsModel correctFlag;
    private ArrayList<FlagsModel> wrongOptionsList;

    HashSet<FlagsModel> mixOptions = new HashSet<>();

    ArrayList<FlagsModel> options = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewCorrect = findViewById(R.id.textViewCorrect);
        textViewWrong = findViewById(R.id.textViewWrong);
        textViewEmpty = findViewById(R.id.textViewEmpty);

        imageViewFlag = findViewById(R.id.imageViewFlag);
        imageViewNext = findViewById(R.id.imageViewNext);

        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);

        fdatabase = new FlagsDatabase(QuizActivity.this);
        questionsList = new FlagsDAO().getRandomTenQuestion(fdatabase);

        loadQuestions();

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        imageViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question++;
                loadQuestions();
            }
        });
    }

    public void loadQuestions(){
        textViewQuestion.setText("Question: " + question+1);
        correctFlag = questionsList.get(question);

        imageViewFlag.setImageResource(getResources().getIdentifier(correctFlag.getFlag_image(),
                "drawable", getPackageName()));
        wrongOptionsList = new FlagsDAO().getRandomThreeOptions(fdatabase, correctFlag.getFlag_id());
        mixOptions.clear();
        mixOptions.add(correctFlag);
        mixOptions.add(wrongOptionsList.get(0));
        mixOptions.add(wrongOptionsList.get(1));
        mixOptions.add(wrongOptionsList.get(2));

        options.clear();
        for(FlagsModel flg: mixOptions){
            options.add(flg);
        }
        buttonA.setText(options.get(0).getFlag_name());
        buttonB.setText(options.get(1).getFlag_name());
        buttonC.setText(options.get(2).getFlag_name());
        buttonD.setText(options.get(3).getFlag_name());


    }
}