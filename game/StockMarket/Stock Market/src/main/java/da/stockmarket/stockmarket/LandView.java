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
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


import damulticast.Device;
import damulticast.ResourceState;
import damulticast.RicartListener;


public class LandView extends Activity implements RicartListener {
    protected static LandFactoryHandler lfHandler;
    protected static boolean whichFactory;
    protected LandView thisObject;
    private Timer leftLandProduction;
    private Timer rightLandProduction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        thisObject = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_view);
        lfHandler = new LandFactoryHandler((ImageButton) this.findViewById(R.id.leftFactory), (ImageButton) this.findViewById(R.id.rightFactory));
        FactorySet.populate();

        new Connection().execute();

    }

    protected Device device;


    private class Connection extends AsyncTask {

        @Override
        protected Object doInBackground(Object... arg0) {
            connection();

            return null;
        }

    }



    private void connection() {
        try {

            device = new Device(this);
//            String serverIP = "192.168.56.1";
            String serverIP="172.20.10.3";//running a tracker in that localhost(this ip address is recognized by genymotion)
            device.establishConnection(serverIP);

        } catch (ClientProtocolException e) {
            Log.d("HTTPCLIENT", e.getLocalizedMessage());
        } catch (IOException e) {
            Log.d("HTTPCLIENT", e.getLocalizedMessage());
        }

        /* Start listening to P2P communication */
        Thread t = new Thread(device);
        t.start();

        /* Say hello to rest of peers */
        try {
            device.sayHello();
        } catch (Exception e) {
            System.err.println("An error occurred while saying hello: "
                    + e.getMessage());
        }

        /* Ask for the current state of the game */
        try {
            device.askForState();
        } catch (IOException ioe) {
            System.err.println("Peers couldn't respond to askForState(): "
                    + ioe.getMessage());
        }

        initializeNetworkResource();



        //  new Connection().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.land_view, menu);
        return true;

    }
    protected final int standardDuration = 5;
    protected static int leftLandTimer = -1;
    protected static int rightLandTimer = -1;

    @Override
    public void onStart() {
        super.onStart();
        leftLandProduction = new Timer();
        rightLandProduction = new Timer();

        if (LandFactoryHandler.factoryStarted) {
            System.out.println("inside production");
            if (!whichFactory) {

                //leftLandProduction.cancel();
                TimerTask task = new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                //factory just clicked
                                //seeking resources
                                //System.out.println("left called");
                                if(leftLandTimer == -1) {
                                    PlayerResources.beerAmount += LandFactoryHandler.leftFactory.beerAmount;
                                    PlayerResources.dairyAmount += LandFactoryHandler.leftFactory.dairy;
                                    PlayerResources.foodAmount += LandFactoryHandler.leftFactory.foodAmount;
                                    PlayerResources.metalAmount += LandFactoryHandler.leftFactory.metalAmount;
                                    PlayerResources.woodAmount += LandFactoryHandler.leftFactory.wood;
                                    leftLandTimer = 0;
                                    updateResourceView();
                                }
                                if(leftLandTimer == 0) {
                                    if(PlayerResources.beerAmount > -1 &&
                                            PlayerResources.dairyAmount > -1 &&
                                            PlayerResources.foodAmount > -1 &&
                                            PlayerResources.metalAmount > -1 &&
                                            PlayerResources.woodAmount > -1)
                                        leftLandTimer = standardDuration;
                                    else
                                        requestForResource();
                                }
                                //enough resources for factory to start working
                                if (leftLandTimer > 0) {
                                    if(leftLandTimer == 1) leftLandTimer--;
                                    leftLandTimer--;
                                }
                            }
                        });
                    }
                };
                leftLandProduction.scheduleAtFixedRate(task, 0, 1000);
            } else {
                //if(leftLandProduction.)
                //rightLandProduction.cancel();
                TimerTask task = new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                //factory just clicked
                                //seeking resources
                                //System.out.println("right called");
                                if(rightLandTimer == -1) {
                                    PlayerResources.beerAmount += LandFactoryHandler.rightFactory.beerAmount;
                                    PlayerResources.dairyAmount += LandFactoryHandler.rightFactory.dairy;
                                    PlayerResources.foodAmount += LandFactoryHandler.rightFactory.foodAmount;
                                    PlayerResources.metalAmount += LandFactoryHandler.rightFactory.metalAmount;
                                    PlayerResources.woodAmount += LandFactoryHandler.rightFactory.wood;
                                    rightLandTimer = 0;
                                    updateResourceView();
                                }
                                if(rightLandTimer == 0) {
                                    if(PlayerResources.beerAmount > -1 &&
                                            PlayerResources.dairyAmount > -1 &&
                                            PlayerResources.foodAmount > -1 &&
                                            PlayerResources.metalAmount > -1 &&
                                            PlayerResources.woodAmount > -1)
                                        rightLandTimer = standardDuration;
                                    else
                                        requestForResource();
                                }
                                //enough resources for factory to start working
                                if (rightLandTimer > 0) {
                                    if(rightLandTimer == 1) rightLandTimer--;
                                    rightLandTimer--;
                                }
                            }
                        });
                    }
                };
                rightLandProduction.scheduleAtFixedRate(task, 0, 1000);
            }
        }




    }

    protected void updateResourceView(){
        System.out.println("update resource");
        ((TextView) thisObject.findViewById(R.id.mbeer_amount)).setText(Integer.toString(PlayerResources.beerAmount));
        ((TextView) thisObject.findViewById(R.id.mfood_amount)).setText(Integer.toString(PlayerResources.foodAmount));
        ((TextView) thisObject.findViewById(R.id.mmetal_amount)).setText(Integer.toString(PlayerResources.metalAmount));
        ((TextView) thisObject.findViewById(R.id.mgold_amount)).setText(Integer.toString(PlayerResources.goldAmount));
        ((TextView) thisObject.findViewById(R.id.mwood_amount)).setText(Integer.toString(PlayerResources.woodAmount));
        ((TextView) thisObject.findViewById(R.id.mdairy_amount)).setText(Integer.toString(PlayerResources.dairyAmount));
    }

    protected static boolean leftInitiated = false;
    protected static boolean rightInitiated = false;

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

    public void leftFactoryClicked(View view) {
        if(leftInitiated)   leftLandProduction.cancel();
        leftInitiated = true;
        whichFactory = false;
        Intent intent = new Intent(this, FactoryListActivity.class);
        startActivity(intent);
    }

    public void rightFactoryClicked(View view) {
        if(rightInitiated) rightLandProduction.cancel();
        rightInitiated = true;
        whichFactory = true;
        Intent intent = new Intent(this, FactoryListActivity.class);
        startActivity(intent);
    }

    public void lockGranted(String lock) {
        System.out.println("Lock Recieved : " +lock);
        int quantity = device.getSharedResources().getValue(lock);
        if(lock.equalsIgnoreCase("WOOD")) {

            if (quantity + PlayerResources.woodAmount >= 0){
                if(PlayerResources.woodAmount > 0) PlayerResources.goldAmount += PlayerResources.woodAmount;
                    //Toast.makeText(getApplicationContext(),"+" +Integer.toString(PlayerResources.woodAmount)+" Coins", Toast.LENGTH_SHORT).show();}
                quantity += PlayerResources.woodAmount;
                PlayerResources.woodAmount = 0;
            }else{
                PlayerResources.woodAmount += quantity;
                quantity = 0;
                PlayerResources.goldAmount -= 0.3*PlayerResources.woodAmount;
                PlayerResources.woodAmount = 0;
            }
        }else if(lock.equalsIgnoreCase("FOOD")) {
            if (quantity + PlayerResources.foodAmount >= 0){
                if(PlayerResources.foodAmount > 0) PlayerResources.goldAmount += PlayerResources.foodAmount;
                quantity += PlayerResources.foodAmount;
                PlayerResources.foodAmount = 0;
            }else{
                PlayerResources.foodAmount += quantity;
                quantity = 0;
                PlayerResources.goldAmount -= 0.3*PlayerResources.foodAmount;
                PlayerResources.foodAmount = 0;
            }
        }else if(lock.equalsIgnoreCase("BEER")) {
            if (quantity + PlayerResources.beerAmount >= 0){
                if(PlayerResources.beerAmount > 0) PlayerResources.goldAmount += PlayerResources.beerAmount;
                quantity += PlayerResources.beerAmount;
                PlayerResources.beerAmount = 0;
            }else{
                PlayerResources.beerAmount += quantity;
                quantity = 0;
                PlayerResources.goldAmount -= 0.3*PlayerResources.beerAmount;
                PlayerResources.beerAmount = 0;
            }
        }else if(lock.equalsIgnoreCase("METAL")) {
            if (quantity + PlayerResources.metalAmount >= 0){
                if(PlayerResources.metalAmount > 0) PlayerResources.goldAmount += 1.4*PlayerResources.metalAmount;
                quantity += PlayerResources.metalAmount;
                PlayerResources.metalAmount = 0;
            }else{
                PlayerResources.metalAmount += quantity;
                quantity = 0;
                PlayerResources.goldAmount -= 0.3*PlayerResources.metalAmount;
                PlayerResources.metalAmount = 0;
            }
        }else if(lock.equalsIgnoreCase("DAIRY")) {
            if (quantity + PlayerResources.dairyAmount >= 0){
                if(PlayerResources.dairyAmount > 0) PlayerResources.goldAmount += PlayerResources.dairyAmount;
                quantity += PlayerResources.dairyAmount;
                PlayerResources.dairyAmount = 0;
            }else{
                PlayerResources.dairyAmount += quantity;
                quantity = 0;
                PlayerResources.goldAmount -= 0.3*PlayerResources.dairyAmount;
                PlayerResources.dairyAmount = 0;
            }
        }

        try{
            device.updateResource(lock, quantity);
            device.releaseResource(lock);
        } catch (UnknownHostException e) {
            Toast.makeText(getApplicationContext(), "It seems that your peer dude crashed!", Toast.LENGTH_LONG).show();
        } catch (SocketTimeoutException e) {
            Toast.makeText(getApplicationContext(), "It seems that your peer dude crashed!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "It seems that your peer dude crashed!", Toast.LENGTH_LONG).show();
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateResourceView();
            }
        });
    }

    private void requestForResource(){

        HashMap<String, Integer> values = device.getSharedResources().getValues();
        if(values.isEmpty()) {
            //initializeNetworkResource();
//            Toast.makeText(getApplicationContext(), "Initializing Network", Toast.LENGTH_LONG).show();
        }else
            printSharedValues(values);

        class RequestForLock extends AsyncTask {

            @Override
            protected Object doInBackground(Object... arg0) {
                try {
                    //System.out.println("inside lock");
                    //System.out.println("inside lock");
                    //System.out.println("inside lock");
                    if (PlayerResources.woodAmount != 0){device.lockResource("WOOD"); System.out.println("request for wood");}
                    if (PlayerResources.metalAmount != 0){device.lockResource("METAL"); System.out.println("request for metal");}
                    if (PlayerResources.foodAmount != 0){device.lockResource("FOOD"); System.out.println("request for food");}
                    if (PlayerResources.dairyAmount != 0){device.lockResource("DAIRY"); System.out.println("request for dairy");}
                    if (PlayerResources.beerAmount != 0){device.lockResource("BEER"); System.out.println("request for beer");}
                }catch (Exception e){
                    e.printStackTrace();
                    //Toast.makeText(getApplicationContext(), "You Dumb, Don't request lock again!"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                return null;
            }

        }
        AsyncTask rfl = new RequestForLock().execute();
    }
    public void resourceUpdate(String resource, int value) {
        System.out.println(resource);

    }

    private void printSharedValues(HashMap<String, Integer> values) {
        for (String key : values.keySet()) {
            String entry = key + ":" + values.get(key);
            HashMap<String, ResourceState> locks = device
                    .getSharedResources().getLocks();
            try {
                entry += ":" + locks.get(key).getState();
            } catch (NullPointerException npe) {
                entry += ":LOCK_NOT_INITIALIZED";
            }
            System.out.println(entry);
        }
    }

    public void initializeNetworkResource(){
        if(!device.getSharedResources().getValues().containsKey("BEER")) {
            device.addNewResource("BEER", 0);
        }
        if(!device.getSharedResources().getValues().containsKey("FOOD")) {
            device.addNewResource("FOOD", 0);
        }
        if(!device.getSharedResources().getValues().containsKey("METAL")) {
            device.addNewResource("METAL", 0);
        }
        if(!device.getSharedResources().getValues().containsKey("WOOD")) {
            device.addNewResource("WOOD", 0);
        }
        if(!device.getSharedResources().getValues().containsKey("DAIRY")) {
            device.addNewResource("DAIRY", 0);
        }
    }
}
