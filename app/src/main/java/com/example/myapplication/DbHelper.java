package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.sql.Blob;

public class DbHelper extends SQLiteOpenHelper {

    //Initialized a public static string variable and assigned database name.
    public static final String DATABASE_NAME = "myData.db";

    //Initialized public static string variables to assign vale of table name and tables columns.
    public static final String TABLE_NAME_1 = "user";
    public static final String USER_NAME = "userName";
    public static final String FIRST_NAME = "fname";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String PICTURE = "picture";

    //Initialized public static string variables to assign vale of table name and tables columns.
    public static final String TABLE_NAME_2 = "subUser";
    public static final String PARENT_NAME = "parentName";
    public static final String SUB_USER_NAME = "subUserName";
    public static final String F_NAME = "fName";
    public static final String PIC = "picture";
    public static final String GENDER = "gender";
    public static final String AGE = "age";
    public static final String REPORT = "report";

    //Initialized public static string variables to assign vale of table name and tables columns.
    public static final String TABLE_NAME_3 = "method";
    public static final String METHOD_ID = "mId";
    public static final String METHOD_NAME = "method";

    //Initialized public static string variables to assign vale of table name and tables columns.
    public static final String TABLE_NAME_4 = "data";
    public static final String SUB_USER_ID = "subUser";
    public static final String TRY_OUT_ID = "tryOutId";
    public static final String METHOD = "mId";
    public static final String WORDS = "words";
    public static final String TIME = "time";
    public static final String RECORDING = "recording";

    //Initialized public static string variables to assign vale of table name and tables columns.
    public static final String TABLE_NAME_5 = "question";

    //Generated the constructor of the class and called super().
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    //override the onCreate() method and execute the sql query for create table
    @Override
    public void onCreate(SQLiteDatabase db) {

        //db.execSQL("create table "+TABLE_NAME_1+" ("+USER_NAME+" TEXT PRIMARY KEY,"+FIRST_NAME+" TEXT, "+EMAIL+" TEXT, "+PASSWORD+" TEXT, "+PICTURE+" BLOB)");

        db.execSQL("create table "+TABLE_NAME_2+" ("+SUB_USER_NAME+" TEXT PRIMARY KEY,"+PARENT_NAME+" TEXT, "+F_NAME+" TEXT, "+GENDER+" TEXT, "+AGE+" INTEGER,  UNIQUE("+SUB_USER_NAME+","+PARENT_NAME+"))");

       // db.execSQL("create table "+TABLE_NAME_3+" ("+METHOD_ID+" TEXT PRIMARY KEY,"+METHOD_NAME+" TEXT)");

       // db.execSQL("create table "+TABLE_NAME_4+" ("+SUB_USER_ID+" TEXT,"+TRY_OUT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+METHOD+" TEXT, "+WORDS+" TEXT, "+TIME+" TEXT, "+RECORDING+" BLOB, " +
//                "    CONSTRAINT fk_sub_user\n" +
//                "    FOREIGN KEY ("+SUB_USER_ID+")\n" +
//                "    REFERENCES "+TABLE_NAME_2+"("+SUB_USER_NAME+")," +
//                "    CONSTRAINT fk_method\n" +
//                "    FOREIGN KEY ("+METHOD+")\n" +
//                "    REFERENCES "+TABLE_NAME_3+"("+METHOD_ID+"))");

    }

    //override the onUpgrade() method to drop the tables if exists.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       // sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_2);
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_3);
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_4);
//        sqLiteDatabase.execSQL("drop table if exists user");
        onCreate(sqLiteDatabase);
    }

    //Implemented a public method to insert data into the subUser table.
    public  boolean insertData(String userId,String parentId, String firstName, String gender, String age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SUB_USER_NAME, userId);
        contentValues.put(PARENT_NAME, parentId);
        contentValues.put(F_NAME, firstName);
        contentValues.put(GENDER, gender);
        contentValues.put(AGE, age);

        long result = db.insert(TABLE_NAME_2, null, contentValues);

        if (result == -1) {
            return false;
        }else {
//            db.close();
            return true;
        }

    }

    //Implemented a public method to fetch all the data inside the subUser table based on the parent name.
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "SELECT  * FROM " + TABLE_NAME_2,null);
//        db.close();
        return cursor;
    }

    //Implemented a public method to update the data inside the subUser table based on the parent name.
    public boolean updateData(String userId, String firstName, String gender, Blob profilePic, Integer age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(F_NAME, firstName);
        contentValues.put(PIC, String.valueOf(profilePic));
        contentValues.put(GENDER, gender);
        contentValues.put(AGE, age);
        db.update(TABLE_NAME_2,contentValues, "parentName = ?", new String[]{userId});
        return true;
    }

    //Implemented a public method to fetch relevant sub user data inside the subUser table based on the primary key.
    public Cursor getSubUserData(String userId){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME_2+" where "+SUB_USER_NAME+ "=?", new String[]{userId});
        return cursor;
    }
}
