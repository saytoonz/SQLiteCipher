package com.nsromapa.sqlitecipher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.nsromapa.sqlitecipher.Helper.DBHelper;
import com.nsromapa.sqlitecipher.Helper.UpdateLocalDB;

import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;

import static com.nsromapa.sqlitecipher.DBOperations.DB_NAME;

public class MainActivity extends AppCompatActivity {

    Button BtnAdd, BtnDelete, BtnUpdate;
    EditText edtEmail;

    ListView lstEmail;

    String saveEmail = ""; // Save the current Email to update or delete

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase.loadLibs(this);


        edtEmail  = findViewById(R.id.edtEmail);
        lstEmail  = findViewById(R.id.lstEmail);
        lstEmail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) lstEmail.getItemAtPosition(position);
                edtEmail.setText(item);
                saveEmail = item;
            }
        });
        BtnAdd  = findViewById(R.id.BtnAdd);
        BtnUpdate  = findViewById(R.id.BtnUpdate);
        BtnDelete  = findViewById(R.id.BtnDelete);

        BtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundTask backgroundTask = new BackgroundTask(MainActivity.this);
                backgroundTask.execute("add_params",edtEmail.getText().toString());
//                DBHelper.getInstance(MainActivity.this).insertNewMail(edtEmail.getText().toString());
                reLoadEmails();
            }
        });

        BtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper.getInstance(MainActivity.this)
                        .updateEmail(saveEmail,edtEmail.getText().toString());

                reLoadEmails();
            }
        });

        BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper.getInstance(MainActivity.this)
                        .deleteNewMail(edtEmail.getText().toString());
                reLoadEmails();
            }
        });


        reLoadEmails();


//        if (checkDatabase()){
//            Intent intent = new Intent(this,SaveInfo.class);
//            startActivity(intent);
//        }else{
//            Intent intent = new Intent(this,UpdateLocalDB.class);
//            startActivity(intent);
//            finish();
//        }


    }

    public void reLoadEmails() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                                            android.R.layout.simple_list_item_1,
                                            android.R.id.text1,
                                            DBHelper.getInstance(MainActivity.this).getAllEmail());

        lstEmail.setAdapter(adapter);
    }

    public void addProduct(View view) {
        Intent intent = new Intent(this,SaveInfo.class);
        startActivity(intent);
    }

    public void createMyTables(View view) {
    }

    public boolean checkDatabase(){
        File dbFile = this.getDatabasePath(DB_NAME);
        return dbFile.exists();
    }

}
