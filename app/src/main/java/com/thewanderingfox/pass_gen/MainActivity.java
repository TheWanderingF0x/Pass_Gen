package com.thewanderingfox.pass_gen;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.admanager.AdManagerAdView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv_password;
    Button btn_generate;
    Button btn_copy;
    EditText txb_charlenght;

    private ClipboardManager myClipboard;
    private ClipData myClip;

    @SuppressLint("VisibleForTests")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, initializationStatus -> {
        });

        AdManagerAdView adView = new AdManagerAdView(this);
        adView.setAdSizes(AdSize.BANNER);
        adView.setAdUnitId("/6499/example/banner");

        AdView mAdView = findViewById(R.id.adManagerAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        tv_password = findViewById(R.id.tv_password);
        btn_generate = findViewById(R.id.btn_generate);
        btn_copy = findViewById(R.id.btn_copy);
        txb_charlenght = findViewById(R.id.txb_charlength);

        btn_generate.setOnClickListener(v -> {

           // int length = 20;

            String value= txb_charlenght.getText().toString();
            int length =Integer.parseInt(value);

            tv_password.setText(GetPassword(length));

        });

        btn_copy.setOnClickListener(v -> {

            myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            String text;
            text = tv_password.getText().toString();

            myClip = ClipData.newPlainText("text", text);
            myClipboard.setPrimaryClip(myClip);

            Toast.makeText(getApplicationContext(), "Text Copied",Toast.LENGTH_SHORT).show();
        });
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