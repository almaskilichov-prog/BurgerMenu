package com.example.burgermenu.ui.rectperimeter;

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

public class RectPerimeterFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rect_perimeter, container, false);
        EditText rectPerimeterA = root.findViewById(R.id.rectPerimeterA);
        EditText rectPerimeterB = root.findViewById(R.id.rectPerimeterB);
        TextView rectPerimeterResult = root.findViewById(R.id.rectPerimeterResult);
        Button button = root.findViewById(R.id.rectPerimeterCalculate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (String.valueOf(rectPerimeterA.getText()).equals("") || rectPerimeterA == null) {
                    rectPerimeterA.setError("Enter a!");
                } else if (String.valueOf(rectPerimeterB.getText()).equals("") || rectPerimeterB == null) {
                    rectPerimeterB.setError("Enter b!");
                } else {
                    double result = (Double.parseDouble(String.valueOf(rectPerimeterA.getText())) + Double.parseDouble(String.valueOf(rectPerimeterB.getText()))) * 2;
                    rectPerimeterResult.setText(result + "");
                }
            }
        });
        return root;
    }

}
