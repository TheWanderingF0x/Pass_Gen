package com.example.java_pass_gen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.ClipboardManager;
import android.widget.Toast;

import java.util.Scanner;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv_password;
    Button btn_generate;
    Button btn_copy;
    EditText txb_charlenght;

    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tv_password = (TextView)findViewById(R.id.tv_password);
        btn_generate = (Button)findViewById(R.id.btn_generate);
        btn_copy = (Button)findViewById(R.id.btn_copy);
        txb_charlenght = (EditText)findViewById(R.id.txb_charlength);

        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // int length = 20;

                String value= txb_charlenght.getText().toString();
                int length =Integer.parseInt(value);

                tv_password.setText(GetPassword(length));

            }
        });

        btn_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                String text;
                text = tv_password.getText().toString();

                myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);

                Toast.makeText(getApplicationContext(), "Text Copied",Toast.LENGTH_SHORT).show();

            }
        });

    }

    public static boolean isNumeric(String lenght){
        return lenght != null && lenght.matches("[-+]?\\d*\\.?\\d+");
    }


    public String GetPassword(int length){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        Random rand = new Random();

        for(int i = 0; i < length; i++){
            char c = chars[rand.nextInt(chars.length)];
            stringBuilder.append(c);
        }


        return stringBuilder.toString();
    }




}