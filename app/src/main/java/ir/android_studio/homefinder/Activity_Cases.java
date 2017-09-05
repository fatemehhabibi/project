package ir.android_studio.homefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Cases extends AppCompatActivity {

    DatabaseManager db ;

    TextView Welcome_insert_home,Goal,Type_home,Situation_home,Map_home,Situation_bongah,Map_bongah,
    Bongah_name, Land_measurement,Price,Enter_particulars;
   // Spinner Spinner_goal,Spinner_type_home;
    EditText Enter_land_measurement,Original_price,Ejare_price;
    Button Insert_case,Delete_case,Update_case;

    String [] goal = {"خرید","رهن","اجاره"};
    //اونایی ک قصد فروش ملک رو دارن باید خودشون ب بنگاه مراجعه کنن تا خود بنگاهدار اطلاعات رو وارد اپلیکیشن کند

    String [] type_home = {"آپارتمان","ویلایی","کلنگی","پنت هاوس","ویلا","زمین مسکونی","اداری","تجاری",
                           "زمین تجاری","زمین زراعی","باغ","انبار","کارخانه","مغازه"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__cases);

        Intent intent = getIntent();

        Welcome_insert_home = (TextView) findViewById(R.id.welcome_insert_home);
        Goal = (TextView) findViewById(R.id.goal);
        Type_home = (TextView) findViewById(R.id.type_home);
        Situation_home = (TextView) findViewById(R.id.situation_home);
        Situation_bongah = (TextView) findViewById(R.id.situation_bongah);
        Map_home = (TextView) findViewById(R.id.map_home);
        Map_bongah = (TextView) findViewById(R.id.map_bongah);
        Bongah_name = (TextView) findViewById(R.id.bongah_name);
        Land_measurement = (TextView) findViewById(R.id.land_measurement);
        Price = (TextView) findViewById(R.id.price);
        Enter_particulars = (TextView)findViewById(R.id.particulars);

        final Spinner Spinner_goal = (Spinner) findViewById(R.id.spinner_goal);
        final Spinner Spinner_type_home = (Spinner) findViewById(R.id.spinner_type_home);

        Enter_land_measurement = (EditText) findViewById(R.id.enter_land_measurement);
        Original_price = (EditText) findViewById(R.id.original_price);
        Ejare_price = (EditText) findViewById(R.id.rent_price);
        //بعد از اینا باید دوتا جایی ک ب مپ وصل میشم واسه ادرس ملک و بنگاه رو اینجا معرفی کنم

         Insert_case = (Button) findViewById(R.id.insert_case);
         Delete_case = (Button) findViewById(R.id.delete_case);
        // Update_case = (Button) findViewById(R.id.update_case);

        ArrayAdapter<String> adapter_goal = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, goal);
        // adapter_goal.setDropDownViewTheme(R.layout.support_simple_spinner_dropdown_item);
        Spinner_goal.setAdapter(adapter_goal);
        Spinner_goal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(Activity_Cases.this, "" + goal[position], Toast.LENGTH_SHORT).show();

                if (goal[position]== "خرید")
                    Ejare_price.setEnabled(false);
                 //یا میتونیم اصلا  "ادیت تکس مربوط به اجاره"رو فالس نکنیم (ینی اصلا دو خط شرط بالا رو ننویسیم)
                // بجاش بنگاه دار اگر خونه فروشی بود مقدار اجاره رو 0 بذاره

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(Activity_Cases.this, "لطفا تمامی فیلدهارا کامل کنید", Toast.LENGTH_SHORT).show();
            }
        });


        ArrayAdapter<String> adapter_type_home = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, type_home);
        // adapter_type_home.setDropDownViewTheme(R.layout.support_simple_spinner_dropdown_item);
        Spinner_type_home.setAdapter(adapter_type_home);
        Spinner_type_home.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(Activity_Cases.this, "" + type_home[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(Activity_Cases.this, "لطفا تمامی فیلدهارا کامل کنید", Toast.LENGTH_SHORT).show();
            }
        });

        db = new DatabaseManager(this);

        Insert_case.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cases sample = new Cases();

                String Goal = Spinner_goal.getSelectedItem().toString();
                String Type_Home = Spinner_type_home.getSelectedItem().toString();
                //اینجا باید به گوگل مپ وصل شم واسه دوتا استرینگ پایینی ( باید بجاشون یه چیز دیگه بنویسم)حواسم باشه
                String Situation_Home = Map_home.getText().toString();
                String Situation_Bongah = Map_bongah.getText().toString();
                String Bongah_Name = Bongah_name.getText().toString();
                int  Land_Measurement = Integer.parseInt( Enter_land_measurement.getText().toString());
                int  Orijinal_Price = Integer.parseInt(Original_price.getText().toString());
                int  Ejare_Price = Integer.parseInt(Ejare_price.getText().toString());
                String Particulars = Enter_particulars.getText().toString();

                sample.Goal = Goal;
                sample.Type_Home = Type_Home;
                sample.Situation_Home = Situation_Home;
                sample.Situation_Bongah = Situation_Bongah;
                sample.Bongah_Name = Bongah_Name;
                sample.Land_Measurement = Land_Measurement;
                sample.Orijinal_Price = Orijinal_Price;
                sample.Ejare_Price = Ejare_Price;
                sample.Particulars = Particulars;

                boolean result = db.Insert_Cases(sample);

                if(result == true)
                    Toast.makeText(getApplicationContext(),"عملیات اضافه کردن ملک با موفقیت انجام شد",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"متاسفانه ملک مورد نظر اضافه نشد",Toast.LENGTH_LONG).show();


            }

        });
        Delete_case.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Goal = Spinner_goal.getSelectedItem().toString();
                String Type_Home = Spinner_type_home.getSelectedItem().toString();
                //اینجا باید به گوگل مپ وصل شم واسه دوتا استرینگ پایینی ( باید بجاشون یه چیز دیگه بنویسم)حواسم باشه
                String Situation_Home = Map_home.getText().toString();
                String Situation_Bongah = Map_bongah.getText().toString();
                String Bongah_Name = Bongah_name.getText().toString();
                String Particulars = Enter_particulars.getText().toString();
                int    Land_Measurement = Integer.parseInt( Enter_land_measurement.getText().toString());
                int  Orijinal_Price = Integer.parseInt(Original_price.getText().toString());
                int  Ejare_Price = Integer.parseInt(Ejare_price.getText().toString());

                boolean result = db.Delete_Cases(Goal,Type_Home,Situation_Home,Land_Measurement,Orijinal_Price,Ejare_Price
                        ,Particulars,Bongah_Name,Situation_Bongah);

                if (result == true)
                    Toast.makeText(getApplicationContext(),"ملک مورد نظر با موفقیت حذف شد",Toast.LENGTH_LONG).show();



                else
                    Toast.makeText(getApplicationContext(),"متاسفانه عملیات حذف با شکست مواجه شد",Toast.LENGTH_LONG).show();

            }
        });


    }
}
