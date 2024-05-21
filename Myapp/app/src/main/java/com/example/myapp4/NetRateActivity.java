package com.example.myapp4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class NetRateActivity extends AppCompatActivity implements Runnable{

    Button btnNetRate;
    TextView showRate;
    Handler handler;
    private static final String TAG = "NetRateActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_rate);
        btnNetRate = findViewById(R.id.getRate);
        showRate = findViewById(R.id.netRateShow);
        handler = new Handler(Objects.requireNonNull(Looper.myLooper())){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 5) {
                    Log.i(TAG, "handleMessage: ....");
                    showRate.setText((String)msg.obj);
                }
                super.handleMessage(msg);
            }
        };

    }
    public void onClick(View view){
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
//        URL url = null;
        StringBuilder html = new StringBuilder("货币名称\t\t价值\n人民币\t\t100\n");
        try {
//            url = new URL("https://www.safe.gov.cn/AppStructured/hlw/RMBQuery.do");
//            HttpURLConnection con = (HttpURLConnection)url.openConnection();
//            InputStream inputStream = con.getInputStream();
//            html = inputStream2String(inputStream);
            Document doc = Jsoup.connect("https://www.safe.gov.cn/AppStructured/hlw/RMBQuery.do").get();
            Elements tables = doc.getElementsByTag("table");
            Element table = tables.get(0);
            Elements table2 = table.getElementsByTag("table");
            Element table3 = table2.get(4);
//            Element table4 = table3.getElementsByTag("table").first();
            Log.i(TAG, "run: table4:"+table3);


            Elements trs = table3.getElementsByTag("tr");

            Element tr = trs.get(0);
            Elements contrytds = tr.getElementsByTag("th");
            Element tr2 = trs.get(1);
            Elements ratetds = tr2.getElementsByTag("td");
            Log.i(TAG, "run: ratetds:"+ratetds);
            for (int j = 0; j < 26; j++) {
                Element td1 = contrytds.get(j);
                Element td2 = ratetds.get(j);
                String contry = td1.text();
                String val = td2.text();
                html.append(contry).append("\t\t").append(val).append("\n");
            }

        }catch (Exception e){
            Log.i(TAG, "run: "+e);
        }
        Message msg = handler.obtainMessage(5, html.toString());
        handler.sendMessage(msg);
    }
    private String inputStream2String(InputStream inputStream) throws IOException{
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        while (true){
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0) {
                break;
            }
            out.append(buffer, 0, buffer.length);
        }
        return out.toString();
    }
}