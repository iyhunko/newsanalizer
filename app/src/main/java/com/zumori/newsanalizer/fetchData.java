package com.zumori.newsanalizer;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Abhishek Panwar on 7/14/2017.
 */
public class fetchData extends AsyncTask<Void, Void, Void> {
    String data ="";
    String dataParsed = "";
    String singleParsed ="";
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Log.d("api_item", "OK1");
            URL url = new URL("https://zumori.com/api/v1/news");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            Log.d("api_item", "OK2");
            dataParsed = data;
//            JSONArray JA = new JSONArray(data);

//            for(int i =0 ;i < JA.length(); i++){
//                JSONObject JO = (JSONObject) JA.get(i);
//
//
//
//                singleParsed =  JO.toString() + "\n";
////                +
////                        "Password:" + JO.get("password") + "\n"+
////                        "Contact:" + JO.get("contact") + "\n"+
////                        "Country:" + JO.get("country") + "\n";
//
//                dataParsed = dataParsed + singleParsed +"\n" ;
//            }

            Log.d("api_item", "OK3");
//            dataParsed = JA.toString();
            Log.d("api_item", "OK4");
            Log.d("api_item", dataParsed);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Main2Activity.data.setText(this.dataParsed);

    }
}