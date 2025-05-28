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


public class ChangeTopicActivity extends AppCompatActivity {

    Button btnScience,btnEconomy,btnPolitics,btnSocial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_topic);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.change_topic), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnScience = findViewById(R.id.btn_science);
        btnEconomy = findViewById(R.id.btn_economy);
        btnPolitics = findViewById(R.id.btn_politics);
        btnSocial = findViewById(R.id.btn_social);


        btnScience.setOnClickListener(new View.OnClickListener() {//key:topic, value:Science
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("userData", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("topic","Science");
                editor.apply();

                finish();
            }
        });
        btnEconomy.setOnClickListener(new View.OnClickListener() {//key:topic, value:Economy
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("userData", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("topic","Economy");
                editor.apply();

                finish();
            }
        });
        btnPolitics.setOnClickListener(new View.OnClickListener() {//key:topic, value:Politics
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("userData", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("topic","Politics");
                editor.apply();

                finish();
            }
        });
        btnSocial.setOnClickListener(new View.OnClickListener() {//key:topic, value:Social
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("userData", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("topic","Social");
                editor.apply();

                finish();
            }
        });



    }
}