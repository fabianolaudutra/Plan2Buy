package plan2buy.com.myplan2buy_developertest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

import adapter.CommerceItemAdapter;
import adapter.ShoppingCartAdapter;
import pojo.CommerceItem;
import pojo.Produtcs;
import pojo.ShoppingCart;
import services.ProductsServices;
import services.ShoppingCartService;

/**
 * Created by f.dutra on 07/06/2016.
 */
public class CommerceItemActivity extends AppCompatActivity implements CommerceItemAdapter.customButtonListener{


    private ListView listView;
    ImageButton imgBack2;
    EditText edtMycart;
   ShoppingCartService servicesCar = new ShoppingCartService();

    private void bingShoppingCart() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        edtMycart= (EditText)findViewById(R.id.edtMycart);
        BigDecimal total = BigDecimal.ZERO;
        ArrayList<ShoppingCart> res = servicesCar.shoppingcartGet();
        for (ShoppingCart _shoppingcart : res) {
            total =  total.add(_shoppingcart.getAmount());
        }
        if(total!= null){

           // Toast.makeText(CommerceItemActivity.this, "Your Amount is $:" + decimalFormat.format(total),
             //       Toast.LENGTH_SHORT).show();

           edtMycart.setText("    Your Amount is :     " + decimalFormat.format(total));

        }
        else{
            edtMycart.setText("   Your Basket is Empty:  ");
       }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commerce_item);
        listView = (ListView)findViewById(R.id.lstVwCommerce);
        imgBack2= (ImageButton)findViewById(R.id.imgBack2);
        imgBack2.setImageResource(R.drawable.back);

        imgBack2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                finish();
            }
        });

     //   CommerceItemAdapter adapterMycar = new CommerceItemAdapter(this, servicesCar.items("xxx",2));
        CommerceItemAdapter adapterMycar = new CommerceItemAdapter(this, servicesCar.items("xxx",2));
        listView.setAdapter(adapterMycar);
       adapterMycar.setCustomButtonListner(CommerceItemActivity.this);

    }
    /*
    private ArrayList<CommerceItem> BindItens(){
        ArrayList<ShoppingCart> retS =  servicesCar.shoppingcartGet();
        ArrayList<CommerceItem> item = new ArrayList<CommerceItem>();

        for (ShoppingCart ss: retS  ) {
            CommerceItem _item = new CommerceItem();
            _item.setId(ss.getItems());
            _item.
            item.add(_item);
        }

        return item;
    }
*/

    public void onButtonClickListnerRemove(int position, String value, String id) {

        Toast.makeText(CommerceItemActivity.this, "Remove item to Basket" + value,
                Toast.LENGTH_SHORT).show();
      // bingShoppingCart();

    }


}
