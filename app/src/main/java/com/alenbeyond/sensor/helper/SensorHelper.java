package com.alenbeyond.sensor.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alen on 16/11/23.
 */

public class SensorHelper {

    private final DBHelper dbHelper;

    public SensorHelper(Context context) {
        dbHelper = new DBHelper(context, "sensor.db", null, 1);
    }

    /**
     * 保存
     */
    public void save(SensorValue sensorValue) {// 插入记录
        SQLiteDatabase db = dbHelper.getWritableDatabase();// 取得数据库操作
        db.execSQL("insert into t_sensor (x_value,y_value,z_value) values(?,?,?)",
                new Object[]{sensorValue.getX_value(), sensorValue.getY_value(), sensorValue.getZ_value()});
        db.close();// 记得关闭数据库操作
    }

    /**
     * 查找所有
     * @return
     */
    public List<SensorValue> findAll() {// 查询所有记录
        List<SensorValue> lists = new ArrayList<SensorValue>();
        SensorValue sensorValue = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from t_sensor ", null);
        while (cursor.moveToNext()) {
            sensorValue = new SensorValue();
            sensorValue.setId(cursor.getInt(cursor.getColumnIndex("id")));
            sensorValue.setX_value(cursor.getString(cursor.getColumnIndex("x_value")));
            sensorValue.setY_value(cursor.getString(cursor.getColumnIndex("y_value")));
            sensorValue.setZ_value(cursor.getString(cursor.getColumnIndex("z_value")));
            lists.add(sensorValue);
        }
        db.close();
        return lists;
    }

    /**
     * 清空数据
     */
    public void deleteAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.execSQL("delete from t_sensor");
    }
}
