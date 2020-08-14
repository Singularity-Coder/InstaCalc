package com.singularitycoder.instacalc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.singularitycoder.instacalc.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private int result = 0;

    @NonNull
    private String runningNumber = "";

    @NonNull
    private String leftValueStr = "";

    @NonNull
    private String rightValueStr = "";

    @Nullable
    private Operation currentOperation;

    @Nullable
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvResult.setText("");
        setUpListeners();
    }

    private void setUpListeners() {
        binding.btn0.setOnClickListener((View.OnClickListener) v -> numberPressed(0));
        binding.btn1.setOnClickListener((View.OnClickListener) v -> numberPressed(1));
        binding.btn2.setOnClickListener((View.OnClickListener) v -> numberPressed(2));
        binding.btn3.setOnClickListener((View.OnClickListener) v -> numberPressed(3));
        binding.btn4.setOnClickListener((View.OnClickListener) v -> numberPressed(4));
        binding.btn5.setOnClickListener((View.OnClickListener) v -> numberPressed(5));
        binding.btn6.setOnClickListener((View.OnClickListener) v -> numberPressed(6));
        binding.btn7.setOnClickListener((View.OnClickListener) v -> numberPressed(7));
        binding.btn8.setOnClickListener((View.OnClickListener) v -> numberPressed(8));
        binding.btn9.setOnClickListener((View.OnClickListener) v -> numberPressed(9));

        binding.btnAdd.setOnClickListener((View.OnClickListener) v -> processOperation(Operation.ADD));
        binding.btnSubtract.setOnClickListener((View.OnClickListener) v -> processOperation(Operation.SUBTRACT));
        binding.btnMultiply.setOnClickListener((View.OnClickListener) v -> processOperation(Operation.MULTIPLY));
        binding.btnDivide.setOnClickListener((View.OnClickListener) v -> processOperation(Operation.DIVIDE));

        binding.btnClear.setOnClickListener((View.OnClickListener) v -> btnClear());
        binding.btnEquals.setOnClickListener((View.OnClickListener) v -> processOperation(Operation.EQUAL));
    }

    private void btnClear() {
        leftValueStr = "";
        rightValueStr = "";
        result = 0;
        runningNumber = "";
        currentOperation = null;
        binding.tvResult.setText("0");
    }

    void processOperation(Operation operation) {
        if (null != currentOperation) {
            if (!("").equals(runningNumber)) {
                rightValueStr = runningNumber;
                runningNumber = "";

                switch (currentOperation) {
                    case ADD:
                        result = Integer.parseInt(leftValueStr) + Integer.parseInt(rightValueStr);
                        break;
                    case SUBTRACT:
                        result = Integer.parseInt(leftValueStr) - Integer.parseInt(rightValueStr);
                        break;
                    case MULTIPLY:
                        result = Integer.parseInt(leftValueStr) * Integer.parseInt(rightValueStr);
                        break;
                    case DIVIDE:
                        result = Integer.parseInt(leftValueStr) / Integer.parseInt(rightValueStr);
                        break;
                }
                leftValueStr = String.valueOf(result);
                binding.tvResult.setText(leftValueStr);
            }
        } else {
            leftValueStr = runningNumber;
            runningNumber = "";
        }
        currentOperation = operation;
    }

    void numberPressed(int number) {
        runningNumber += String.valueOf(number);
        binding.tvResult.setText(runningNumber);
    }

    public enum Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE, EQUAL
    }
}