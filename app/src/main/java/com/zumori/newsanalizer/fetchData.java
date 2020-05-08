package com.zumori.newsanalizer;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.*;

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
    String newsList[];
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

            JSONObject obj = new JSONObject(data);
            JSONArray arr = obj.getJSONArray("data");
            String post_id = null;

            if(arr != null && arr.length() > 0) {
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject item = arr.getJSONObject(i);
                    String id = item.getString("id");
                    post_id = post_id + "|" + id;

                }
            }

            String[] newsList = { "jalapeno", "anaheim", "serrano",
                    "habanero", "thai" };


            dataParsed = post_id;

//            String pageName = obj.getJSONObject("pageInfo").getString("pageName");
//
//            JSONArray arr = obj.getJSONArray("posts");
//            for (int i = 0; i < arr.length(); i++)
//            {
//                String post_id = arr.getJSONObject(i).getString("post_id");
//
//            }
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
        } catch (IOException | JSONException e) {
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


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(NewsListActivity.thisActivity, android.R.layout.simple_list_item_1, newsList);

        NewsListActivity.data.setAdapter(adapter);

//        listView.setAdapter(adapter);

    }
}