package southernit.lk.sas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class DB_Controller extends SQLiteOpenHelper {

    public DB_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "test.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE STUDENTS( ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT UNIQUE, LASTNAME TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS STUDENTS;");
        onCreate(sqLiteDatabase);
    }

    public void insert_student (String firstName, String lastName) {
        ContentValues contentValus = new ContentValues();
        contentValus.put("FIRSTNAME", firstName);
        contentValus.put("LASTNAME", lastName);
        this.getWritableDatabase().insertOrThrow("STUDENTS", "", contentValus);
    }

    public void delete_student (String firstName) {
        this.getWritableDatabase().delete("STUDENTS", "FIRSTNAME='"+firstName+"'", null);
    }

    public void update_studnet (String oldFirstName, String newFirstName) {
        this.getWritableDatabase().execSQL("UPDATE STUDENTS SET FIRSTNAME='"+newFirstName+"' WHERE FIRSTNAME='"+oldFirstName+"'");
    }

    public void list_all_students (TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM STUDENTS", null);
        while (cursor.moveToNext()) {
            textView.append(cursor.getString(1)+ " "+cursor.getString(2));
        }
    }

}
