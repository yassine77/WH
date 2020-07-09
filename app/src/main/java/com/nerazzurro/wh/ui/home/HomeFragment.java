package com.nerazzurro.wh.ui.home;

import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nerazzurro.wh.MainActivity;
import com.nerazzurro.wh.R;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TimePickerDialog picker;
    private TimeShift TimeShift;
    private static final String TAG = "LogSnippets";

    // Access a Cloud Firestore instance from your Activity
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final EditText StartAM = root.findViewById(R.id.activity_main_start_am);
        final EditText StopAM = root.findViewById(R.id.activity_main_stop_am);
        final EditText StartPM = root.findViewById(R.id.activity_main_start_pm);
        final EditText StopPM = root.findViewById(R.id.activity_main_stop_pm);
        Button Calculate = root.findViewById(R.id.activity_main_calculate_btn);
        final TextView Result = root.findViewById(R.id.activity_main_text_result);

        toClock(StartAM);
        toClock(StopAM);
        toClock(StartPM);
        toClock(StopPM);

        Calculate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                List<EditText> champs = Arrays.asList(StartAM, StopAM, StartPM, StopPM);

                if (!CheckTimeFields(champs)) {
                    return;
                }

                LocalTime startAM = LocalTime.parse(StartAM.getText().toString());
                LocalTime stopAM = LocalTime.parse(StopAM.getText().toString());
                LocalTime startPM = LocalTime.parse(StartPM.getText().toString());
                LocalTime stopPM = LocalTime.parse(StopPM.getText().toString());

                TimeShift = new TimeShift(startAM, stopAM, startPM, stopPM);

                Result.setText(LocalTime.MIN.plus(TimeShift.Duration()).toString());


            }
        });

        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    private void toClock(final EditText eText) {
        final NumberFormat f = new DecimalFormat("00");
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eText.setText(f.format(sHour) + ":" + f.format(sMinute));
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
    }

    private boolean CheckTimeFields(List<EditText> times) {
        int champsManquants = 0;
        for (int i = 0; i < times.size(); i++) {
            if (TextUtils.isEmpty(times.get(i).getText())) {
                times.get(i).setError("This blank is required!");
                champsManquants++;
            }
        }

        if(champsManquants > 0){
            Toast.makeText(getActivity(), "Tous les champs doivent être renseignés.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void logUseFirestore(TimeShift timeShift){
        // Add a new document with a generated id.
        Map<String, Object> data = new HashMap<>();
        data.put("date", new Timestamp(System.currentTimeMillis()));
        /*data.put("name", name);
        data.put("score", score);*/
    }
}