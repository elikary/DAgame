package da.stockmarket.stockmarket;

public class Factory {
    public String name;
    public int imageResId; //R.drawable.windmill
    public int goldAmount;
    public int beerAmount;
    public int foodAmount;
    public int metalAmount;
    public int wood;
    public int dairy;

    public Factory(String name, int imageResId, int goldAmount, int beerAmount, int foodAmount, int metalAmount, int wood, int dairy) {
        this.goldAmount = goldAmount;
        this.beerAmount = beerAmount;
        this.foodAmount = foodAmount;
        this.metalAmount = metalAmount;
        this.wood = wood;
        this.dairy = dairy;
        this.name = name;
        this.imageResId = imageResId;
    }
}
