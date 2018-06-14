package com.example.hhj.hello.Model;

import java.util.Date;

public class Message {
    private int mMessageId;
    private int mUserSend;
    private int mUserRec;
    private long mSendDT;
    private String mContent;
    private int mType;//0-收，1-发

    public int getMessageId() {
        return mMessageId;
    }

    public void setMessageId(int messageId) {
        mMessageId = messageId;
    }

    public int getUserSend() {
        return mUserSend;
    }

    public void setUserSend(int userSend) {
        mUserSend = userSend;
    }

    public int getUserRec() {
        return mUserRec;
    }

    public void setUserRec(int userRec) {
        mUserRec = userRec;
    }

    public long getSendDT() {
        return mSendDT;
    }

    public void setSendDT(long sendDT) {
        mSendDT = sendDT;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }
}
