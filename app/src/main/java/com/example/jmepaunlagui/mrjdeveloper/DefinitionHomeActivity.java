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

public class DefinitionHomeActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ_DEF = 1;
    public static final String SHARED_PREFS_DEF = "sharedPrefsDef";
    public static final String KEY_HIGHSCORE_DEF = "keyHighScoreDef";
    private TextView textViewHighScoreDef;
    private int highscoreDef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition_home_layout);

        LinearLayout linearLayoutD = findViewById(R.id.LLHomeDef);
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayoutD.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        textViewHighScoreDef = findViewById(R.id.def_text_view_highscore);
        loadHighscoreDef();

        if (highscoreDef <= 7) {
            Toast.makeText(this, "Practice makes it perfect", Toast.LENGTH_SHORT).show();
        } else if (highscoreDef > 8 && highscoreDef < 12) {
            Toast.makeText(this, "Can we make a perfect score?", Toast.LENGTH_SHORT).show();
        } else if (highscoreDef == 13 || highscoreDef == 14) {
            Toast.makeText(this, "That's all you got?", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Keep it up!", Toast.LENGTH_SHORT).show();
        }

        Button buttonStartQuiz = findViewById(R.id.def_button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startdefQuiz();
            }
        });
    }

    private void startdefQuiz() {
        Intent D = new Intent(this, DefinitionQuizActivity.class);
        startActivityForResult(D, REQUEST_CODE_QUIZ_DEF);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_QUIZ_DEF) {
            if (resultCode == RESULT_OK) {
                int scoreDef = data.getIntExtra(DefinitionQuizActivity.EXTRA_SCORE_DEF, 0);
                if (scoreDef > highscoreDef) {
                    updateHighScoreDef(scoreDef);
                }
            }
        }
    }

    private void loadHighscoreDef() {
        SharedPreferences preferencesDef = getSharedPreferences(SHARED_PREFS_DEF, MODE_PRIVATE);
        highscoreDef = preferencesDef.getInt(KEY_HIGHSCORE_DEF, 0);
        textViewHighScoreDef.setText("Highscore: " + highscoreDef);
    }

    private void updateHighScoreDef(int highscoreNewDef) {
        highscoreDef = highscoreNewDef;
        textViewHighScoreDef.setText("Highscore: " + highscoreDef);

        SharedPreferences defPrefs = getSharedPreferences(SHARED_PREFS_DEF, MODE_PRIVATE);
        SharedPreferences.Editor defedit = defPrefs.edit();
        defedit.putInt(KEY_HIGHSCORE_DEF, highscoreDef);
        defedit.apply();

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

    protected void GotoHomeActitvity() {
        Intent H = new Intent(this, HomeActivity.class);
        H.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(H);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}