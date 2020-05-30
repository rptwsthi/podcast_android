package com.android.podcast.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.ContactsContract
import android.util.Log
import com.android.podcast.Models.Item
import java.lang.Exception

class DataSource(ctx: Context) {
    val context = ctx
    val dbHelper: DatabaseHelper? = DatabaseHelper(context)
    companion object {
        var database: SQLiteDatabase? = null
        val allColumns: Array<String> = arrayOf(DatabaseHelper.ID,
            DatabaseHelper.NAME, DatabaseHelper.PARENT_ID,
            DatabaseHelper.THUMB_IMAGE_PATH, DatabaseHelper.DESCRIPTION,
        DatabaseHelper.UPDATE_DATE, DatabaseHelper.TYPE, DatabaseHelper.FILE_PATH)
    }

    fun open(){
        database = dbHelper!!.writableDatabase
    }

    fun close(){
        dbHelper!!.close()
    }

    fun saveEntity(item: Item){
        try{
            var values = ContentValues()
            values.put(DatabaseHelper.ID,item.id)
            values.put(DatabaseHelper.NAME,item.name)
            values.put(DatabaseHelper.PARENT_ID,item.parentId)
            if(item.thumbImagePath !=null&& item.filePath.length>0)
                values.put(DatabaseHelper.THUMB_IMAGE_PATH,item.thumbImagePath)
            if(item.description!=null)
                values.put(DatabaseHelper.DESCRIPTION,item.description)
            values.put(DatabaseHelper.UPDATE_DATE,item.updateDate.toString())
            values.put(DatabaseHelper.TYPE, item.type)
            if(item.filePath != null && item.filePath.length>0)
                values.put(DatabaseHelper.FILE_PATH,item.filePath);
            var insert = database!!.insert(DatabaseHelper.TABLE_Folder,null,values);
        }catch (e:Exception){
            Log.e("err", e.message)
        }
    }

    fun getEntitiesForParent(parentID : String) : ArrayList<Item> {
        var itemList = ArrayList<Item>()
        try{
           var cursor = database!!.query(DatabaseHelper.TABLE_Folder, allColumns,DatabaseHelper.PARENT_ID+"=?",
           arrayOf(parentID),null,null,null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast){
                var entity = cursorToItem(cursor)
                itemList.add(entity)
            }
        }catch (e:Exception){
            Log.e("err", e.message)
        }
        return itemList
    }

    fun cursorToItem(cursor:Cursor) : Item {
        var entity = Item()
        entity.id = cursor.getString(0)
        entity.name = cursor.getString(1)
        entity.parentId = cursor.getString(2)
        entity.thumbImagePath = cursor.getString(3)
        entity.description = cursor.getString(4)
        entity.updateDate = cursor.getString(5).toLong();
        entity.type = cursor.getString(6)
        entity.filePath = cursor.getString(7)
        return entity
    }

}