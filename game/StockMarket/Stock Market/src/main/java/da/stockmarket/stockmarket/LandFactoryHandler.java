package da.stockmarket.stockmarket;

import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by saman on 22/05/2014.
 */
public class LandFactoryHandler {
    private ImageButton leftLand;
    private ImageButton rightLand;
    private Factory leftFactory;
    private Factory rightFactory;

    public LandFactoryHandler(ImageButton leftLand, ImageButton rightLand){
        this.leftLand = leftLand;
        this.rightLand = rightLand;
    }

    public void setLeftLandFactory(Factory factory){
        System.out.println("left Clicked");
        this.leftFactory = factory;
        leftLand.setBackgroundResource(factory.imageResId);
    }

    public void setRightLandFactory(Factory factory){
        System.out.println("right Clicked");
        this.rightFactory = factory;
        rightLand.setBackgroundResource(factory.imageResId);
    }
}
