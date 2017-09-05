package ir.android_studio.homefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Register extends AppCompatActivity {

   // private static count;
   static DatabaseManager db ;

    TextView username_show,password_show,bongah_name_show,bongah_place_show;
    static TextView count;
    EditText username,password,bongah_name,bongah_place;
    Button btn_insert_account,btn_delete_acount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__register);

        Intent intent = getIntent();

        username_show = (TextView)findViewById(R.id.username_show);
        password_show = (TextView)findViewById(R.id.password_show);
        bongah_name_show = (TextView)findViewById(R.id.bongah_name_show);
        bongah_place_show = (TextView)findViewById(R.id.bongah_place_show);
        count = (TextView)findViewById(R.id.count);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        bongah_name = (EditText)findViewById(R.id.bongah_name);
        bongah_place = (EditText)findViewById(R.id.bongah_place);

        btn_insert_account = (Button)findViewById(R.id.btn_insert_acount);


        db = new DatabaseManager(this);

        counter();

        btn_insert_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Username = username.getText().toString();
                String Password = password.getText().toString();
                String Bongah_name = bongah_name.getText().toString();
                String Bongah_place = bongah_place.getText().toString();

                Register register = new Register();

                register.ID = db.Get_Register_Count()+1; //بعلاوه 1 شده چون در دیتابیس آی دی از 0 شروع میشه ب شمردن
                register.Username = Username;
                register.Password = Password;
                register.Bongah_Name = Bongah_name;
                register.Bongah_Place = Bongah_place;

                if(Bongah_name == null || Bongah_place == null)

                    Toast.makeText(Activity_Register.this, "لطفا تمامی موارد خواسته شده را تکمیل کنید", Toast.LENGTH_SHORT).show();

                else {
                    boolean result = db.Insert_Register(register);
                    if(result == true) {
                        Toast.makeText(getApplicationContext(), "عملیات ثبت نام با موفقیت انجام شد", Toast.LENGTH_LONG).show();
                        counter();
                    }
                    else
                        Toast.makeText(getApplicationContext(),"متاسفانه عملیات ثبت نام با شکست مواجه شد",Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    public static void counter(){
        int result = db.Get_Register_Count();
        count.setText(Integer.toString(result) + "کاربر");
    }

    //این کانتر برای موقعی هست ک یک اکانت حذف شده...البته از این متد در صفحه لاگین استفاده میشه
    public static void counter_delete(){
        int result = db.Get_Register_Count()- 1;
        count.setText(Integer.toString(result) + "کاربر");
    }
}
