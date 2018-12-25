package com.mansourappdevelopment.androidapp.minicompiler;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Scanner {
    private String mCode;
    private int mLineNumber;
    private String[] mCodeLines;
    private List<HashMap> mTokens;
    private HashMap mToken;
    private List<String> mExpressions;
    private List<HashMap> mLanguage;
    private Context mContext;
    private View mView;


    public Scanner(String mCode, int mLineNumber, Context mContext, View mView) {
        this.mCode = mCode;
        this.mLineNumber = mLineNumber;
        this.mContext = mContext;
        this.mView = mView;
        buildLanguage();

    }

    private void buildLanguage() {
        mLanguage = new ArrayList<>();

        HashMap number = new HashMap();
        HashMap floatNumber = new HashMap();
        HashMap mul = new HashMap();
        HashMap div = new HashMap();
        HashMap sum = new HashMap();
        HashMap sub = new HashMap();
        HashMap id = new HashMap();
        HashMap integer = new HashMap();
        HashMap decimal = new HashMap();
        HashMap assignment = new HashMap();
        HashMap conditionalStatement = new HashMap();
        HashMap specialCharacters = new HashMap();

        number.put("type", "number");
        number.put("regex", "\\d+");
        mLanguage.add(number);

        floatNumber.put("type", "float number");
        floatNumber.put("regex", "\\d*(\\.\\d+)");
        mLanguage.add(floatNumber);

        mul.put("type", "operation");
        mul.put("regex", "\\bmul\\b");
        mLanguage.add(mul);

        div.put("type", "operation");
        div.put("regex", "\\bdiv\\b");
        mLanguage.add(div);

        sum.put("type", "operation");
        sum.put("regex", "\\bsum\\b");
        mLanguage.add(sum);

        sub.put("type", "operation");
        sub.put("regex", "\\bsub\\b");
        mLanguage.add(sub);

        assignment.put("type", "assignment");
        assignment.put("regex", "\\beql\\b");
        mLanguage.add(assignment);

        conditionalStatement.put("type", "conditional statement");
        conditionalStatement.put("regex", "\\bif\\b");
        mLanguage.add(conditionalStatement);

        specialCharacters.put("type", "special character");
        specialCharacters.put("regex", "[^\\w\\s]");
        mLanguage.add(specialCharacters);

        integer.put("type", "data type");
        integer.put("regex", "\\bint\\b");
        mLanguage.add(integer);

        decimal.put("type", "data type");
        decimal.put("regex", "\\bdecimal\\b");
        mLanguage.add(decimal);

        id.put("type", "identifier");
        id.put("regex", "([a-zA-Z]|_)([a-zA-Z]+|[0-9]+|_+)*");
        mLanguage.add(id);


    }


    public void separateLines() {

        mCodeLines = mCode.split("\n");
    }

    public void separateExpressions() {
        mExpressions = new ArrayList<>();

        for (int i = 0; i < mLineNumber; i++) {

            mExpressions.addAll(Arrays.asList(mCodeLines[i].split(" ")));
        }
    }


    public void validateExpressions() {
        mTokens = new ArrayList<>();

        for (String expression : mExpressions) {

            mToken = new HashMap();
            String expressionType = getExpressionType(expression);

            mToken.put("type", expressionType);
            mToken.put("value", expression);
            mTokens.add(mToken);
        }
    }

    private String getExpressionType(String expression) {

        for (HashMap word : mLanguage) {

            if (expression.matches(word.get("regex").toString())) {
                return word.get("type").toString();

            }

        }

        return "Undefined Expression";

    }

    public void printTokens() {

        StringBuilder scannerOutput = new StringBuilder();

        for (HashMap token : mTokens) {

            scannerOutput.append("<" + token.get("type") + ", " + token.get("value") + ">" + " ");
        }

        Snackbar.make(mView, scannerOutput, 10000).show();
    }

    public List<HashMap> getmTokens() {
        return mTokens;
    }
}

