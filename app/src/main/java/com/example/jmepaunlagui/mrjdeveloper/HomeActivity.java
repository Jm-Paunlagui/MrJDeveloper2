package com.example.jmepaunlagui.mrjdeveloper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView definitionCard, advanceCard, codeCard, guideCard, title;
    MediaPlayer mybgmusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);

        LinearLayout linearLayoutH = findViewById(R.id.LLHome);
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayoutH.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        mybgmusic = MediaPlayer.create(HomeActivity.this,R.raw.music);
        mybgmusic.start();
        mybgmusic.setLooping(true);

        //Card ID's
        definitionCard = (CardView) findViewById(R.id.DEFINITION_card);
        advanceCard = (CardView) findViewById(R.id.ADVANCE_card);
        codeCard = (CardView) findViewById(R.id.CODE_card);
        guideCard = (CardView) findViewById(R.id.GUIDE_card);
        title = (CardView) findViewById(R.id.tap);
        //Clicklistener
        definitionCard.setOnClickListener(this);
        advanceCard.setOnClickListener(this);
        codeCard.setOnClickListener(this);
        guideCard.setOnClickListener(this);
        title.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent C;
        switch (v.getId()) {
            case R.id.DEFINITION_card:
                C = new Intent(this, DefinitionHomeActivity.class);
                startActivity(C);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.ADVANCE_card:
                C = new Intent(this, StartingScreenAtivity.class);
                startActivity(C);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.CODE_card:
                C = new Intent(this, CodeHomeActivity.class);
                startActivity(C);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.GUIDE_card:
                C = new Intent(this, TipsHomeActivity.class);
                startActivity(C);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.tap:
                AlertDialog.Builder tapp = new AlertDialog.Builder(this);

                tapp.setTitle("Who is Mr. J?");
                tapp.setMessage("You might be thinking Who I am \n" +
                        "right?\n" +
                        "\n" +
                        "I, Mr. J will help you to understand\n" +
                        "the \"World of Java\" by giving simple\n" +
                        "codes and correct terms you should use.\n" +
                        "\n" +
                        "My name comes from the initial \n" +
                        "who developed me. My job is to \n" +
                        "help you understand a simple \n" +
                        "syntax and help you to review \n" +
                        "the java for your coming \n" +
                        "examination.\n" +
                        "\n" +
                        "\"Break your limit and chase your \n" +
                        "dreams\"\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t-Mr. J");

                tapp.setPositiveButton("Great!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nexttap();
                    }
                });
                tapp.show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mybgmusic.release();
    }
    private void nexttap(){
        AlertDialog.Builder tapp = new AlertDialog.Builder(this);

        tapp.setTitle("Thank you!");
        tapp.setMessage("Thank you for using our application\n" +
                        "I hope that you learned something.");

        tapp.setPositiveButton("You're Welcome!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        tapp.show();
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
                HomeActivity.super.onBackPressed();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}