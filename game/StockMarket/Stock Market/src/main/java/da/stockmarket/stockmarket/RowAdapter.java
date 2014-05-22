package da.stockmarket.stockmarket;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RowAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<Factory> values;

    public RowAdapter(Context context, ArrayList values) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
        this.values = values;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);

        Factory tmpFactory = values.get(position);
        ((TextView) rowView.findViewById(R.id.item_title)).setText(tmpFactory.name);
        ((ImageView) rowView.findViewById(R.id.icon)).setImageResource(tmpFactory.imageResId);

        TextView beertext = (TextView) rowView.findViewById(R.id.beer_amount);
        beertext.setText(Integer.toString(tmpFactory.beerAmount));


        ((TextView) rowView.findViewById(R.id.food_amount)).setText(Integer.toString(tmpFactory.foodAmount));


        ((TextView) rowView.findViewById(R.id.dairy_amount)).setText(Integer.toString(tmpFactory.dairy));


        ((TextView) rowView.findViewById(R.id.metal_amount)).setText(Integer.toString(tmpFactory.metalAmount));


        ((TextView) rowView.findViewById(R.id.wood_amount)).setText(Integer.toString(tmpFactory.wood));

        ((TextView) rowView.findViewById(R.id.gold_amount)).setText(Integer.toString(tmpFactory.goldAmount));

        return rowView;
    }
}