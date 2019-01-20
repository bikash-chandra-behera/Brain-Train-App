package com.example.myapplication;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView answerText;
    TextView pointText;
    TextView sumText;
    TextView timeText;
    TextView scorTextBtn;
    Button playAgainBtn;
    RelativeLayout gameRelativeLayout;
    LinearLayout playAgainLayout;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int randAnswerPosition;
    int numberOfQuestion=0;


    int score;

    public void start(View view){
        playAgain(playAgainBtn);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.INVISIBLE);

    }
    public  void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(randAnswerPosition))){
            Log.i("Tag","Currect Answer");
            answerText.setText("Currect Answer!");
            score++;

        }else {
            answerText.setText("Wrong!");
            Log.i("Tag","Incurrect Answer");
        }
        numberOfQuestion++;
        pointText.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
        generateQuestion();
    }
    public void playAgain(View view){
        gameRelativeLayout.setVisibility(View.VISIBLE);
        score = 0;
        numberOfQuestion = 0;
        pointText.setText("0/0");
        timeText.setText("30s");
        generateQuestion();
        playAgainLayout.setVisibility(View.INVISIBLE);
        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                answerText.setText("Done");
                gameRelativeLayout.setVisibility(View.INVISIBLE);

                playAgainLayout.setVisibility(View.VISIBLE);
                scorTextBtn.setText("Your Score: "+ Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
            }
        }.start();
    }
    public void generateQuestion(){
        Random rand = new Random();
        int level = 10;

        int a  =  rand.nextInt(level);
        int b  =  rand.nextInt(level);
        sumText.setText(Integer.toString(a) + " + " + Integer.toString(b));
        randAnswerPosition = rand.nextInt(4);
        answer.clear();
        int incorrectAnswer;
        for (int i = 0; i < 4; i++){
            if(i == randAnswerPosition){
                answer.add(a+b);
            }else {

                incorrectAnswer = rand.nextInt(level*2);
                while(incorrectAnswer == a+b){
                    incorrectAnswer = rand.nextInt(level*2);
                }
                answer.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.go);
        sumText = (TextView)findViewById(R.id.sumText);
        answerText = (TextView)findViewById(R.id.answerTextView);
        pointText = (TextView)findViewById(R.id.scoreText);
        timeText = (TextView)findViewById(R.id.timeTextView);
        scorTextBtn = (TextView)findViewById(R.id.scoreTextBtn);
        playAgainBtn = (Button)findViewById(R.id.playAgainButton);
        button0 = (Button)findViewById(R.id.oneBtn);
        button1 = (Button)findViewById(R.id.twoBtn);
        button2 = (Button)findViewById(R.id.threeBtn);
        button3 = (Button)findViewById(R.id.fourBtn);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);
        playAgainLayout = findViewById(R.id.playAgainLayout);






    }


}
