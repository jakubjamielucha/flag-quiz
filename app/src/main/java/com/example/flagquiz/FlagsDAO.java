package com.example.flagquiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FlagsDAO {

    public ArrayList<FlagsModel> getRandomTenQuestion(FlagsDatabase fd){
        ArrayList<FlagsModel> modelArrayList = new ArrayList<>();
        SQLiteDatabase liteDatabase = fd.getWritableDatabase();
        Cursor cursor = liteDatabase.rawQuery("SELECT * FROM flagquiztable ORDER BY RANDOM () LIMIT 10", null);

        int flagIdIndex = cursor.getColumnIndex("flag_id");
        int flagNameIndex = cursor.getColumnIndex("flag_name");
        int flagImageIndex = cursor.getColumnIndex("flag_image");

        while (cursor.moveToNext()){
            FlagsModel model = new FlagsModel(cursor.getInt(flagIdIndex),
                    cursor.getString(flagNameIndex),
                    cursor.getString(flagImageIndex));

            modelArrayList.add(model);
        }
        return modelArrayList;
    }
}
