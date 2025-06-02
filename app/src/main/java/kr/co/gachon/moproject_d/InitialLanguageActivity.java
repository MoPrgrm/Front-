package kr.co.gachon.moproject_d;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class InitialLanguageActivity extends AppCompatActivity {

    Button btnEnglish,btnJapanese,btnChinese;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_initial_select_language);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.initial_language), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnEnglish = findViewById(R.id.btn_english);
        btnJapanese = findViewById(R.id.btn_japanese);
        btnChinese = findViewById(R.id.btn_chinese);

        SharedPreferences prefs = getSharedPreferences("userData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("language","English");
        editor.apply();

        btnEnglish.setOnClickListener(new View.OnClickListener() {// click english button, edit user language
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("userData", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("language","English");
                editor.apply();
                Intent intent = new Intent(InitialLanguageActivity.this, InitialCompleteActivity.class);
                startActivity(intent);
            }
        });

        btnJapanese.setOnClickListener(new View.OnClickListener() {// click japanese button, edit user language
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("userData", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("language","Japanese");
                editor.apply();
                Intent intent = new Intent(InitialLanguageActivity.this, InitialCompleteActivity.class);
                startActivity(intent);
            }
        });

        btnChinese.setOnClickListener(new View.OnClickListener() {// click chinese button, edit user language
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("userData", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("language","Chinese");
                editor.apply();
                Intent intent = new Intent(InitialLanguageActivity.this, InitialCompleteActivity.class);
                startActivity(intent);
            }
        });


    }
}