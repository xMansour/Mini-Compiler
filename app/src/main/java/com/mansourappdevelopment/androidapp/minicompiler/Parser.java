package com.mansourappdevelopment.androidapp.minicompiler;

import android.support.design.widget.Snackbar;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parser {
    private List<HashMap> mTokens;
    private List<HashMap> mTree;
    private int mCurrentTokenIndex = 0;
    private int mLastTokenIndex = -1;
    private HashMap mToken;
    private View mView;
    private boolean valid = true;

    public Parser(List<HashMap> mTokens, View mView) {
        this.mTokens = mTokens;
        this.mView = mView;
        mTree = new ArrayList<>();
    }

    /**
     * Grammar:
     * ex                       -> number | float number | operation | identifier | data type | assignment | conditional statement | special character
     * number                   -> digit+
     * float number             -> digit*.digit+
     * digit                    -> 0|1|2|3|4|5|6|7|8|9
     * data type                -> int | decimal
     * conditional statement    -> if
     * special character        -> { | } | " | " | ? | ! | ^ | &
     * operation                -> sum|sub|mul|div
     * assignment               -> =
     */

    public void parse() {
        while (mCurrentTokenIndex < mTokens.size()) {
            mToken = mTokens.get(mCurrentTokenIndex);
            parseExpression(mToken);
        }
        validateOutput();
    }

    private void parseExpression(HashMap mToken) {
        if (mToken.get("type") == "number")
            parseNumber(mToken);
        else if (mToken.get("type") == "float number")
            parseFloatNumber(mToken);
        else if (mToken.get("type") == "operation")
            parseOperation(mToken);
        else if (mToken.get("type") == "identifier")
            parseIdentifier(mToken);
        else if (mToken.get("type") == "data type")
            parseDataType(mToken);
        else if (mToken.get("type") == "assignment")
            parseAssignment(mToken);
        else if (mToken.get("type") == "conditional statement")
            parseConditionalStatement(mToken);
        else if (mToken.get("type") == "special character")
            parseSpecialCharacters(mToken);
        else
            Snackbar.make(mView, "Error: Undefined Expression", 5000).show();

    }


    private void parseNumber(HashMap mToken) {
        HashMap token = new HashMap();
        token.put("type", mToken.get("type"));
        token.put("value", mToken.get("value"));
        token.put("parent", mLastTokenIndex);
        mTree.add(token);
        mCurrentTokenIndex++;
        valid = true;

    }

    private void parseFloatNumber(HashMap mToken) {
        HashMap token = new HashMap();
        token.put("type", mToken.get("type"));
        token.put("value", mToken.get("value"));
        token.put("parent", mLastTokenIndex);
        mTree.add(token);
        mCurrentTokenIndex++;
        valid = true;
    }

    private void parseOperation(HashMap mToken) {
        HashMap token = new HashMap();
        HashMap nextToken = new HashMap();
        token.put("type", mToken.get("type"));
        token.put("value", mToken.get("value"));
        token.put("parent", mLastTokenIndex);
        mTree.add(token);
        mLastTokenIndex = mCurrentTokenIndex;
        mCurrentTokenIndex++;
        valid = true;
        try {
            nextToken = mTokens.get(mCurrentTokenIndex);
        } catch (IndexOutOfBoundsException e) {
            valid = false;
        }
    }

    private void parseIdentifier(HashMap mToken) {
        HashMap token = new HashMap();
        token.put("type", mToken.get("type"));
        token.put("value", mToken.get("value"));
        token.put("parent", mLastTokenIndex);
        mTree.add(token);
        mCurrentTokenIndex++;
        valid = true;
    }

    private void parseDataType(HashMap mToken) {
        HashMap token = new HashMap();
        HashMap nextToken = new HashMap();
        token.put("type", mToken.get("type"));
        token.put("value", mToken.get("value"));
        token.put("parent", mLastTokenIndex);
        mTree.add(token);
        mLastTokenIndex = mCurrentTokenIndex;
        mCurrentTokenIndex++;
        valid = true;
        try {
            nextToken = mTokens.get(mCurrentTokenIndex);
        } catch (IndexOutOfBoundsException e) {
            valid = false;
        }
    }

    private void parseAssignment(HashMap mToken) {
        HashMap token = new HashMap();
        HashMap nextToken = new HashMap();
        token.put("type", mToken.get("type"));
        token.put("value", mToken.get("value"));
        token.put("parent", mLastTokenIndex);
        mTree.add(token);
        mLastTokenIndex = mCurrentTokenIndex;
        mCurrentTokenIndex++;
        valid = true;
        try {
            nextToken = mTokens.get(mCurrentTokenIndex);
        } catch (IndexOutOfBoundsException e) {
            valid = false;
        }
    }

    private void parseConditionalStatement(HashMap mToken) {
        HashMap token = new HashMap();
        HashMap nextToken = new HashMap();
        token.put("type", mToken.get("type"));
        token.put("value", mToken.get("value"));
        token.put("parent", mLastTokenIndex);
        mTree.add(token);
        mLastTokenIndex = mCurrentTokenIndex;
        mCurrentTokenIndex++;
        valid = true;
        try {
            nextToken = mTokens.get(mCurrentTokenIndex);
        } catch (IndexOutOfBoundsException e) {
            valid = false;
        }
    }

    private void parseSpecialCharacters(HashMap mToken) {
        HashMap token = new HashMap();
        token.put("type", mToken.get("type"));
        token.put("value", mToken.get("value"));
        token.put("parent", mLastTokenIndex);
        mTree.add(token);
        mCurrentTokenIndex++;
        valid = true;
    }

    private void validateOutput() {
        if (valid) {
            Snackbar.make(mView, "Info: Correct syntax", 5000).show();

        } else
            Snackbar.make(mView, "Info: Wrong syntax", 5000).show();
    }

    public void printTree() {

        StringBuilder scannerOutput = new StringBuilder();

        for (HashMap token : mTree) {
            scannerOutput.append("<" + token.get("type") + ", " + token.get("value") + ", " + token.get("parent") + ">" + " ");
        }
        Snackbar.make(mView, scannerOutput, 10000).show();
    }
}
