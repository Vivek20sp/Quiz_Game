package com.example.mathsquizgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity
{
    TextView timer;
    TextView Score;
    TextView Question;
    TextView Result;
    Button FirstBtn;
    Button SecondBtn;
    Button ThirdBtn;
    Button FourthBtn;
    Button PlayAgain;
    ConstraintLayout first;
    ConstraintLayout Second;
    int correctAnswerLocation;
    ArrayList<Integer> arrayList;
    int score=0;
    int NoOfQuestions=0;
    public void OnStartClick(View view)
    {
        first.setVisibility(View.INVISIBLE);
        Second.setVisibility(View.VISIBLE);
        play();
    }

    private void play()
    {
        Second.setVisibility(View.VISIBLE);
        //setQuestion
        getNewQuestion();
        //set Score
        score=0;
        NoOfQuestions=0;
        getScore();
        //set empty value in the answer Text View
        getEmptyValue();
        //set timer
        getTimer();
    }

    private void getTimer()
    {
        new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished)
            {
                timer.setText(String.valueOf(millisUntilFinished/1000 + "s"));
            }
            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish()
            {
                Result.setText("GameOver!");
                PlayAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    private void getEmptyValue()
    {
        Result.setText(" ");
    }

    @SuppressLint("SetTextI18n")
    private void getScore()
    {
        Score.setText(Integer.toString(score)+"/"+Integer.toString(NoOfQuestions));
    }

    @SuppressLint("SetTextI18n")
    private void getNewQuestion()
    {
        Random random=new Random();
        int a=random.nextInt(30);
        int b=random.nextInt(30);
        Question.setText(Integer.toString(a)+"+"+Integer.toString(b)+"="+"?");
        correctAnswerLocation=random.nextInt(4);
        for(int i=0;i<4;i++)
        {
            if(i==correctAnswerLocation)
            {
                arrayList.add(a+b);
            }
            else
            {
                int wrongNumber=random.nextInt(61);
                while(wrongNumber==(a+b))
                {
                    wrongNumber=random.nextInt(61);
                }
                arrayList.add(wrongNumber);
            }
        }
        FirstBtn.setText(Integer.toString(arrayList.get(0)));
        SecondBtn.setText(Integer.toString(arrayList.get(1)));
        ThirdBtn.setText(Integer.toString(arrayList.get(2)));
        FourthBtn.setText(Integer.toString(arrayList.get(3)));
    }

    @SuppressLint("SetTextI18n")
    public void OnOptionClick(View view)
    {

        if(FirstBtn.getTag().toString().equals(Integer.toString(correctAnswerLocation)))
        {
            score++;
            NoOfQuestions++;
            Result.setVisibility(View.VISIBLE);
            Result.setText("Correct");
            getNewQuestion();
        }
        else
        {
            NoOfQuestions++;
            Result.setVisibility(View.VISIBLE);
            Result.setText("Wrong");
            getNewQuestion();
        }
        getScore();
    }
    public void OnClickPlayAgain(View view)
    {
        play();
        PlayAgain.setVisibility(View.INVISIBLE);
        getEmptyValue();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer=findViewById(R.id.timer);
        Score=findViewById(R.id.score);
        Question=findViewById(R.id.question);
        Result=findViewById(R.id.result);
        FirstBtn=findViewById(R.id.firstBtn);
        SecondBtn=findViewById(R.id.secondBtn);
        ThirdBtn=findViewById(R.id.thirdBtn);
        FourthBtn=findViewById(R.id.fourthBtn);
        PlayAgain=findViewById(R.id.playagain);
        first=findViewById(R.id.fisrtConstraintLayout);
        Second=findViewById(R.id.secondConstraintLayout);
        first.setVisibility(View.VISIBLE);
        Second.setVisibility(View.INVISIBLE);
        Result.setVisibility(View.INVISIBLE);
        arrayList=new ArrayList<>();
    }
}