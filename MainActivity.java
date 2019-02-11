package com.example.kontess.booklibrary;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText editText;
    Button btn;
    ListView listview;
    Veritabani veritabani;
    MyAdapter adapter;
    ArrayList<Book> bulunanbooklist = new ArrayList<>();
    String girilen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        listview = (ListView) findViewById(R.id.lst);


        this.veritabani = new Veritabani(getApplicationContext());


    }

    public void Metot(View view) {

        veritabani.ClearAll();//her buton clickte önceden aratılanlar silinmeli
        /*adapter=new MyAdapter(MainActivity.this, bulunanbooklist);
        listview.setAdapter(adapter);*/

        girilen = editText.getText().toString();

        try {

            DownloadRate downloadRate = new DownloadRate();

            String url = "http://gutendex.com/books/";
            downloadRate.execute(url);


        } catch (Exception ee) {
            ee.printStackTrace();
        }


    }

    private class DownloadRate extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            URL url;
            HttpURLConnection httpURLConnection;

            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();


                while (data > 0) {
                    char character = (char) data;
                    result += character;
                    data = inputStreamReader.read();
                }
                return result;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {

                JSONObject js = new JSONObject(s);


                for (int i = 0; i < 10; i++) { // apide 32 tane kitap bilgisi var ancak benim bilgisayarımda kastığı için i sayısını küçük tuttum

                    JSONObject jsondatas = (JSONObject) js.getJSONArray("results").get(i);
                    // kitapadı yazaradı yıl konular id

                    //JSONObject > results array > i. objesi > authors dizisi > 0. objesi > özellikleri

                    JSONObject jyazarlar = jsondatas.getJSONArray("authors").getJSONObject(0);
                    String yazar = jyazarlar.getString("name");
                    //JSONObject jsonobdeneme = (JSONObject) js.getJSONArray("authors").get(0);

                    Integer olumyili = jyazarlar.getInt("death_year");

                    //JSONObject jkonular = (JSONObject) jsondatas.getJSONArray("subjects").getJSONArray(0);
                    JSONArray jkonular = jsondatas.getJSONArray("subjects");
                    String konu = jkonular.getString(0);


                    Integer id = jsondatas.getInt("id");

                    String kitapadi = jsondatas.getString("title");


                    Book books = new Book(kitapadi, yazar, olumyili, konu, id);
                    veritabani.AddBookInfo(books);

                }
                bulunanbooklist = veritabani.GetAllLike(girilen);

                if (bulunanbooklist != null) {
                    adapter = new MyAdapter(MainActivity.this, bulunanbooklist);
                    listview.setAdapter(adapter);
                }


            } catch (Exception e) {
                e.printStackTrace();

            }


        }

    }
}

