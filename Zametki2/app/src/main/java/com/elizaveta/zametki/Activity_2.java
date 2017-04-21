package com.elizaveta.zametki;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class Activity_2 extends AppCompatActivity {

    GridView userList;
    DBHelper dbHelper;
//    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        userList = (GridView)findViewById(R.id.Gridview);

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Activity_3.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });


        dbHelper = new DBHelper(getApplicationContext());

    }


    @Override
    public void onResume() {
        super.onResume();
//        db.isOpen();
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        //получаем данные из бд в виде курсора
        userCursor =  db.rawQuery("select * from "+ DBHelper.TABLE_NAME, null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        String[] headers = new String[] {DBHelper.KEY_TEXT};
        // создаем адаптер, передаем в него курсор
        userAdapter = new SimpleCursorAdapter(this, R.layout.item,
                userCursor, headers, new int[]{R.id.tvText, android.R.id.text2}, 0);
        userList.setAdapter(userAdapter);
    }
    // по нажатию на кнопку запускаем UserActivity для добавления данных
    public void goToActivity3(View view){
        Intent intent = new Intent(this, Activity_3.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключение и курсор
        dbHelper.close();
        userCursor.close();
    }



//      public void goToDelate(View v){
//        Intent intent = new Intent(this, Activity_delate.class);
//        startActivity(intent);
//
//    }


}
