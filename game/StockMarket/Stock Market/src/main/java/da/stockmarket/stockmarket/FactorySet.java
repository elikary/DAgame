package da.stockmarket.stockmarket;

import java.util.ArrayList;

public class FactorySet {
    public static ArrayList<Factory> presetFactoryList = new ArrayList<Factory>();

    public static void populate(){
        presetFactoryList.add(new Factory("Wood Cutter", R.drawable.woodcutter, 30, -1, 2, -2, 5, 1));
        presetFactoryList.add(new Factory("Fisherman", R.drawable.fisherman, 30, 7, -1, -2, -1, -2));
        presetFactoryList.add(new Factory("Dairy Factory", R.drawable.dairy_f, 50, -5, -1, -5, -5, 20));
        presetFactoryList.add(new Factory("Farm", R.drawable.farm_f, 60, 25, 10, -5, -20, 10));
        presetFactoryList.add(new Factory("Cafe", R.drawable.cafe, 80, 15, 10, -5, -5, -7));
        presetFactoryList.add(new Factory("Log Factory", R.drawable.woodfactory, 100, -10, -10, -15, 25, -15));
        presetFactoryList.add(new Factory("Beer Factory", R.drawable.beerfactory, 100, -20, 25, -10, -10, -15));
        presetFactoryList.add(new Factory("Carnegie Steel Factory", R.drawable.steel, 150, -30, -25, 30, -10, -10));


    }

}
