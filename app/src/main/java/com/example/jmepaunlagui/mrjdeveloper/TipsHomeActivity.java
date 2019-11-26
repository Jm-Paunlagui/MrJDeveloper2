package com.example.jmepaunlagui.mrjdeveloper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TipsHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_home_layout);
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder backpress = new AlertDialog.Builder(this);

        backpress.setCancelable(true);
        backpress.setTitle("Confirm Exit");
        backpress.setMessage("Do you want to Exit?");

        backpress.setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ghome();
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
    protected void ghome(){
        Intent gH = new Intent(this,HomeActivity.class);
        gH.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(gH);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
