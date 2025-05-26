package kr.co.gachon.moproject_d.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import kr.co.gachon.moproject_d.ChangeAccountActivity;
import kr.co.gachon.moproject_d.ChangeLanguageActivity;
import kr.co.gachon.moproject_d.ChangeTopicActivity;
import kr.co.gachon.moproject_d.InitialAccountActivity;
import kr.co.gachon.moproject_d.InitialCheckActivity;
import kr.co.gachon.moproject_d.databinding.FragmentSettingBinding;

public class SettingFragment extends Fragment {

    private FragmentSettingBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingViewModel notificationsViewModel =
                new ViewModelProvider(this).get(SettingViewModel.class);

        binding = FragmentSettingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnNewsLanguageChange.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ChangeLanguageActivity.class));
        });

        binding.btnNewsTopicChange.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ChangeTopicActivity.class));
        });

        binding.btnAccountChange.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ChangeAccountActivity.class));
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}