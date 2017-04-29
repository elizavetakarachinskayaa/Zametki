package com.esopoff.kosmas_zametki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Activity_3 extends AppCompatActivity{

    //описываем переменные с кнопками и текстом.
    EditText etText;
    ImageButton btnSave;
    ImageButton delButton;

    //    DBHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long zametka_Id=0;

    //описываем метом работы с БД
    DBHelper dbHelper;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
//Находим по id комопненты активити_3
        etText = (EditText) findViewById(R.id.editText);

        btnSave = (ImageButton) findViewById(R.id.btnSave);
        delButton = (ImageButton) findViewById(R.id.delButton);
//        btnSave.setOnClickListener(this);


        // создаем объект для создания и управления версиями БД
        dbHelper = new DBHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            zametka_Id= extras.getLong("id");
        }
        // если 0, то добавление
        if (zametka_Id > 0) {
            // получаем элемент по id из бд
            userCursor = db.rawQuery("select * from " + DBHelper.TABLE_NAME + " where " +
                    DBHelper.KEY_ID + "=?", new String[]{String.valueOf(zametka_Id)});
            userCursor.moveToFirst();
            etText.setText(userCursor.getString(1));

            userCursor.close();
        }
        }

    public void save(View view){
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.KEY_TEXT, etText.getText().toString());

        if (zametka_Id > 0) {
            db.update(DBHelper.TABLE_NAME, cv, DBHelper.KEY_ID + "=" + String.valueOf(zametka_Id), null);
        } else {
            db.insert(DBHelper.TABLE_NAME, null, cv);
        }
        Intent intent = new Intent(this, Activity_2.class);
        startActivity(intent);

    }
    public void delete(View view){
        db.delete(DBHelper.TABLE_NAME, "_id = ?", new String[]{String.valueOf(zametka_Id)});
        Intent intent = new Intent(this, Activity_2.class);
        startActivity(intent);
    }

//    public void goToDelate(View v){
//        Intent intent = new Intent(this, Activity_delate.class);
//        startActivity(intent);
//
//    }
//    public void onBack(){
//        // закрываем подключение
//        db.close();
//        // переход к главной activity
//        Intent intent = new Intent(this, Activity_2.class);
//        startActivity(intent);
//    }
}