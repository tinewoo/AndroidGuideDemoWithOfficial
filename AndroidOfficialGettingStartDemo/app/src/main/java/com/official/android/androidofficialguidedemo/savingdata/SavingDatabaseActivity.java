package com.official.android.androidofficialguidedemo.savingdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.official.android.androidofficialguidedemo.R;

public class SavingDatabaseActivity extends AppCompatActivity {

    FeedReaderDbHelper mDbHelper;
    private static final String LOG_TAG = "SavingDatabaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_database);
        mDbHelper = new FeedReaderDbHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_saving_database, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void insertSQLLiteData(View v){

        String id = "id1",title = "title1", content = "content1";

        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID, id);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_CONTENT, content);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                FeedReaderContract.FeedEntry.COLUMN_NAME_NULLABLE,//指定在 ContentValues 为空的情况下框架可在其中插入 NULL 的列的名称
                values);
    }

    private void querySQLLiteData(View v){

        String[] selectionArgs = null;
        String selection = null;

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_UPDATED,
//        ...
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedReaderContract.FeedEntry.COLUMN_NAME_UPDATED + " DESC";

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        //遍历查询的结果
        cursor.moveToFirst();
        long itemId = cursor.getLong(
                cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID)
        );
    }

    private void deleteSQLLiteData(View v){

        String rowId = "id1";

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Define 'where' part of query.
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selelectionArgs = { String.valueOf(rowId) };
        // Issue SQL statement.
        int changedCount = db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selelectionArgs);
        if(changedCount > 0){
            Log.i(LOG_TAG, "数据库数据删除成功");
        }
        else{
            Log.i(LOG_TAG, "数据库数据删除失败");
        }
    }

    private void updateSQLLiteData(View v){

        String rowId = "id1";
        String title = "title2";

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);

        // Which row to update, based on the ID
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(rowId) };

        int count = db.update(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if(count > 0){
            Log.i(LOG_TAG, "数据库数据删除成功");
        }
        else{
            Log.i(LOG_TAG, "数据库数据删除失败");
        }
    }

    /**
     * 数据库创建
     */
    //定义架构和契约
    public final class FeedReaderContract {
        // To prevent someone from accidentally instantiating the contract class,
        // give it an empty constructor.
        public FeedReaderContract() {}

        /* Inner class that defines the table contents */
        public abstract class FeedEntry implements BaseColumns {
            public static final String TABLE_NAME = "entry";
            public static final String COLUMN_NAME_ENTRY_ID = "entryid";
            public static final String COLUMN_NAME_TITLE = "title";
            public static final String COLUMN_NAME_SUBTITLE = "subtitle";
            public static final String COLUMN_NAME_CONTENT = "content";
            public static final String COLUMN_NAME_NULLABLE = "nullable";
            public static final String COLUMN_NAME_UPDATED = "updated";
//            ...
        }
    }

    public class FeedReaderDbHelper extends SQLiteOpenHelper {

        //使用 SQL 辅助工具创建数据库
        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                        FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                        FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                        FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                        FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + TEXT_TYPE + COMMA_SEP +
                        FeedReaderContract.FeedEntry.COLUMN_NAME_CONTENT + TEXT_TYPE + COMMA_SEP +
                        FeedReaderContract.FeedEntry.COLUMN_NAME_UPDATED + TEXT_TYPE +
         // Any other options for the CREATE command
                " )";

        //创建数据库
        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "FeedReader.db";

        public FeedReaderDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

}
