package com.yuan.xmpp.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.yuan.xmpp.dbhelper.ContactOpenHelper;

import javax.xml.validation.Validator;

public class ContactsProvider extends ContentProvider {

    public static final String AUTHRITIES = ContentProvider.class.getSimpleName();

    static UriMatcher mUriMatcher;
    public static Uri URI_CONTACT = Uri.parse("content://" + AUTHRITIES + "/contact");
    public static final int CONTACT = 1;

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //添加一个匹规则
        mUriMatcher.addURI(AUTHRITIES, "/contact", CONTACT);
    }

    private ContactOpenHelper mHelper;

    @Override
    public boolean onCreate() {
        mHelper = new ContactOpenHelper(getContext());
        if (mHelper != null) {
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        int code = mUriMatcher.match(uri);
        switch (code) {
            case CONTACT:
                SQLiteDatabase db = mHelper.getWritableDatabase();
                long id = db.insert(ContactOpenHelper.T_CONTACT, "", contentValues);
                if (id != -1) {
                    Log.i("hetao", "---------insert success-----------");
                    uri = ContentUris.withAppendedId(uri, id);
                }
                break;
                default:
                    break;
        }
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int code = mUriMatcher.match(uri);
        int deleteCont = 0;
        switch (code) {
            case CONTACT:
                SQLiteDatabase db = mHelper.getWritableDatabase();
                deleteCont = db.delete(ContactOpenHelper.T_CONTACT, s, strings);
                if (deleteCont > 0) {
                    Log.i("hetao", "---------delete success-----------");
                }
                break;
            default:
                break;
        }
        return deleteCont;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        int code = mUriMatcher.match(uri);
        int updateCount = 0;
        switch (code) {
            case CONTACT:
                SQLiteDatabase db = mHelper.getWritableDatabase();
                updateCount = db.update(ContactOpenHelper.T_CONTACT, contentValues, s, strings);
                if (updateCount > 0) {
                    Log.i("hetao", "---------update success-----------");
                }
                break;
            default:
                break;
        }
        return updateCount;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        int code = mUriMatcher.match(uri);
        Cursor cursor = null;
        switch (code) {
            case CONTACT:
                SQLiteDatabase db = mHelper.getWritableDatabase();
                cursor = db.query(ContactOpenHelper.T_CONTACT, strings, s, strings, null, null, s1);
                Log.i("hetao", "---------query success-----------");
                break;
            default:
                break;
        }
        return cursor;
    }
}
