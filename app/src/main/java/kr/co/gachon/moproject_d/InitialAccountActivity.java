package kr.co.gachon.moproject_d;

import android.content.Intent;
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


public class InitialAccountActivity extends AppCompatActivity {

    Button btnComplete;
    EditText txtAccountID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_initial_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.initial_account), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnComplete = findViewById(R.id.btn_initial_account_complete);
        txtAccountID = findViewById(R.id.txt_initial_user_id);


        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtAccountID.getText().toString().isEmpty()){// Check input is empty
                    Toast.makeText(getApplicationContext(),"다시 입력하시오", Toast.LENGTH_LONG).show();
                }
                else{
                    if(true) {// Check input userID is in database --> get user information(user's topic/language) from database
                        SharedPreferences prefs = getSharedPreferences("userData", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("userId",txtAccountID.getText().toString());
//                        editor.putString("topic",); // edit user topic
//                        editor.putString("language",); // edit user language
                        editor.apply();

                        Intent intent = new Intent(InitialAccountActivity.this, InitialCompleteActivity.class);
                        startActivity(intent);
                    }
                    else{// input userID is not in database
                        Toast.makeText(getApplicationContext(),"다시 입력하시오", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });



    }
}