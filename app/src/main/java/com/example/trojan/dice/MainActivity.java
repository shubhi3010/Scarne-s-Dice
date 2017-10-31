package com.example.trojan.dice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv1,tv2,tv3;
    Button b1,b2,b3;
    ImageView i;
    private boolean turn=true;
    private int userscore=0;
    private int compscore=0;
    private int turnscore=0;
    int mean=0;
    int chance=6;
    int n;
    String s1="Your Score";
    String s2="Computer Score";
    String s3="Current Turn Score";

    int images[]={R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=(TextView)findViewById(R.id.textView);
        tv2=(TextView)findViewById(R.id.textView2);
        tv3=(TextView)findViewById(R.id.textView3);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        i=(ImageView) findViewById(R.id.imageView);
    }
    public void roll(View v){
        n=new Random().nextInt(6) + 1;
        if(n==1){
           turnscore=0;
            hold(null);
        }
        else turnscore+=n;

        updateUi();
    }
    public void hold(View v)
    {
       if(turn)
       {    userscore+=turnscore;
           }
        else{
           compscore+=turnscore;


       }
        turnscore=0;
        n=1;
        updateUi();
        turn=!turn;
        if(compscore>100||userscore>100)
        {   Toast.makeText(MainActivity.this, (compscore > 100 ? "Computer" : "Player") + " won", Toast.LENGTH_SHORT).show();
            reset(null);
        }
    if(!turn){
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                computerTurn();
            }
        },1000);
    }

    }
    public void computerTurn(){
     mean=(userscore+turnscore)/2;
        if(!turn){
            if(mean>compscore&&chance>0)
            {
                roll(null);
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {chance--;
                       computerTurn();
                    }
                },1000);
            }
            else
                hold(null);
        }

    }
    public void reset(View v){
        userscore=turnscore=compscore=0;
        turn=true;

        updateUi();
    }
    public void updateUi(){
        tv1.setText(s1 + userscore);
        tv2.setText(s2 + compscore);
        tv3.setText(s3 + turnscore);
        i.setImageResource(images[n-1]);
    }
}
