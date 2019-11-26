package com.example.jmepaunlagui.mrjdeveloper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class StartingScreenAtivity extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";
    private TextView textViewHighscore;
    private int highscore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen_ativity);

        LinearLayout linearLayoutV = findViewById(R.id.LLHomeAdv);
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayoutV.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        textViewHighscore = findViewById(R.id.text_view_highscore);
        loadHighscore();

        if (highscore <= 7){
            Toast.makeText(this, "Practice makes it perfect", Toast.LENGTH_SHORT).show();
        }
        else if (highscore > 8 && highscore < 12){
            Toast.makeText(this, "Can we make a perfect score?", Toast.LENGTH_SHORT).show();
        }
        else if (highscore == 13 || highscore == 14){
            Toast.makeText(this, "That's all you got?", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Keep it up!", Toast.LENGTH_SHORT).show();
        }

        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    private void startQuiz() {
        Intent intent = new Intent(StartingScreenAtivity.this, QuizActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
                if (score > highscore) {
                    updateHighscore(score);
                }
            }
        }
    }

    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighscore.setText("Highscore: " + highscore);
    }

    private void updateHighscore(int highscoreNew) {
        highscore = highscoreNew;
        textViewHighscore.setText("Highscore: " + highscore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder backpress = new AlertDialog.Builder(this);

        backpress.setCancelable(true);
        backpress.setTitle("Confirm Exit");
        backpress.setMessage("Do you want to exit?");

        backpress.setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GotoHomeActitvity();
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

    protected void GotoHomeActitvity(){
        Intent H = new Intent(this,HomeActivity.class);
        H.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(H);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}