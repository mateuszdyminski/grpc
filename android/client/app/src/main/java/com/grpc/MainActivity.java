package com.grpc;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends Activity {
    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView=(TextView)findViewById(R.id.textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startGrpcOnClick(View v) {
        txtView.setText("Grpc test started!");
        new RetrieveUsersTask().execute(Type.GRPC);
    }

    public void startGrpcStreamOnClick(View v) {
        txtView.setText("Grpc stream test started!");
        new RetrieveUsersTask().execute(Type.GRPC_STREAM);
    }

    public void startJsonOnClick(View v) {
        txtView.setText("Json test started!");
        new RetrieveUsersTask().execute(Type.JSON);
    }

    private class RetrieveUsersTask extends AsyncTask<Type, Void, String> {

        private final Logger logger = Logger.getLogger(RetrieveUsersTask.class.getName());

        public static final int BATCH_SIZE = 1;
        public static final int TOTAL = 10000;

        private AsyncClient client = new AsyncClient("192.168.3.152", 36111);
        private JsonClient jsonClient = new JsonClient("192.168.3.152", 10000);

        @Override
        protected String doInBackground(Type... params) {
            try {
                switch (params[0]) {
                    case GRPC:
                        return client.load(BATCH_SIZE, TOTAL);
                    case GRPC_STREAM:
                        return client.loadStream(BATCH_SIZE, TOTAL);
                    case JSON:
                        return jsonClient.load(BATCH_SIZE, TOTAL);
                    default:
                        throw new RuntimeException("wrong type");
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "can't load users", e);
                return "";
            }
        }

        protected void onPostExecute(String time) {
            txtView.setText(String.format("Fetched %d users. Took: %s", TOTAL, time));
        }
    }
}
