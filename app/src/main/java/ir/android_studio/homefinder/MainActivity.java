package ir.android_studio.homefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView welcom;
    Button search ,login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcom=(TextView)findViewById(R.id.welcom);
        search=(Button)findViewById(R.id.search);
        login=(Button)findViewById(R.id.login);
        register=(Button)findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent reg = new Intent(MainActivity.this,Activity_Register.class);
                startActivity(reg);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent log = new Intent(MainActivity.this,Activity_Login.class);
                startActivity(log);

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent search = new Intent(MainActivity.this,Activity_Search.class);
                startActivity(search);
            }
        });


    }
}
