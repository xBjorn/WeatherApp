package bjorn.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;




public class MainActivity extends AppCompatActivity {


    //Globale variabele
    String inputLocatie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        //Code voor URL request en JSON te lezen
        private static String readAll(Reader rd) throws IOException {
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            return sb.toString();
        }

        //Code voor URL request en JSON te lezen
        public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
            InputStream is = new URL(url).openStream();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                String jsonText = readAll(rd);
                JSONObject json = new JSONObject(jsonText);
                return json;
            } finally {
                is.close();
            }
        }





    public void doeIets(View view){
        //Update de waarde van de input van de TextView bij elke klik op de button
        TextView textView = (TextView) findViewById(R.id.test);

        inputLocatie = textView.getText().toString();

  }

    public void main(String[] args) throws IOException, JSONException {
        //Voeg de input van de TextView aan de URL toe
        JSONObject json = readJsonFromUrl("http://weerlive.nl/api/json-data-10min.php?key=36e9560c2e&locatie="+inputLocatie);
        //Pak de temperatuur uit de JSON array
        TextView tempOut = (TextView) findViewById(R.id.txtTemp);
        tempOut.setText(json.getString("temp"));
    }
}
