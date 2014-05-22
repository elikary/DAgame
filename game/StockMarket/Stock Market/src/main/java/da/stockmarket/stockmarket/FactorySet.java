package da.stockmarket.stockmarket;

import java.util.ArrayList;

public class FactorySet {
    public static ArrayList<Factory> presetFactoryList = new ArrayList<Factory>();

    public static void populate(){
        presetFactoryList.add(new Factory("Resturant", R.drawable.ic_launcher, 1, -2, 3, 4, 2, 1));
        presetFactoryList.add(new Factory("Fisherman", R.drawable.ic_launcher, 4, 4, 4, 4, 400, -4));
        presetFactoryList.add(new Factory("Resturant", R.drawable.ic_launcher, 1, -2, 3, 4, 2, 1));
        presetFactoryList.add(new Factory("Fisherman", R.drawable.ic_launcher, 4, 4, 4, 4, 400, -4));
        presetFactoryList.add(new Factory("Resturant", R.drawable.ic_launcher, 1, -2, 3, 4, 2, 1));
        presetFactoryList.add(new Factory("Fisherman", R.drawable.ic_launcher, 4, 4, 4, 4, 400, -4));
        presetFactoryList.add(new Factory("Resturant", R.drawable.ic_launcher, 1, -2, 3, 4, 2, 1));
        presetFactoryList.add(new Factory("Fisherman", R.drawable.ic_launcher, 4, 4, 4, 4, 400, -4));
        presetFactoryList.add(new Factory("Resturant", R.drawable.ic_launcher, 1, -2, 3, 4, 2, 1));
        presetFactoryList.add(new Factory("Fisherman", R.drawable.ic_launcher, 4, 4, 4, 4, 400, -4));
    }

}
