package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String equation = "";
    String arg1 = "", arg2 = "";
    String resultat = "";
    String sign = "";

    private Button ch0;
    private Button ch1;
    private Button ch2;
    private Button ch3;
    private Button ch4;
    private Button ch5;
    private Button ch6;
    private Button ch7;
    private Button ch8;
    private Button ch9;

    private Button point;
    private Button plus;
    private Button sous;
    private Button multi;
    private Button divi;
    private Button percent;
    private Button bracket;

    private Button ans;
    private Button erase;
    private Button egal;
    private ImageButton backspace;
    private HorizontalScrollView scroll;
    private TextView equationTV;
    private TextView result;
    private TextView historique;
    private View.OnClickListener chiffre = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String text = null;
            if(v instanceof Button){
                Button t = (Button) v;
                text = t.getText().toString();
                if (sign.equals(""))
                    arg1 = arg1 + text;
                else
                    arg2 = arg2 + text;
                equation = arg1 + sign + arg2;

                equationTV.setText(equation);
                scroll.fullScroll(View.FOCUS_RIGHT);
            }
        }
    };
    private View.OnClickListener operation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v instanceof Button) {
                Button t = (Button) v;
                if (! arg1.equals(""))
                    sign = t.getText().toString();

                equation = arg1 + sign + arg2;

                equationTV.setText(equation);
                scroll.fullScroll(View.FOCUS_RIGHT);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ch0 = findViewById(R.id.ch0);
        ch0.setOnClickListener(chiffre);
        ch1 = findViewById(R.id.ch1);
        ch1.setOnClickListener(chiffre);
        ch2 = findViewById(R.id.ch2);
        ch2.setOnClickListener(chiffre);
        ch3 = findViewById(R.id.ch3);
        ch3.setOnClickListener(chiffre);
        ch4 = findViewById(R.id.ch4);
        ch4.setOnClickListener(chiffre);
        ch5 = findViewById(R.id.ch5);
        ch5.setOnClickListener(chiffre);
        ch6 = findViewById(R.id.ch6);
        ch6.setOnClickListener(chiffre);
        ch7 = findViewById(R.id.ch7);
        ch7.setOnClickListener(chiffre);
        ch8 = findViewById(R.id.ch8);
        ch8.setOnClickListener(chiffre);
        ch9 = findViewById(R.id.ch9);
        ch9.setOnClickListener(chiffre);

        point = findViewById(R.id.point);
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = -2;
                if (sign.equals("")) {
                    if(! arg1.equals("")) {
                        index = arg1.indexOf(".");
                        if (index == -1)
                            arg1 = arg1 + '.';
                    }
                }
                else {
                    if (! arg2.equals("")) {
                        index = arg2.indexOf(".");
                        if (index == -1)
                            arg2 = arg2 + '.';
                    }
                }
                equation = arg1 + sign + arg2;

                equationTV.setText(equation);
                scroll.fullScroll(View.FOCUS_RIGHT);
            }
        });

        plus = findViewById(R.id.plus);
        plus.setOnClickListener(operation);
        sous = findViewById(R.id.sous);
        sous.setOnClickListener(operation);
        multi = findViewById(R.id.multi);
        multi.setOnClickListener(operation);
        divi = findViewById(R.id.divi);
        divi.setOnClickListener(operation);
        percent = findViewById(R.id.percent);
        percent.setOnClickListener(operation);

        bracket = findViewById(R.id.bracket);
        bracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = -2;
                if (! arg1.equals("")) {
                    index = arg1.indexOf("-");
                    if (index == -1)
                        arg1 = "-" + arg1;
                }
                equation = arg1 + sign + arg2;

                equationTV.setText(equation);
                scroll.fullScroll(View.FOCUS_RIGHT);
            }
        });

        ans = findViewById(R.id.ans);
        ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arg1 = resultat;
                equation = arg1 + sign + arg2;
                equationTV.setText(equation);
            }
        });

        equationTV = findViewById(R.id.equationTV);
        result = findViewById(R.id.result);
        historique = findViewById(R.id.historique);

        scroll = findViewById(R.id.scroll);

        erase = findViewById(R.id.erase);
        erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arg1= "";
                arg2 = "";
                sign = "";
                equation = arg1 + sign + arg2;
                equationTV.setText(equation);

            }
        });
        backspace = findViewById(R.id.backspace);
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((arg2 != null) && (arg2.length() > 0)) {
                    arg2 = arg2.substring(0, arg2.length() - 1);
                }
                else if ((sign != null) && (sign.length() > 0)) {
                    sign = sign.substring(0, sign.length() - 1);
                }
                else if ((arg1 != null) && (arg1.length() > 0)) {
                    arg1 = arg1.substring(0, arg1.length() - 1);
                }
                equation = arg1 + sign + arg2;
                equationTV.setText(equation);
            }
        });
        egal = findViewById(R.id.egal);
        egal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (! arg1.equals("")) {
                    historique.setText(equation);
                    //Call Class service
                    resultat = Double.toString(Service.operation(arg1, sign, arg2));
                    result.setText(resultat);
                    arg1 = "";
                    arg2 = "";
                    sign = "";
                    equation = arg1 + sign + arg2;
                    equationTV.setText(equation);
                }
            }
        });


    }
}
