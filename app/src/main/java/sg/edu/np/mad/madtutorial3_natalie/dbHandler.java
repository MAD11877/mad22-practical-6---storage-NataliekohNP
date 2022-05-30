package sg.edu.np.mad.madtutorial3_natalie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.lang.UScript;

import java.util.ArrayList;


public class dbHandler extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "list.db";
    public static String ACCOUNTS = "List";
    public static String COLUMN_USERNAME = "Username";
    public static String COLUMN_DESCRIPTION = "description";
    public static String COLUMN_FOLLOW = "Follow";
    public static int DATABASE_VERSION = 1;

    public dbHandler(ListActivity context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATEACCOUNT = "CREATE TABLE " + ACCOUNTS + "(" + COLUMN_USERNAME + "TEXT" + COLUMN_DESCRIPTION + "TEXT" + COLUMN_FOLLOW + "TEXT" + ")";
        db.execSQL(CREATEACCOUNT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newerversion) {
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS);
        onCreate(db);
    }

    public void addUsers(User userData) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, userData.getName());
        values.put(COLUMN_DESCRIPTION, userData.getDescription());
        values.put(COLUMN_FOLLOW, userData.getFollowed().booleanValue());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ACCOUNTS, null, values);
        db.close();
    }

    public User findUser(String userName) {
        String query = "SELECT * FROM " + ACCOUNTS + " WHERE " + COLUMN_USERNAME + " =\"" + userName + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User querydate = new User();
        if (cursor.moveToFirst()) {
            querydate.setName(cursor.getString(0));
            querydate.setDescription(cursor.getString(1));
            querydate.setFollowed(Boolean.valueOf(cursor.getString(2)));
            cursor.close();
        } else {
            querydate = null;
        }
        db.close();
        return querydate;
    }

    public ArrayList<User> showall() {
        String query = "SELECT * FROM " + ACCOUNTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<User> userslist = null;
        if (cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndexOrThrow("Username");
            int description = cursor.getColumnIndexOrThrow("description");
            int follow = cursor.getColumnIndexOrThrow("Follow");
            String name = cursor.getString(nameIndex);
            String des = cursor.getString(description);

            String follow2 = cursor.getString(follow);

            userslist.add(new User(name, des,1, Boolean.valueOf(follow2)));
        }
        db.close();
        return userslist;
    }
}


