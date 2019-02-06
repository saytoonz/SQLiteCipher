package com.nsromapa.sqlitecipher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SaveInfo extends AppCompatActivity {

    private EditText txt_id,txt_name,txt_price, txt_qty;
    private String id, name, price, qty;

    private BackgroundTask backgroundTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_info);

        txt_id = findViewById(R.id.d_id);
        txt_name = findViewById(R.id.d_name);
        txt_price = findViewById(R.id.d_price);
        txt_qty = findViewById(R.id.d_qty);


    }

    public void SaveData(View view) {
        id = txt_id.getText().toString();
        name = txt_name.getText().toString();
        price = txt_price.getText().toString();
        qty = txt_qty.getText().toString();

        backgroundTask = new BackgroundTask(this);
        backgroundTask.execute("insert_info",id,name,price,qty);
//        finish();
    }
}
