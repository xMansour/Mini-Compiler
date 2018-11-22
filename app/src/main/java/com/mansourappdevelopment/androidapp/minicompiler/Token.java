package com.mansourappdevelopment.androidapp.minicompiler;

public class Token {
    private String mTokenType;
    private int mTokenValue;

    public Token(String mTokenType, int mTokenValue) {
        this.mTokenType = mTokenType;
        this.mTokenValue = mTokenValue;
    }

    public String getmTokenType() {
        return mTokenType;
    }

    public int getmTokenValue() {
        return mTokenValue;
    }

    public void setmTokenType(String mTokenType) {
        this.mTokenType = mTokenType;
    }

    public void setmTokenValue(int mTokenValue) {
        this.mTokenValue = mTokenValue;
    }
}
