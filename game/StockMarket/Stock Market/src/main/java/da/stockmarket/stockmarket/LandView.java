package da.stockmarket.stockmarket;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import damulticast.Device;


import org.apache.http.client.ClientProtocolException;

import java.io.IOException;

import damulticast.Device;


public class LandView extends Activity {
protected static LandFactoryHandler lfHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_view);
        lfHandler = new LandFactoryHandler((ImageButton)this.findViewById(R.id.leftFactory), (ImageButton)this.findViewById(R.id.rightFactory));
        FactorySet.populate();
        //create connection
        new Connection().execute();//to create a connection with the tracker

        //end of create connection


    }


    private class Connection extends AsyncTask {

        @Override
        protected Object doInBackground(Object... arg0) {
            connect();
            return null;
        }

    }

    private void connect() {
        try {
            Device device = new Device();
            String serverIP="192.168.56.1";
            //String serverIP="10.9.145.111";//running a tracker in that localhost(this ip address is recognized by genymotion)
            device.establishConnection(serverIP);

        } catch (ClientProtocolException e) {
            Log.d("HTTPCLIENT", e.getLocalizedMessage());
        } catch (IOException e) {
            Log.d("HTTPCLIENT", e.getLocalizedMessage());
        }
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.land_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void leftFactoryClicked(View view){

        Intent intent = new Intent(this, FactoryListActivity.class);
        intent.putExtra("whichFactory", 0);
        startActivity(intent);
    }
    public void rightFactoryClicked(View view){
        Intent intent = new Intent(this, FactoryListActivity.class);
        intent.putExtra("whichFactory", 1);
        startActivity(intent);
    }
}
