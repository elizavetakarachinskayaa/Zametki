package com.elizaveta.zametki;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Activity_delate extends AppCompatActivity {

    SQLiteDatabase db;
    long zametka_Id=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delate);
    }
    public void goToActivity2S_Delete(View v){

        Intent intent = new Intent(this, Activity_2.class);
        startActivity(intent);

    }


    public void goToActivity3S_Delete(View v){
        Intent intent = new Intent(this, Activity_3.class);
        startActivity(intent);

    }
    public void delete(View view){
        db.delete(DBHelper.TABLE_NAME, "_id = ?", new String[]{String.valueOf(zametka_Id)});
        Intent intent = new Intent(this, Activity_2.class);
        startActivity(intent);
    }
}
