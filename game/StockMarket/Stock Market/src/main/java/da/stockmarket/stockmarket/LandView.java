package da.stockmarket.stockmarket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class LandView extends Activity {
protected static LandFactoryHandler lfHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_view);
        lfHandler = new LandFactoryHandler((ImageButton)this.findViewById(R.id.leftFactory), (ImageButton)this.findViewById(R.id.rightFactory));
        FactorySet.populate();
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
