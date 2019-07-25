package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import plan2buy.com.myplan2buy_developertest.R;
import pojo.ShoppingCart;

/**
 * Created by Fabiano.
 */
public class ShoppingCartAdapter  extends ArrayAdapter<ShoppingCart> {

    private Context context;
    ArrayList<ShoppingCart> _shoppingCartBean = new ArrayList<ShoppingCart>();
    private List<ShoppingCart> shoppingCarList;

    public ShoppingCartAdapter(Context context, ArrayList<ShoppingCart> shoppingcart) {
        super(context, R.layout.activity_main, shoppingcart);
        this.context = context;
        this.shoppingCarList = shoppingcart;
    }



    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View rowView= inflater.inflate(R.layout.client_list, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.Itemname);

        txtTitle.setText(shoppingCarList.get(position).getItems());
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        // txtTitle.setText(_shoppingCartBean.get(position).getItems());

        //imageView.setImageResource(imageId[position]);
        return rowView;
    }


}
