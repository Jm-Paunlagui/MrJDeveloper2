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

public class CodeQuizActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE_COD = "extraScoreCOD";
    private static final long COUNTDOWN_IN_MILLIS_COD = 300000;
    private static final String KEY_SCORE_COD = "keyScore_code";
    private static final String KEY_QUESTION_COUNT_COD = "keyQuestionCount_code";
    private static final String KEY_MILLIS_LEFT_COD = "keyMillisLeft_code";
    private static final String KEY_ANSWERED_COD = "keyAnswered_code";
    private static final String KEY_QUESTION_LIST_COD = "keyQuestionList_code";
    private TextView textViewQuestionCOD;
    private TextView textViewScoreCOD;
    private TextView textViewQuestionCountCOD;
    private TextView textViewCountDownCOD;
    private RadioGroup radioGroupCOD;
    private RadioButton rb1COD;
    private RadioButton rb2COD;
    private RadioButton rb3COD;
    private RadioButton rb4COD;
    private Button buttonConfirmNextCOD;
    private ColorStateList textColorDefaultRbCOD;
    private CountDownTimer countDownTimerCOD;
    private long timeLeftInMillisCOD;
    private ArrayList<QuestionCOD> questionCODList;
    private int questionCounterCOD;
    private int questionCountTotalCOD;
    private QuestionCOD currentQuestionCOD;
    private int scoreCOD;
    private boolean answeredCOD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_code_quiz_layout);
        textViewQuestionCOD = findViewById(R.id.cod_text_view_question);
        textViewScoreCOD = findViewById(R.id.cod_text_view_score);
        textViewQuestionCountCOD = findViewById(R.id.cod_text_view_question_count);
        textViewCountDownCOD = findViewById(R.id.cod_text_view_countdown);
        radioGroupCOD = findViewById(R.id.cod_radio_group);
        rb1COD = findViewById(R.id.cod_radio_button1);
        rb2COD = findViewById(R.id.cod_radio_button2);
        rb3COD = findViewById(R.id.cod_radio_button3);
        rb4COD = findViewById(R.id.cod_radio_button4);
        buttonConfirmNextCOD = findViewById(R.id.cod_button_confirm_next);
        textColorDefaultRbCOD = rb1COD.getTextColors();

        if (savedInstanceState == null) {
            QuizDbHelperCOD dbHelperCOD = new QuizDbHelperCOD(this);
            questionCODList = dbHelperCOD.getAllQuestionsCOD();
            questionCountTotalCOD = questionCODList.size();
            Collections.shuffle(questionCODList);

            showNextQuestionCOD();
        } else {
            questionCODList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST_COD);
            questionCountTotalCOD = questionCODList.size();
            questionCounterCOD = savedInstanceState.getInt(KEY_QUESTION_COUNT_COD);
            currentQuestionCOD = questionCODList.get(questionCounterCOD - 1);
            scoreCOD = savedInstanceState.getInt(KEY_SCORE_COD);
            timeLeftInMillisCOD = savedInstanceState.getLong(KEY_MILLIS_LEFT_COD);
            answeredCOD = savedInstanceState.getBoolean(KEY_ANSWERED_COD);

            if (!answeredCOD) {
                startCountDownCOD();
            } else {
                updateCountDownTextCOD();
                showSolutionCOD();
            }
        }

        buttonConfirmNextCOD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answeredCOD) {
                    if (rb1COD.isChecked() || rb2COD.isChecked() || rb3COD.isChecked() || rb4COD.isChecked()) {
                        checkAnswerCOD();
                    } else {
                       ConfirmAnsCode();
                    }
                } else {
                    showNextQuestionCOD();
                }
            }
        });
    }
    private void ConfirmAnsCode(){
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

    private void showNextQuestionCOD() {
        rb1COD.setTextColor(textColorDefaultRbCOD);
        rb2COD.setTextColor(textColorDefaultRbCOD);
        rb3COD.setTextColor(textColorDefaultRbCOD);
        rb4COD.setTextColor(textColorDefaultRbCOD);
        radioGroupCOD.clearCheck();

        if (questionCounterCOD < questionCountTotalCOD) {
            currentQuestionCOD = questionCODList.get(questionCounterCOD);

            textViewQuestionCOD.setText(currentQuestionCOD.getCODquestion());
            rb1COD.setText(currentQuestionCOD.getCODoption1());
            rb2COD.setText(currentQuestionCOD.getCODoption2());
            rb3COD.setText(currentQuestionCOD.getCODoption3());
            rb4COD.setText(currentQuestionCOD.getCODoption4());

            questionCounterCOD++;
            textViewQuestionCountCOD.setText("Question: " + questionCounterCOD + "/" + questionCountTotalCOD);
            answeredCOD = false;
            buttonConfirmNextCOD.setText("Confirm!");

            timeLeftInMillisCOD = COUNTDOWN_IN_MILLIS_COD;
            startCountDownCOD();
        } else {
            finishQuizCOD();
        }
    }

    private void startCountDownCOD() {
        countDownTimerCOD = new CountDownTimer(timeLeftInMillisCOD, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillisCOD = millisUntilFinished;
                updateCountDownTextCOD();
            }

            @Override
            public void onFinish() {
                timeLeftInMillisCOD = 0;
                updateCountDownTextCOD();
                checkAnswerCOD();
            }
        }.start();
    }

    private void updateCountDownTextCOD() {
        int minutesADV = (int) (timeLeftInMillisCOD / 1000) / 60;
        int secondsADV = (int) (timeLeftInMillisCOD / 1000) % 60;

        String timeFormattedADV = String.format(Locale.getDefault(), "%02d:%02d", minutesADV, secondsADV);

        textViewCountDownCOD.setText(timeFormattedADV);

        if (timeLeftInMillisCOD < 10000) {
            textViewCountDownCOD.setTextColor(Color.RED);

        } else {
            textViewCountDownCOD.setTextColor(Color.BLUE);
        }
    }

    private void checkAnswerCOD() {
        answeredCOD = true;

        countDownTimerCOD.cancel();

        RadioButton rbSelectedADV = findViewById(radioGroupCOD.getCheckedRadioButtonId());
        int answerNrADV = radioGroupCOD.indexOfChild(rbSelectedADV) + 1;

        if (answerNrADV == currentQuestionCOD.getCODanswerNr()) {
            scoreCOD++;
            textViewScoreCOD.setText("Score: " + scoreCOD);
        }
        showSolutionCOD();
    }

    private void showSolutionCOD() {
        rb1COD.setTextColor(Color.RED);
        rb2COD.setTextColor(Color.RED);
        rb3COD.setTextColor(Color.RED);
        rb4COD.setTextColor(Color.RED);

        switch (currentQuestionCOD.getCODanswerNr()) {
            case 1:
                rb1COD.setTextColor(Color.GREEN);
                textViewQuestionCOD.setText("Answer, A is CORRECT!");
                break;
            case 2:
                rb2COD.setTextColor(Color.GREEN);
                textViewQuestionCOD.setText("Answer, B is CORRECT!");
                break;
            case 3:
                rb3COD.setTextColor(Color.GREEN);
                textViewQuestionCOD.setText("Answer, C is CORRECT!");
                break;
            case 4:
                rb4COD.setTextColor(Color.GREEN);
                textViewQuestionCOD.setText("Answer, D is CORRECT!");
                break;
        }
        if (questionCounterCOD < questionCountTotalCOD) {
            buttonConfirmNextCOD.setText("NEXT");

        } else {
            buttonConfirmNextCOD.setText("FINISH");
            if (scoreCOD <= 4){
                Toast.makeText(this, "Practice makes it perfect", Toast.LENGTH_SHORT).show();
            }
            else if (scoreCOD > 5 && scoreCOD < 7){
                Toast.makeText(this, "Can we make a perfect score?", Toast.LENGTH_SHORT).show();
            }
            else if (scoreCOD == 8 || scoreCOD == 9){
                Toast.makeText(this, "That's all you got?", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Keep it up!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void finishQuizCOD() {
        Intent ResultIntentCOD = new Intent();
        ResultIntentCOD.putExtra(EXTRA_SCORE_COD, scoreCOD);
        setResult(RESULT_OK, ResultIntentCOD);
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
                Chome();
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
    protected void Chome(){
        Intent CH = new Intent(this,CodeHomeActivity.class);
        CH.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(CH);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimerCOD != null) {
            countDownTimerCOD.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE_COD, scoreCOD);
        outState.putInt(KEY_QUESTION_COUNT_COD, questionCounterCOD);
        outState.putLong(KEY_MILLIS_LEFT_COD, timeLeftInMillisCOD);
        outState.putBoolean(KEY_ANSWERED_COD, answeredCOD);
        outState.putParcelableArrayList(KEY_QUESTION_LIST_COD, questionCODList);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}