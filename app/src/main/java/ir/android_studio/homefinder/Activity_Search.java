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

public class   Activity_Search extends AppCompatActivity {

    DatabaseManager db ;

    TextView Goal_search,Type_home_search,Situation_home_search,Map_home_search,Land_measurement_search,Price_search;
    EditText Lower_size,Upper_size,Bm_lower_price,Bm_upper_price,Rent_lower_price,Rent_upper_price;
    Button Btn_search;
   // Spinner Enter_goal_search,Enter_type_home;
    String [] goal_search = {"خرید","رهن","اجاره"};
    //اونایی ک قصد فروش ملک رو دارن باید خودشون ب بنگاه مراجعه کنن تا خود بنگاهدار اطلاعات رو وارد اپلیکیشن کند

    String [] type_home_search = {"آپارتمان","ویلایی","کلنگی","پنت هاوس","ویلا","زمین مسکونی","اداری","تجاری",
            "زمین تجاری","زمین زراعی","باغ","انبار","کارخانه","مغازه"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__search);
        final Intent intent = getIntent();

        Goal_search = (TextView) findViewById(R.id.goal_search);
        Type_home_search = (TextView) findViewById(R.id.type_home_search);
        Situation_home_search = (TextView) findViewById(R.id.situation_home_search);
        Map_home_search = (TextView) findViewById(R.id.map_home_search);
        Land_measurement_search = (TextView) findViewById(R.id.land_measurement_search);
        Price_search = (TextView) findViewById(R.id.price_search);

        Lower_size = (EditText) findViewById(R.id.lower_size);
        Upper_size = (EditText) findViewById(R.id.upper_size);
        Bm_lower_price = (EditText) findViewById(R.id.bm_lower_price);
        Bm_upper_price = (EditText) findViewById(R.id.bm_upper_price);
        Rent_lower_price = (EditText) findViewById(R.id.rent_lower_price);
        Rent_upper_price = (EditText) findViewById(R.id.rent_upper_price);


        Btn_search = (Button) findViewById(R.id.btn_search);

        final Spinner Enter_goal_search = (Spinner) findViewById(R.id.enter_goal_search);
        final Spinner Enter_type_home_search = (Spinner) findViewById(R.id.enter_type_home_search);

        ArrayAdapter<String> adapter_goal_search = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, goal_search);
        // adapter_goal_search.setDropDownViewTheme(R.layout.support_simple_spinner_dropdown_item);
        Enter_goal_search.setAdapter(adapter_goal_search);
        Enter_goal_search.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(Activity_Search.this, "" + goal_search[position], Toast.LENGTH_SHORT).show();

                if(goal_search[position] ==  "خرید" || goal_search[position] ==  "رهن"){
                    Rent_lower_price.setEnabled(false);
                    Rent_upper_price.setEnabled(false);
                }
                else{
                    Bm_lower_price.setEnabled(false);
                    Bm_upper_price.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(Activity_Search.this, "لطفا تمامی فیلدهارا کامل کنید", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter<String> adapter_type_home_search = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, type_home_search);
        //adapter_type_home_search.setDropDownViewTheme(R.layout.support_simple_spinner_dropdown_item);
        Enter_type_home_search.setAdapter(adapter_type_home_search);
        Enter_type_home_search.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(Activity_Search.this, "" + type_home_search[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(Activity_Search.this, "لطفا تمامی فیلدهارا کامل کنید", Toast.LENGTH_SHORT).show();
            }
        });

        db = new DatabaseManager(this);

        Btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String goal_Search = Enter_goal_search.getSelectedItem().toString();
                String type_Home_Search = Enter_type_home_search.getSelectedItem().toString();
                String map_home_search = Map_home_search.getText().toString();
                int    lower_size = Integer.parseInt(Lower_size.getText().toString());
                int    upper_size = Integer.parseInt(Upper_size.getText().toString());
                int    bm_lower_price = Integer.parseInt(Bm_lower_price.getText().toString());
                int    bm_upper_price = Integer.parseInt(Bm_upper_price.getText().toString());
                int    rent_lower_price = Integer.parseInt(Rent_lower_price.getText().toString());
                int    rent_upper_price = Integer.parseInt(Rent_upper_price.getText().toString());

                Cases wanted = new Cases();
                int size = wanted.Land_Measurement;
                int original_price = wanted.Orijinal_Price;
                int rent_price = wanted.Ejare_Price;

                if(goal_Search ==  "خرید" || goal_Search == "رهن") {

                    if (wanted.Goal == goal_Search && wanted.Type_Home == type_Home_Search && wanted.Situation_Home == map_home_search &&
                            lower_size <= size && size <= upper_size &&
                            bm_lower_price <= original_price && original_price <= bm_upper_price )
////یادت باشه این خط پایین رو حتما باید درست کنم وگرنه نمیتونه سرچی ک کاربر انجام داده رو نشون بده

                        wanted = db.Show_Search(String goal_Search,String type_Home_Search,String map_home_search, int size,
                    int original_price,int rent_price);
                    else{
                        //  if (wanted == null)
                        Toast.makeText(new AppCompatActivity(), "هیچ موردی یافت نشد", Toast.LENGTH_LONG).show();
                    }
                }

                else if (wanted.Goal == goal_Search && wanted.Type_Home == type_Home_Search && wanted.Situation_Home == map_home_search &&
                        lower_size <= size && size <= upper_size &&
                        rent_lower_price <= rent_price && rent_price <= rent_upper_price )
////یادت باشه این خط پایین رو حتما باید درست کنم وگرنه نمیتونه سرچی ک کاربر انجام داده رو نشون بده

                    wanted = db.Show_Search(String goal_Search,String type_Home_Search,String map_home_search, int size,
                int original_price,int rent_price);
                    else{
                    //  if (wanted == null)
                    Toast.makeText(new AppCompatActivity(), "هیچ موردی یافت نشد", Toast.LENGTH_LONG).show();
                }
            }

            }
        });
    }
}
