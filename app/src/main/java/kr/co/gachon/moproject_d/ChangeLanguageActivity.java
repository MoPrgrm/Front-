package kr.co.gachon.moproject_d;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class ChangeLanguageActivity extends AppCompatActivity {

    Button btnEnglish, btnJapanese, btnChinese;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_language);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.change_language), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnEnglish = findViewById(R.id.btn_english);
        btnJapanese = findViewById(R.id.btn_japanese);
        btnChinese = findViewById(R.id.btn_chinese);

        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    SharedPreferences prefs = getSharedPreferences("userData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("language","English");
                    editor.apply();

                    finish();
            }
        });

        btnJapanese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("userData", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("language","Japanese");
                editor.apply();

                finish();
            }
        });

        btnChinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("userData", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("language","Chinese");
                editor.apply();

                finish();
            }
        });
    }
}