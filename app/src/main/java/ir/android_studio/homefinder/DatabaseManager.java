package ir.android_studio.homefinder;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

/**
 * Created by golden on 7/26/2017.
 */

public class DatabaseManager extends SQLiteOpenHelper {

    // ایجاد دیتابیس
    public DatabaseManager(Context context) {
        super(context, "homefinding", null, 1);
    }

    //ایجاد جدول ثبت نام و خونه ها

    @Override
    public void onCreate(SQLiteDatabase db) {
        String reg = "CREATE TABLE tbl_register (\n" +
                "    ID           INT          UNIQUE\n" +
                "                              NOT NULL\n" +
                "                              PRIMARY KEY" +
                "                               autoincrement\n" +
                "    Username     VARCHAR (50) UNIQUE\n" +
                "                              NOT NULL,\n" +
                "    Password     TEXT         UNIQUE\n" +
                "                              NOT NULL,\n" +
                "    Bongah_Name  VARCHAR (50) NOT NULL,\n" +
                "    Bongah_Place TEXT         NOT NULL\n" +
                ");\n";

        String Case = "CREATE TABLE Cases (\n" +
                "    Goal             VARCHAR (20) NOT NULL,\n" +
                "    Type_Home        VARCHAR (50) NOT NULL,\n" +
                "    Situation_Home   VARCHAR (50) NOT NULL,\n" +
                "    Land_Measurement INT          NOT NULL,\n" +
                "    Orijinal_Price   INT          NOT NULL,\n" +
                "    Ejare_Price      INT          NOT NULL,\n" +
                "    Particulars      TEXT         NOT NULL,\n" +
                "    Bongah_Name      VARCHAR (50) NOT NULL,\n" +
                "    Situation_Bongah TEXT         NOT NULL\n" +
                ");\n";



        db.execSQL(reg);
       db.execSQL(Case);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROPE TABLE IF EXISTS tbl_register");
        db.execSQL("DROPE TABLE IF EXISTS tbl_cases");
        onCreate(db);
    }
//متد های جدول رجیستر که افراد ثبت نام شده در آن قرار دارند

    public boolean Insert_Register(Register register) {

        boolean result;
        try {
            String insert_reg = "INSERT INTO tbl_register (ID,Username,Password,Bongah_Name,Bongah_place) " +
                    " VALUES (" + Register.ID + ",\'" + Register.Username + "\',\'" + Register.Password + "\'," +
                    "\'" + Register.Bongah_Name + "\',\'" + Register.Bongah_Place + "\')";
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(insert_reg);
            db.close();
            result = true;

        }
        catch (Exception e) {
            result = false;

//            if(Register.Bongah_Name == null || Register.Bongah_Place == null) {
//
//                   result = false;
//               }
        }
       return result;
    }

    public int Get_Register_Count() {

        int result = 0;

        String count = "SELECT * FROM tbl_register";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(count, null);
        result = cursor.getCount();
        return result;

    }
//متد های صفحه لاگین

    public boolean Delete_Register(String username,String password) {

       boolean result;
       try {

           //یادت باشه ک کوئری حدف رو باید ب این تغییر بدی ک وقتی ی اکانت حذف شد تمام مواردی ک اون بنگاه ب عنوان ملک اضافه کرده حذف شه
//           DELETE
//                   FROM tbl_register R INNER JOIN tbl_cases C ON R.Bongah_Name=C.Bongah_Name AND R.Bongah_Place=C.Situation_Bongah
//           WHERE R.Username='hoseyn' AND R.Password='92213028'

            String delete_reg = "DELETE FROM tbl_register WHERE " +
                    "" +Register.Username+ "='" +username+ "' and " +Register.Password+ "='" +password+ "'";
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(delete_reg);
            db.close();
            result = true;
       }
       catch (Exception e) {
            result = false;
       }
        return result;
    }

    //یادت باشه ک این متد ویرایش رو هم باید بزاری ک اگه طرف اسم بنگاشو تو جدول ثبت نام تغییر داد تو جدول املاک هم تغییر کنه
    //البته میتونه این متدنباشه چون خیلی کم پیش میاد اسم بنگاه تغییر کنه واگرم بخواد مکانش رو تغییر بده هم باید کل اکانتش رو حذف کنه
    //چون دیگه نمیتونه خونه و املاک اون محل رو داشته باشه  باید خونه های محله جدید رو وارد کنه
//    public boolean Update_Register(String username,String password) {
//
//        boolean result;
//        try {
//
//            String update_reg ="UPDATE tbl_register SET " +
//                    "Bongah_Name='shahrivar',Bongah_Place='elahiye'\n" +
//                    "WHERE Username='" +username+ "' and Password='" +password + "'";
//            SQLiteDatabase db = this.getWritableDatabase();
//            db.execSQL(update_reg);
//            db.close();
//            result = true;
//        }
//        catch (Exception e) {
//            result = false;
//        }
//        return result;
//    }


