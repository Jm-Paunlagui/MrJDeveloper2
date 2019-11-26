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

public class CodeHomeActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ_COD = 1;
    public static final String SHARED_PREFS_COD = "sharedPrefsCOD";
    public static final String KEY_HIGHSCORE_COD = "keyHighscoreCOD";
    private TextView textViewHighScoreCOD;
    private int highscoreCOD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_home_layout);

        LinearLayout linearLayout = findViewById(R.id.LLHomeCode);
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        Button buttonStartQuiz = findViewById(R.id.cod_button_start_quiz);

        textViewHighScoreCOD = findViewById(R.id.cod_text_view_highscore);
        loadHighscoreCOD();

        if (highscoreCOD <= 4){
            Toast.makeText(this, "Practice makes it perfect", Toast.LENGTH_SHORT).show();
        }
        else if (highscoreCOD > 5 && highscoreCOD < 7){
            Toast.makeText(this, "Can we make a perfect score?", Toast.LENGTH_SHORT).show();
        }
        else if (highscoreCOD == 8 || highscoreCOD == 9){
            Toast.makeText(this, "That's all you got?", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Keep it up!", Toast.LENGTH_SHORT).show();
        }

        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startcodQuiz();
            }
        });
    }

    private void startcodQuiz() {
        Intent C = new Intent(this, CodeQuizActivity.class);
        startActivityForResult(C, REQUEST_CODE_QUIZ_COD);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ_COD) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(CodeQuizActivity.EXTRA_SCORE_COD, 0);
                if (score > highscoreCOD) {
                    updateHighscoreCOD(score);
                }
            }
        }
    }

    private void loadHighscoreCOD() {
        SharedPreferences prefsCOD = getSharedPreferences(SHARED_PREFS_COD, MODE_PRIVATE);
        highscoreCOD = prefsCOD.getInt(KEY_HIGHSCORE_COD, 0);
        textViewHighScoreCOD.setText("Highscore: " + highscoreCOD);
    }

    private void updateHighscoreCOD(int highscoreNewCOD) {
        highscoreCOD = highscoreNewCOD;
        textViewHighScoreCOD.setText("Highscore: " + highscoreCOD);

        SharedPreferences prefsCOD = getSharedPreferences(SHARED_PREFS_COD, MODE_PRIVATE);
        SharedPreferences.Editor editorCOD = prefsCOD.edit();
        editorCOD.putInt(KEY_HIGHSCORE_COD, highscoreCOD);
        editorCOD.apply();
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