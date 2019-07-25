package plan2buy.com.myplan2buy_developertest;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

import adapter.ProdutcsAdapter;
import adapter.ShoppingCartAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;
import pojo.CommerceItem;
import pojo.Produtcs;
import pojo.ShoppingCart;
import services.ProductsServices;
import services.ShoppingCartService;

public class MainActivity extends AppCompatActivity implements ProdutcsAdapter.customButtonListener  {

    private ListView listView;
    ProductsServices services = new ProductsServices();
    ImageButton imgCart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Fresco.initialize(this);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        imgCart= (ImageButton)findViewById(R.id.imgCart);
        imgCart.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,CommerceItemActivity.class);
                startActivity(it);
            }
        });


        ProdutcsAdapter adapter = new ProdutcsAdapter(this,services.getAllProdutcs());
        listView = (ListView)findViewById(R.id.lstVeProducts);
        adapter.setCustomButtonListner(MainActivity.this);
        listView.setAdapter(adapter);
        //registerForContextMenu(listView);


     }



    public void onButtonClickListnerAdd(int position, String value, int inputQt) {

        Toast.makeText(MainActivity.this, "ADD item to Basket " + value,
                Toast.LENGTH_SHORT).show();

    }

//http://www.devexchanges.info/2016/02/android-loading-and-caching-images-with.html
   /*
    private void findViewsQtd() {
        textQts = (EditText) findViewById(R.id.editTextQtd);
         retValue = Integer.parseInt( textQts.getText().toString());
        //   sdvImage = (SimpleDraweeView) findViewById(R.id.sdv_image);
    }
    */

/*
    public void onButtonClickListnerRemove(int position, String value, String id) {

        Toast.makeText(MainActivity.this, "Remove item to Basket" + value,
                Toast.LENGTH_SHORT).show();
        bingShoppingCart();
    }
*/
}

