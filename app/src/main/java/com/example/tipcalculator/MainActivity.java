package com.example.tipcalculator;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity
{

    TextView amountTextView;
    TextView tipTextView;
    TextView totalAmountTextView;
    SeekBar seekBar;

    TextView tipPercentTextView;

    NumberFormat percentFormat = NumberFormat.getPercentInstance();

    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //inflating the xml (GUI)

        //references to the widgets

        amountTextView = findViewById(R.id.amountEditText);
        tipTextView = findViewById(R.id.calculatedTipTextView);
        totalAmountTextView = findViewById(R.id.calculatedTotalAmount);
        seekBar = findViewById(R.id.percentSeekBar);
        tipPercentTextView = findViewById(R.id.tipPercentTextView);

        //Se tup the listeners
        amountTextView.addTextChangedListener(amountTextViewWatcher);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);


    }

    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            double percentage = ((double)seekBar.getProgress()/100);
            tipPercentTextView.setText(percentFormat.format(percentage));
            calculate();


        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    TextWatcher amountTextViewWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        calculate();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    private void calculate()
    {
        String amountString = amountTextView.getText().toString();
        double amount = 0.0;
        if(amountString.isEmpty())
        {
            tipTextView.setText(currencyFormat.format(0.0));
            totalAmountTextView.setText(currencyFormat.format(0.0));
        }
        else {
            amount = Double.parseDouble(amountTextView.getText().toString());
            int tipPercentage = seekBar.getProgress();
            double tip = amount * tipPercentage / 100;
            double totalAmount = amount + tip;
            tipTextView.setText(currencyFormat.format(tip));
            totalAmountTextView.setText(currencyFormat.format(totalAmount));
            }
    }

}