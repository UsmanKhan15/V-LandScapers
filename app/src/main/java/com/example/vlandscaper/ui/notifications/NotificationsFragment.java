package com.example.vlandscaper.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.vlandscaper.R;
import com.example.vlandscaper.activities.LandscapingDesign;
import com.example.vlandscaper.activities.ManualLandscapingActivity;
import com.example.vlandscaper.activities.VirtualLandscapingActivity;
import com.example.vlandscaper.adapters.HomeRecViewAdapter;
import com.example.vlandscaper.databinding.FragmentHomeBinding;
import com.example.vlandscaper.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {
    private FragmentNotificationsBinding binding;

    Button btnLandscapingDesign, btnManualLandscaping;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btnLandscapingDesign = root.findViewById(R.id.btnLandscapingDesign);
        btnManualLandscaping = root.findViewById(R.id.btnManualLandcaping);

        btnLandscapingDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LandscapingDesign.class));
            }
        });

        btnManualLandscaping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ManualLandscapingActivity.class));
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}