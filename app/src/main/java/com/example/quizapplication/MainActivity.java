package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTV;
    TextView questionTV;
    Button ansA,ansB,ansC,ansD;
    Button submitButton;

    int score = 0;
    int totalQuestions = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTV = findViewById(R.id.total_question);
        questionTV = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitButton = findViewById(R.id.submit_button);

        questionTV.setOnClickListener(this);
        ansA .setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC .setOnClickListener(this);
        ansD .setOnClickListener(this);
        submitButton .setOnClickListener(this);

        totalQuestionsTV.setText("Total Questions: " + totalQuestions);

        loadNewQuestion();
    }



    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_button)
        {
            if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex]))
            {
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();

        }
        else
        {
            //choices button clicked
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }

    void loadNewQuestion()
    {
        if(currentQuestionIndex == totalQuestions)
        {
            finishQuiz();
            return;
        }
        questionTV.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

    }

    void finishQuiz()
    {
        String passStatus = "";
        if(score > totalQuestions*0.50)
        {
            passStatus = "You Passed! :)";
        }
        else
        {
            passStatus = "Sorry, You Failed :(";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("You got " + score + " out of " + totalQuestions + " correct.")
                .setPositiveButton("Restart",((dialogInterface, i) -> restartQuiz()))
                .setCancelable(false)
                .show();
    }

    void restartQuiz()
    {
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }

}