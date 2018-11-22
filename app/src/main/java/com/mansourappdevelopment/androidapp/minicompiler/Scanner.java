package com.mansourappdevelopment.androidapp.minicompiler;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scanner {
    private String mCode;
    private int mLineNumber;
    private String[] mCodeLines;
    private List<Token> mTokens;
    private List<String> mExpressions;
    private Context mContext;
    private View mView;

    public Scanner(String mCode, int mLineNumber, Context mContext, View mView) {
        this.mCode = mCode;
        this.mLineNumber = mLineNumber;
        this.mContext = mContext;
        this.mView = mView;
    }

    public void separateLines() {
        mCodeLines = mCode.split("\n");
        Log.i("code lines", Arrays.toString(mCodeLines));
    }

    public void separateExpressions() {
        mExpressions = new ArrayList<>();
        //String expression = "";
        for (int i = 0; i < mLineNumber; i++) {
            mExpressions.addAll(Arrays.asList(mCodeLines[i].split(" ")));
        }
        validateExpressions();
        //For testing
        /*for (String exp : mExpressions) {
            expression += exp + " ";
        }
        Log.i("code lines expressions", expression);*/
    }

    private void validateExpressions() {
        mTokens = new ArrayList<>();
        int exepressionIndicator = 0;
        for (String exepression : mExpressions) {
            switch (exepression) {
                case "int":
                    exepressionIndicator = 1;
                    mTokens.add(new Token("keyword", 1));
                    break;
                case "x":
                    exepressionIndicator = 2;
                    mTokens.add(new Token("id", 2));
                    break;
                case "y":
                    exepressionIndicator = 3;
                    mTokens.add(new Token("id", 3));
                    break;
                case "=":
                    exepressionIndicator = 4;
                    mTokens.add(new Token("assignment", 4));
                    break;
                case "+":
                    exepressionIndicator = 5;
                    mTokens.add(new Token("operator", 5));
                    break;
                case "-":
                    exepressionIndicator = 6;
                    mTokens.add(new Token("operator", 6));
                    break;
                case "*":
                    exepressionIndicator = 7;
                    mTokens.add(new Token("operator", 7));
                    break;
                case "/":
                    exepressionIndicator = 8;
                    mTokens.add(new Token("operator", 8));
                    break;
                case "print":
                    exepressionIndicator = 9;
                    mTokens.add(new Token("operator", 9));
                    break;
                case "<":
                    exepressionIndicator = 10;
                    mTokens.add(new Token("operator", 10));
                    break;
                case ">":
                    exepressionIndicator = 11;
                    mTokens.add(new Token("operator", 11));
                    break;
                case "==":
                    exepressionIndicator = 12;
                    mTokens.add(new Token("operator", 12));
                    break;
                case "(":
                    exepressionIndicator = 13;
                    mTokens.add(new Token("special character", 13));
                    break;
                case ")":
                    exepressionIndicator = 14;
                    mTokens.add(new Token("special character", 14));
                    break;
                case "{":
                    exepressionIndicator = 15;
                    mTokens.add(new Token("special character", 15));
                    break;
                case "}":
                    exepressionIndicator = 16;
                    mTokens.add(new Token("special character", 16));
                    break;
                case "1":
                    exepressionIndicator = 17;
                    mTokens.add(new Token("numeric constant", 17));
                    break;
                case "2":
                    exepressionIndicator = 18;
                    mTokens.add(new Token("numeric constant", 18));
                    break;
                case "3":
                    exepressionIndicator = 19;
                    mTokens.add(new Token("numeric constant", 19));
                    break;
                case "4":
                    exepressionIndicator = 20;
                    mTokens.add(new Token("numeric constant", 20));
                    break;
                case "5":
                    exepressionIndicator = 21;
                    mTokens.add(new Token("numeric constant", 21));
                    break;
                case "6":
                    exepressionIndicator = 22;
                    mTokens.add(new Token("numeric constant", 22));
                    break;
                case "7":
                    exepressionIndicator = 23;
                    mTokens.add(new Token("numeric constant", 23));
                    break;
                case "8":
                    exepressionIndicator = 24;
                    mTokens.add(new Token("numeric constant", 24));
                    break;
                case "9":
                    exepressionIndicator = 25;
                    mTokens.add(new Token("numeric constant", 25));
                    break;
                /*default:
                    Snackbar.make(mView, "Error at " + exepression, 5000).show();
                    break;*/

            }
        }
        printTokens();
    }

    private void printTokens() {
        StringBuilder scannerOutput = new StringBuilder();
        for (Token token : mTokens) {
            scannerOutput.append("<" + token.getmTokenType() + ", " + token.getmTokenValue() + ">" + " ");
        }
        Snackbar.make(mView, scannerOutput, 10000).show();
    }
}

