package com.example.devvi.databasehandlingexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.devvi.databasehandlingexample.Contacts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Devvi on 11/23/2017.
 */
public class DataBaseHandling extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static  final String DATABASE_NAME="contactsManager";
    private static final String TABLE_CONTACTS="contacts";
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_PH_NO="phone_number";

    public DataBaseHandling(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    } @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE="CREATE TABLE"+TABLE_CONTACTS+"("+KEY_ID +"INTEGER PRIMARY KEY"
        +KEY_NAME+"TEXT"+KEY_PH_NO+"TEXT"+")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
                sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLE_CONTACTS);
                onCreate(sqLiteDatabase);
    }
    /*CRUD Operations*/
    void addContacts(Contacts contacts)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,contacts.get_name());
        values.put(KEY_PH_NO,contacts.get_phone_number());
        db.insert(TABLE_CONTACTS,null,values);
        db.close();

    }
    Contacts getContacts(int id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_CONTACTS,new String[]{KEY_ID,KEY_NAME,KEY_PH_NO},KEY_ID +"=?",
                new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
            Contacts contacts= new Contacts(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),cursor.getString(2));
           return contacts;
    }
        public List<Contacts> getAllContacts()
        {
            List<Contacts> contactsList=new ArrayList<Contacts>();
            String selectQuery="SELECT * FROM "+TABLE_CONTACTS;
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
            if(cursor.moveToFirst()){
                do {
                    Contacts contacts=new Contacts();
                    contacts.set_id(Integer.parseInt(cursor.getString(0)));
                    contacts.set_name(cursor.getString(1));
                    contacts.set_phone_number((cursor.getString(2)));
                    contactsList.add(contacts);
                     }
                while (cursor.moveToNext());
            }
            return contactsList;
        }
    public int updateContacts(Contacts contacts){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,contacts.get_name());
        values.put(KEY_PH_NO,contacts.get_phone_number());
        /*return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });*/

        return db.update(TABLE_CONTACTS, values ,KEY_ID+"=?",new String[]{String.valueOf(contacts.get_id())});
    }
    public  void deleteContacts(Contacts contacts)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_CONTACTS,KEY_ID +"=?",new String[]{String.valueOf(contacts.get_id())});
        db.close();
    }
    public int getContactCount(Contacts contacts)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String sqlQuery="SELECT * FROM"+TABLE_CONTACTS;
        Cursor cursor= db.rawQuery(sqlQuery,null);
        cursor.close();
        return cursor.getCount();

    }
}

