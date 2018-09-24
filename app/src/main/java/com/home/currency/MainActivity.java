package com.home.currency;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static String NEW_LINE = System.getProperty("line.separator");

    private EditText ntd;
    private TextView us;
    private TextView jp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
    }

    private void findView() {
        ntd = findViewById(R.id.ntd);
        us = findViewById(R.id.us);
        jp = findViewById(R.id.jp);
    }

    private float convertToUSD(float ntd) {
        float NTD_TO_USD_RATIO = 30.9f;
        return ntd / NTD_TO_USD_RATIO;
    }

    private float convertToJPY(float ntd) {
        float NTD_TO_JPY_RATIO = 0.27f;
        return ntd / NTD_TO_JPY_RATIO;
    }

    private void displayDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    public void calculateCurrency(View view) {
        if ("".equals(this.ntd.getText().toString())) {
            displayDialog(getString(R.string.problem), getString(R.string.please_enter_ntd));
            return;
        }
        float ntd = Float.parseFloat(this.ntd.getText().toString());

        float usd = convertToUSD(ntd);
        float jpy = convertToJPY(ntd);

        String message = getString(R.string.usd_is) + usd + NEW_LINE + getString(R.string.jpy_is) + jpy;

        displayDialog(getString(R.string.result), message);
        us.setText(String.format(Locale.getDefault(), "%f", usd));
        jp.setText(String.format(Locale.getDefault(), "%f", jpy));
    }
}
