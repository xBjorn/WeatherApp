package bjorn.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.net.URL;






public class MainActivity extends AppCompatActivity {



    //Globale variabele
    String inputLocatie;
    String bo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //Lees de InputStream en converteer van Bytes naar String
    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }

    public void doeIets(View view) throws IOException   {
        //Update de waarde van de input van de TextView bij elke klik op de button
        TextView textView = (TextView) findViewById(R.id.plaatsInput);
        inputLocatie = textView.getText().toString();
        System.out.println(bo);


        URL url = new URL("http://www.android.com/");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            readStream(in);
        } finally {
            urlConnection.disconnect();
        }



    }

    }



