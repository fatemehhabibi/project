package ir.android_studio.homefinder;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_Login extends AppCompatActivity {

    DatabaseManager db;
    TextView Username_tv,Password_tv,LoginLocked_tv;
    EditText Username_login,Password_login;
    Button Login,Btn_delete_acount;
            //Btn_update_acount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);

        Intent intent = getIntent();

        Username_tv = (TextView)findViewById(R.id.username_tv);
        Password_tv = (TextView)findViewById(R.id.password_tv);
        LoginLocked_tv = (TextView)findViewById(R.id.loginLocked_tv);
        Username_login = (EditText)findViewById(R.id.username_login);
        Password_login = (EditText)findViewById(R.id.password_login);
        Login = (Button)findViewById(R.id.login);
        Btn_delete_acount = (Button)findViewById(R.id.btn_delete_acount);
       // Btn_update_acount = (Button)findViewById(R.id.btn_update_acount);

        db = new DatabaseManager(this);


                final String user = Username_login.getText().toString();
                final String pass = Password_login.getText().toString();
//باید یک آرایه از رجیستر(با طول سطرهای جدول) ک هر آبجکت از رجیستر حاوی یک سطر از دیتابیس باشد
                final Register login_reg = new Register();

       // ArrayList<Register> row_register = new ArrayList<Register>();




              Login.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      int NumberOfTryToLogin = 3;
                      int NumberOfRow = db.Get_Register_Count();
                      int Row = Register.ID;

                      for ( Row = 1; Row<=NumberOfRow ; Row++){
                          if(user.equals(login_reg.Username)&& pass.equals(login_reg.Password)) {
                              Toast.makeText(getApplicationContext(), "صحت سنجی با موفقیت انجام شد.اکنون میتوانید وارد شوید", Toast.LENGTH_LONG).show();

                              Intent home = new Intent(Activity_Login.this,Activity_Cases.class);
                              startActivity(home);
                              //حالا اینجا باید این اجازه رو ب بنگاهی بدم ک بتونه ملک اضافه کنه ینی وارد صفحه اضاف کردن ملک بره دبگه دکمه اضافه
                              //کردن ملک نمیخواد همون ی سره در صورت موفق بودن ثبت لاگین بره ک مشخصات خونه رو وارد کنه

                          }
                          else if(Row == NumberOfRow){
                              Toast.makeText(getApplicationContext(), "رمز عبور یا نام کاربری اشتباه است", Toast.LENGTH_LONG).show();
                              NumberOfTryToLogin --;
                          }

                      }

                      if (NumberOfTryToLogin == 0){
                          Login.setEnabled(false);
                          LoginLocked_tv.setText("شما بیش از حد مجاز رمز عبور یا نا کاربری را وارد کردید");
                      }
                  }
              });
//اگه ی موقع اسم بنگاه یا محل بنگاه تغییر کرد باید بتونه اونارو تغییر بده و همزمان نام و محل بنگاه در جدول کیس هم عوض شه
//        Btn_update_acount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String Username = user.toString();
//                String Password = pass.toString();
//
//                boolean result = db.Delete_Register(Username,Password);
//
//            }
//        });

        Btn_delete_acount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Username = user.toString();
                String Password = pass.toString();

              boolean result = db.Delete_Register(Username,Password);

                if (result == true)
                    Toast.makeText(getApplicationContext(),"حساب کاربری با موفقیت حذف شد",Toast.LENGTH_LONG).show();

                else
                    Toast.makeText(getApplicationContext(),"متاسفانه عملیات حذف با شکست مواجه شد",Toast.LENGTH_LONG).show();

                //حتما باید متد کانتر صدا زده شه یکی از کاربرا ک حذف شد رو از تعداد کل کاربران کم کنه...حواسم باشه
             Activity_Register.counter_delete() ;

            }
        });



    }
}
