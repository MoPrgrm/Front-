package kr.co.gachon.moproject_d.utils;

import android.text.TextUtils;
import android.widget.TextView;
import java.util.List;

public class showSentencesInTextView {

    public static void displaySentences(TextView textView, List<String> sentences) {
        String result = TextUtils.join("\n", sentences);
        textView.setText(result);
    }
}