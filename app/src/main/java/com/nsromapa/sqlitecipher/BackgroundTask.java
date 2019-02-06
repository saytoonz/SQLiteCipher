package com.nsromapa.sqlitecipher;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.widget.Toast;

import com.nsromapa.sqlitecipher.Helper.DBHelper;
import com.nsromapa.sqlitecipher.Helper.DBObjects;


public class BackgroundTask extends AsyncTask<String, Void, String> {

    private Context context;

    public BackgroundTask(Context context) {
        this.context = context;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... params) {

        DBOperations dbOperations = new DBOperations(context);

        if (params[0].equals("insert_info")){
            String id = params[1];
            String name = params[2];
            int price = Integer.parseInt(params[3]);
            int qty = Integer.parseInt(params[4]);

            SQLiteDatabase database = dbOperations.getWritableDatabase();
            dbOperations.addInformations(database,id,name,price,qty);

            dbOperations.AddtoFollowAndFriends(DBObjects.DBHelperObjects.FOLLOWING_TABLE_NAME,database,id,name,name,name,name);
            dbOperations.AddtoFollowAndFriends(DBObjects.DBHelperObjects.FOLLOWERS_TABLE_NAME,database,id,name,name,name,name);
            dbOperations.AddtoFollowAndFriends(DBObjects.DBHelperObjects.FRIENDS_TABLE_NAME,database,id,name,name,name,name);

            dbOperations.AddUser(database,id,name,name,name,name,name);
            dbOperations.AddMessage(database,id,name,name,name,name,name,name,name,id,id);
            dbOperations.AddStories(database,id,name,name,name,name,name,name,name,id,id);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            database.close();
            return "One row inserted";
        }else if (params[0].equals("add_params")){

            DBHelper.getInstance(context).insertNewMail(params[1]);
            return "inserted";
        }

        return null;

    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
//        super.onPostExecute(result);

    }

}
