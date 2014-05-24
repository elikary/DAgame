package da.stockmarket.stockmarket;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by saman on 22/05/2014.
 */
public class LandFactoryHandler {
    private ImageButton leftLand;
    private ImageButton rightLand;
    protected static Factory leftFactory;
    protected static Factory rightFactory;
    protected static boolean factoryStarted = false;

    public LandFactoryHandler(ImageButton leftLand, ImageButton rightLand){
        this.leftLand = leftLand;
        this.rightLand = rightLand;
    }

    public void setLeftLandFactory(Factory factory){
        factoryStarted = true;
        System.out.println("left Clicked");
        this.leftFactory = factory;
        PlayerResources.goldAmount -= factory.goldAmount;
        leftLand.setBackgroundResource(factory.imageResId);
    }

    public void setRightLandFactory(Factory factory){
        factoryStarted = true;
        System.out.println("right Clicked");
        this.rightFactory = factory;
        PlayerResources.goldAmount -= factory.goldAmount;
        rightLand.setBackgroundResource(factory.imageResId);
    }
}
