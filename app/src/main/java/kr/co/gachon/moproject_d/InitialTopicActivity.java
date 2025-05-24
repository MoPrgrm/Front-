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


public class InitialTopicActivity extends AppCompatActivity {

    Button btnTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_initial_select_topic);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.initial_topic), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnTopic = findViewById(R.id.btn_initial_complete);

        btnTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                SharedPreferences prefs = getSharedPreferences("userData", MODE_PRIVATE);
//                SharedPreferences.Editor editor = prefs.edit();
//                editor.putString("topic","");
//                editor.apply();

                Intent intent = new Intent(InitialTopicActivity.this, InitialLanguageActivity.class);
                startActivity(intent);
            }
        });

    }
}