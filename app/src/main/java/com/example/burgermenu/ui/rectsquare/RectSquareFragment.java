package com.example.burgermenu.ui.rectsquare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.burgermenu.R;

public class RectSquareFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rect_square, container, false);
        EditText rectSquareA = root.findViewById(R.id.rectSquareA);
        EditText rectSquareB = root.findViewById(R.id.rectSquareB);
        TextView rectSquareResult = root.findViewById(R.id.rectSquareResult);
        Button button = root.findViewById(R.id.rectSquareCalculate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (String.valueOf(rectSquareA.getText()).equals("") || rectSquareA == null) {
                    rectSquareA.setError("Enter a!");
                } else if (String.valueOf(rectSquareB.getText()).equals("") || rectSquareB == null) {
                    rectSquareB.setError("Enter b!");
                } else {
                    double result = Double.parseDouble(String.valueOf(rectSquareA.getText())) * Double.parseDouble(String.valueOf(rectSquareB.getText()));
                    rectSquareResult.setText(result + "");
                }
            }
        });
        return root;
    }

}
