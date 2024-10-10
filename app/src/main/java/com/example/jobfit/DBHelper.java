package com.example.jobfit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public  static  final  String DBNAME = "JobFit.db";

    public DBHelper(Context context) {
        super(context,"JobFit.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE users ("
                + "username TEXT PRIMARY KEY, "
                + "email TEXT UNIQUE, "
                + "phone_number TEXT, "
                + "gender TEXT, "
                + "skills TEXT, "
                + "experiences TEXT, "
                + "achievements TEXT, "
                + "education TEXT, "
                + "profile_picture BLOB)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop table if exists users");
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE email = ?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    public Bitmap getUserProfilePicture(String email) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT profile_picture FROM users WHERE email = ?", new String[]{email});
        try {
            cursor = MyDB.rawQuery("SELECT profile_picture FROM users WHERE email = ?", new String[]{email});

            if (cursor != null && cursor.moveToFirst()) {
                byte[] bitmap = cursor.getBlob(0); // Indeks 0 untuk kolom pertama
                return BitmapFactory.decodeByteArray(bitmap, 0, bitmap.length);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Tangani exception dengan baik
        } finally {
            if (cursor != null) {
                cursor.close(); // Pastikan cursor ditutup
            }
        }

        return null; // Jika tidak ditemukan atau terjadi kesalahan
    }
    public boolean isUserValid(String email, String username) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE email = ? AND username = ?", new String[]{email, username});
        boolean isValid = cursor.getCount() > 0; // Mengecek apakah ada pengguna yang valid
        cursor.close();
        return isValid;
    }
    public Cursor getUserData(String email) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        return MyDB.rawQuery("SELECT username, phone_number, gender FROM users WHERE email = ?", new String[]{email});
    }

    public boolean updateUserProfile(String email, String name, String phone, String gender, String newemail) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", name);
        contentValues.put("email", newemail);
        contentValues.put("phone_number", phone);
        contentValues.put("gender", gender);

        long result = MyDB.update("users", contentValues, "email = ?", new String[]{email});
        return result != -1; // Kembalikan true jika berhasil
    }
    public boolean updateUserData(String email, String skills, String experiences, String achievements, String education) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("skills", skills);
        contentValues.put("experiences", experiences);
        contentValues.put("achievements", achievements);
        contentValues.put("education", education);

        long result = MyDB.update("users", contentValues, "email = ?", new String[]{email});

        return result != -1; // Returns true if update was successful
    }

}
