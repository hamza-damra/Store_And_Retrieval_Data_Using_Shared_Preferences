package com.example.store_data_using_shared_preferances;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    EditText key, value;
    Button button_go;
    TextView tv_restored_data;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.sp_items);
        key = findViewById(R.id.et_key);
        value = findViewById(R.id.et_value);
        button_go = findViewById(R.id.button_go);
        tv_restored_data = findViewById(R.id.tv_restored_data);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        sharedPreferencesEditor = sharedPreferences.edit();
        String key_now = key.getText().toString().trim();
        String value_now = value.getText().toString().trim();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    key.setVisibility(View.VISIBLE);
                    value.setVisibility(View.VISIBLE);
                }else
                {
                    key.setVisibility(View.VISIBLE);
                    value.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                key.setVisibility(View.VISIBLE);
                value.setVisibility(View.GONE);
            }
        });

        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data;
                // Store Data
                if(key.getVisibility() == View.VISIBLE && value.getVisibility() == View.VISIBLE)
                {

                    if(!key_now.isEmpty() && !value_now.isEmpty())
                    {
                        sharedPreferencesEditor.putString(key.getText().toString(),value.getText().toString());
                        sharedPreferencesEditor.apply();
                        Toast.makeText(getBaseContext(),"Store Finished",Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(getBaseContext(), "Please Fill All Filed", Toast.LENGTH_SHORT).show();

                    }
                }else
                {
                    if (!key_now.isEmpty()) {
                        data = sharedPreferences.getString(key.getText().toString(), "No Data Stored Have This Key");
                        tv_restored_data.setText(data);
                    }else
                    {
                        Toast.makeText(getBaseContext(), "Please Fill All Filed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}