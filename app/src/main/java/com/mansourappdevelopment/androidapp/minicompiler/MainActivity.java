package com.mansourappdevelopment.androidapp.minicompiler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView mTextViewLineNumbers;
    private EditText mEditTextCode;
    private FloatingActionButton mBtnRun;
    private FloatingActionButton mBtnOpen;
    private FloatingActionButton mBtnSave;
    private int mLineNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewLineNumbers = (TextView) findViewById(R.id.textViewLineNumbers);
        mEditTextCode = (EditText) findViewById(R.id.editTextCode);
        mBtnRun = (FloatingActionButton) findViewById(R.id.btnFabRun);
        mBtnSave = (FloatingActionButton) findViewById(R.id.btnFabSave);
        mBtnOpen = (FloatingActionButton) findViewById(R.id.btnFabOpen);


        //mTextViewLineNumbers.setMovementMethod(new ScrollingMovementMethod());
        mEditTextCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fillLineNumberSideBar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBtnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Scanner scanner = new Scanner(mEditTextCode.getText().toString(), mLineNumber, MainActivity.this, v);
                scanner.separateLines();
                scanner.separateExpressions();
                scanner.validateExpressions();
                //scanner.printTokens();
                List<HashMap> mTokens = scanner.getmTokens();
                Parser parser = new Parser(mTokens, v);
                parser.parse();
                //parser.printTree();
            }
        });
    }

    private void fillLineNumberSideBar() {
        int lines = mEditTextCode.getLineCount();
        String lineText = "";

        for (int i = 1; i <= lines; i++) {

            lineText = lineText + i + "\n";
            mLineNumber = i;
        }
        mTextViewLineNumbers.setText(lineText);
    }
}
