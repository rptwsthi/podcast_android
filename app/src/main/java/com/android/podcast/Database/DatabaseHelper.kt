package com.android.podcast.Database
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "PodCast.db"
        //Table Names
        const val TABLE_Folder = "Folder";
        const val TABLE_VIDEO = "Video"

        //Columns

        //commom columns
        val ID: String = "id"
        val NAME:String = "name"
        val PARENT_ID = "parent_id"
        val THUMB_IMAGE_PATH = "thumb_image_path"
        val DESCRIPTION = "description"
        val UPDATE_DATE = "create_date"
        val TYPE = "type"

        //Video table columns
        val FILE_PATH = "file_path"
    }
    //create table Folder
    val CREATE_FOLDER : String = "CREATE TABLE IF NOT EXIST" +
            TABLE_Folder +
            "(" +
            ID +
            "TEXT," +
            NAME +
            "TEXT," +
            PARENT_ID +
            "TEXT," +
            THUMB_IMAGE_PATH +
            "TEXT," +
            DESCRIPTION +
            "TEXT," +
            FILE_PATH +
            "TEXT," +
            TYPE +
            "TEXT," +
            UPDATE_DATE +
            "TEXT)"

    //create table Audio
    //create table Video
    //create table Text

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented")
        db!!.execSQL(CREATE_FOLDER);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}