    //متد های جدول املاک ذخیره شده

    public boolean Insert_Cases(Cases cases) {

        boolean result;
        try {
            String insert_case = "INSERT INTO tbl_cases(Goal,Type_Home,Situation_Home,Land_Measurement,Price,Situation_Bongah) " +
                    " VALUES('" + Cases.Goal + "','" + Cases.Type_Home + "','" + Cases.Situation_Home + "'," +
                    "" + Cases.Land_Measurement + "," + Cases.Orijinal_Price + "," + Cases.Ejare_Price + ",'" + Cases.Situation_Bongah + "')";

            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(insert_case);
            db.close();
            result = true;
        }

        catch (Exception e) {
            result = false;
        }
        return result;
    }

    public boolean Delete_Cases(String goal,String type_home,String situation_home,
                                int land_masurement,int Orijinal_Price,int Ejare_Price,String particulars,String situation_bongah,String Bongah_Name) {

        boolean result;
        try {


            String delete_case = "DELETE FROM tbl_cases WHERE " +Cases.Goal+ "='" +goal+ "' and " +Cases.Type_Home+ "='" +type_home+ "' and" +
                    "" +Cases.Situation_Home+ "='" +situation_home+ "' and " +
                    "" +Cases.Land_Measurement+ "=" +land_masurement+ " and " +Cases.Orijinal_Price+ "=" +Orijinal_Price+ " and " +Cases.Ejare_Price+ "=" +Ejare_Price+ "" +
                    "" +Cases.Particulars+ " = '" +particulars+ "' and " +Cases.Situation_Bongah+ "='" +situation_bongah+ "'" +
                    " " +Cases.Bongah_Name+ "='" +Bongah_Name+ "' ";
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(delete_case);
            db.close();
            result = true;
        }
        catch (Exception e) {
            result = false;

        }
        return result;
    }

    //متد صفحه جستجو

    public Cases Show_Search(String goal,String type_home,String situation_home,
                             int size, int original_price,int rent_price){

        Cases home = new Cases();

        try {
//            String show = "SELECT *" +
//                    " FROM tbl_cases" +
//                    " WHERE Goal='" + goal + "'AND Type_Home='" + type_home + "' AND Situation_Home='" + situation_home + "'" +
//                    " AND " + lower_size + "<=" + Cases.Land_Measurement + "<=" + upper_size + " AND " +
//                    lower_price + "<=" + Cases.Price + "<=" + upper_price + "";

            String show = "SELECT *" +
                    " FROM tbl_cases" +
                    " WHERE Goal='" + goal + "'AND Type_Home='" + type_home + "' AND Situation_Home='" + situation_home + "'" +
                    " AND Land_Measurement=" +size+ "  AND  Orijinal_Price=" +original_price+ " AND Ejare_Price=" +rent_price+ "";


            SQLiteDatabase db = this.getReadableDatabase();

            //ظرفی برای نتیجه برگنداده شده از کوئری
            Cursor cursor = db.rawQuery(show, null);

            //اگه سطری به عنوان نتیجه کوئری برگردانده شده بود انگاه دستورات شرط اجرا شود
            if (cursor.moveToFirst() == true) {

                do {
                    home.Goal = cursor.getString(1);
                    home.Type_Home = cursor.getString(2);
                    home.Situation_Home = cursor.getString(3);
                    home.Land_Measurement = cursor.getInt(4);
                    home.Orijinal_Price = cursor.getInt(5);
                    home.Ejare_Price = cursor.getInt(6);
                    home.Particulars = cursor.getString(7);
                    home.Bongah_Name = cursor.getString(8);
                    home.Situation_Bongah = cursor.getString(9);
                }
                while (cursor.moveToNext());
            }
        }
        catch (Exception e){
            //نباید آبجکت "هم"رو نال گذاشت باید بک"بولین" تعریف کنیم ک بولین فالس برگردونه اگه نتیجه ای از جستجو پیدا نشد
            home = null;
        }

        return home;

    }

}
