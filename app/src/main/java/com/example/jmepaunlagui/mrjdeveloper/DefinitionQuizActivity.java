package com.example.jmepaunlagui.mrjdeveloper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class DefinitionQuizActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE_DEF = "extraScoreDef";
    private static final long COUNTDOWN_IN_MILLIS_DEF = 60000;
    private static final String KEY_SCORE_DEF = "keyScore";
    private static final String KEY_QUESTION_COUNT_DEF = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT_DEF = "keyMillisLeft";
    private static final String KEY_ANSWERED_DEF = "keyAnswered";
    private static final String KEY_QUESTION_LIST_DEF = "keyQuestionList";
    private TextView textViewQuestionDEF;
    private TextView textViewScoreDEF;
    private TextView textViewQuestionCountDEF;
    private TextView textViewCountDownDEF;
    private RadioGroup rbGroupDEF;
    private RadioButton rb1DEF;
    private RadioButton rb2DEF;
    private RadioButton rb3DEF;
    private RadioButton rb4DEF;
    private Button buttonConfirmNextDEF;
    private ColorStateList textColorDefaultRbDEF;
    private CountDownTimer countDownTimerDEF;
    private long timeLeftInMillisDEF;
    private ArrayList<QuestionDEF> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private QuestionDEF currentQuestion;
    private int scoreDEF;
    private boolean answeredDEF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_definition_quiz_layout);
        textViewQuestionDEF = findViewById(R.id.def_text_view_question);
        textViewScoreDEF = findViewById(R.id.def_text_view_score);
        textViewQuestionCountDEF = findViewById(R.id.def_text_view_question_count);
        textViewCountDownDEF = findViewById(R.id.def_text_view_countdown);
        rbGroupDEF = findViewById(R.id.def_radio_group);
        rb1DEF = findViewById(R.id.def_radio_button1);
        rb2DEF = findViewById(R.id.def_radio_button2);
        rb3DEF = findViewById(R.id.def_radio_button3);
        rb4DEF = findViewById(R.id.def_radio_button4);
        buttonConfirmNextDEF = findViewById(R.id.def_button_confirm_next);
        textColorDefaultRbDEF = rb1DEF.getTextColors();

        if (savedInstanceState == null) {
            QuizDbHelperDEF dbHelper = new QuizDbHelperDEF(this);
            questionList = dbHelper.getAllQuestions();
            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);

            showNextQuestion();
        } else {
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST_DEF);
            questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT_DEF);
            currentQuestion = questionList.get(questionCounter - 1);
            scoreDEF = savedInstanceState.getInt(KEY_SCORE_DEF);
            timeLeftInMillisDEF = savedInstanceState.getLong(KEY_MILLIS_LEFT_DEF);
            answeredDEF = savedInstanceState.getBoolean(KEY_ANSWERED_DEF);

            if (!answeredDEF) {
                startCountDown();
            } else {
                updateCountDownText();
                showSolution();
            }
        }

        buttonConfirmNextDEF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answeredDEF) {
                    if (rb1DEF.isChecked() || rb2DEF.isChecked() || rb3DEF.isChecked() || rb4DEF.isChecked()) {
                        checkAnswer();
                    } else {
                        ConfirmAnsDef();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }
    private void ConfirmAnsDef(){
        AlertDialog.Builder noans = new AlertDialog.Builder(this);

        noans.setTitle("Confirm Answer");
        noans.setMessage(" Please select an answer! \n Before the timer runs out!");

        noans.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        noans.show();
    }

    private void showNextQuestion() {
        rb1DEF.setTextColor(textColorDefaultRbDEF);
        rb2DEF.setTextColor(textColorDefaultRbDEF);
        rb3DEF.setTextColor(textColorDefaultRbDEF);
        rb4DEF.setTextColor(textColorDefaultRbDEF);
        rbGroupDEF.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestionDEF.setText(currentQuestion.getDEFquestion());
            rb1DEF.setText(currentQuestion.getDEFoption1());
            rb2DEF.setText(currentQuestion.getDEFoption2());
            rb3DEF.setText(currentQuestion.getDEFoption3());
            rb4DEF.setText(currentQuestion.getDEFoption4());

            questionCounter++;
            textViewQuestionCountDEF.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answeredDEF = false;
            buttonConfirmNextDEF.setText("Confirm");

            timeLeftInMillisDEF = COUNTDOWN_IN_MILLIS_DEF;
            startCountDown();
        } else {
            finishQuiz();
        }
    }

    private void startCountDown() {
        countDownTimerDEF = new CountDownTimer(timeLeftInMillisDEF, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillisDEF = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillisDEF = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillisDEF / 1000) / 60;
        int seconds = (int) (timeLeftInMillisDEF / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountDownDEF.setText(timeFormatted);

        if (timeLeftInMillisDEF < 10000) {
            textViewCountDownDEF.setTextColor(Color.RED);
        } else {
            textViewCountDownDEF.setTextColor(Color.BLUE);
        }
    }

    private void checkAnswer() {
        answeredDEF = true;

        countDownTimerDEF.cancel();

        RadioButton rbSelected = findViewById(rbGroupDEF.getCheckedRadioButtonId());
        int answerNr = rbGroupDEF.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getDEFanswerNr()) {
            scoreDEF++;
            textViewScoreDEF.setText("Score: " + scoreDEF);
        }

        showSolution();
    }

    private void showSolution() {
        rb1DEF.setTextColor(Color.RED);
        rb2DEF.setTextColor(Color.RED);
        rb3DEF.setTextColor(Color.RED);
        rb4DEF.setTextColor(Color.RED);


        switch (currentQuestion.getDEFanswerNr()) {
            case 1:
                rb1DEF.setTextColor(Color.GREEN);
                textViewQuestionDEF.setText("Answer A is correct");
                break;
            case 2:
                rb2DEF.setTextColor(Color.GREEN);
                textViewQuestionDEF.setText("Answer B is correct");
                break;
            case 3:
                rb3DEF.setTextColor(Color.GREEN);
                textViewQuestionDEF.setText("Answer C is correct");
                break;
            case 4:
                rb4DEF.setTextColor(Color.GREEN);
                textViewQuestionDEF.setText("Answer D is correct");
                break;
        }

        if (questionCounter < questionCountTotal) {
            buttonConfirmNextDEF.setText("Next");
        } else {
            buttonConfirmNextDEF.setText("Finish");
            if (scoreDEF <= 7){
                Toast.makeText(this, "Practice makes it perfect", Toast.LENGTH_SHORT).show();
            }
            else if (scoreDEF > 8 && scoreDEF < 12){
                Toast.makeText(this, "Can we make a perfect score?", Toast.LENGTH_SHORT).show();
            }
            else if (scoreDEF == 13 || scoreDEF == 14){
                Toast.makeText(this, "That's all you got?", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Keep it up!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void finishQuiz() {
        Intent resultDef = new Intent();
        resultDef.putExtra(EXTRA_SCORE_DEF, scoreDEF);
        setResult(RESULT_OK,resultDef);
        finish();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder backpress = new AlertDialog.Builder(this);

        backpress.setCancelable(true);
        backpress.setTitle("Confirm Quit");
        backpress.setMessage("Do you want to quit?");

        backpress.setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dhome();
                finish();
            }
        });
        backpress.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        backpress.show();
    }

    protected void Dhome(){
        Intent DH = new Intent(this,DefinitionHomeActivity.class);
        DH.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(DH);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimerDEF != null) {
            countDownTimerDEF.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE_DEF, scoreDEF);
        outState.putInt(KEY_QUESTION_COUNT_DEF, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT_DEF, timeLeftInMillisDEF);
        outState.putBoolean(KEY_ANSWERED_DEF, answeredDEF);
        outState.putParcelableArrayList(KEY_QUESTION_LIST_DEF, questionList);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}