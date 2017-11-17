package jwei.apps.dataforandroid.ch6;

import android.provider.ContactsContract.CommonDataKinds.Phone;

public class ContactEntry {

    private String mName;

    private String mNumber;

    private String mType;

    public ContactEntry(String name, String number, int type) {
        mName = name;
        mNumber = number;
        String numType = "";
        switch (type) {
            case Phone.TYPE_HOME:
                numType = "HOME";
                break;
            case Phone.TYPE_MOBILE:
                numType = "MOBILE";
                break;
            case Phone.TYPE_WORK:
                numType = "WORK";
                break;
            default:
                numType = "MOBILE";
                break;
        }
        mType = numType;
    }

    public String getName() {
        return mName;
    }

    public String getNumber() {
        return mNumber;
    }

    public String getType() {
        return mType;
    }

}
