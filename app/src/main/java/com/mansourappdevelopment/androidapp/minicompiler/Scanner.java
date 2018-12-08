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
        HashMap mul = new HashMap();
        HashMap div = new HashMap();
        HashMap sum = new HashMap();
        HashMap sub = new HashMap();
        HashMap id = new HashMap();
        HashMap integer = new HashMap();
        HashMap decimal = new HashMap();

        number.put("type", "number");
        number.put("regex", "\\d+");
        mLanguage.add(number);

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

            mToken.put("expression type", expressionType);
            mToken.put("expression value", expression);
            mTokens.add(mToken);
        }
    }

    private String getExpressionType(String expression) {

        for (HashMap word : mLanguage) {

            if (expression.matches(word.get("regex").toString())) {

                if (expression.equals("int") || expression.equals("float"))
                    return "data type";
                else
                    return word.get("type").toString();

            }

        }

        return "UNKNOWN";

    }

    public void printTokens() {

        StringBuilder scannerOutput = new StringBuilder();

        for (HashMap token : mTokens) {

            scannerOutput.append("<" + token.get("expression type") + ", " + token.get("expression value") + ">" + " ");
        }

        Snackbar.make(mView, scannerOutput, 10000).show();
    }
}

