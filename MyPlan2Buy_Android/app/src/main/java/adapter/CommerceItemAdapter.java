package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import plan2buy.com.myplan2buy_developertest.R;
import pojo.CommerceItem;
import pojo.Produtcs;
import pojo.ShoppingCart;
import services.ProductsServices;
import services.ShoppingCartService;

/**
 * Created by Fabiano
 */
public class CommerceItemAdapter  extends BaseAdapter {
    customButtonListener customListner;
    private Context context;
    ViewHolder viewHolder = new ViewHolder();
    List<CommerceItem> commerceItemList ;
    List<Produtcs> listByDtlPrd;
    ShoppingCartService shoppingCartservice = new ShoppingCartService();
    ProdutcsAdapter prdAdpter ;
    ProductsServices productServices = new ProductsServices();
    private ListView listView;
    ShoppingCartService servicesCar = new ShoppingCartService();

    public CommerceItemAdapter(Context context, ArrayList<CommerceItem> commerceitem) {
        //super(context, R.layout.activity_main, commerceitem);
        this.context = context;
        this.commerceItemList = commerceitem;
    }

    @Override
    public int getCount() {
        return commerceItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return commerceItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public interface customButtonListener {
        public void onButtonClickListnerRemove(int position,String value,String id);

    }

    public void seCommerceItemList(ArrayList<CommerceItem> commerceitem){
        this.commerceItemList = commerceitem;
    }

    public void setCustomButtonListner(customButtonListener listener) {
        this.customListner = listener;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        if (convertView == null)
             {

        listByDtlPrd = productServices.getProdutcsById(commerceItemList.get(position).getProduct_id());
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.commerce_item_dts, null);

            viewHolder.twDesc = (TextView)convertView.findViewById(R.id.txtShopping);
            viewHolder.btnRemove = (ImageButton)convertView.findViewById(R.id.btnRemove);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Produtcs _ret= prdAdpter.listaProdutos.get(Integer.parseInt(commerceItemList.get(position).getProduct_id()));

        viewHolder.twDesc.setText(_ret.getName() +"Total :"+ _ret.getQtsTotal()+"Price : "+ decimalFormat.format(_ret.getPrice()));
        /*
      if(listByDtlPrd.size() >0){
           DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
          String namme = "";
       //   Log.v("Retorno  _ret " ,listByDtlPrd.get(position).getName());
          if(listByDtlPrd.get(position).getName()!= null){
              namme = listByDtlPrd.get(position).getName();
              viewHolder.twDesc.setText(namme);
          }else
          {
              viewHolder.twDesc.setText(commerceItemList.get(position).getProduct_id());
          }
          viewHolder.twDesc.setText(commerceItemList.get(position).getProduct_id());
          //viewHolder.twDesc.setText(listByDtlPrd.get(position).getName());
       }*/



        viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customListner != null) {
                   // final String input=  viewHolder.editTextQtd.getText().toString();
                    Log.v("Retorno  Remove " ,String.valueOf(position));
                    Log.v("Retorno Nome ADD" ,commerceItemList.get(position).getId() );
                     customListner.onButtonClickListnerRemove(position,commerceItemList.get(position).getProduct_id(),commerceItemList.get(position).getId());
                    shoppingCartservice.shoppingcartItemsIdDelete(commerceItemList.get(position).getId());
                 //   bingShoppingCart();
                }

            }
        });

        return convertView;
    }

    private void atualizaLista() {
       // ((CommerceItemAdapter)listView.getAdapter()).seCommerceItemList(contatoDao.listar());
       // ((CommerceItemAdapter)listView.getAdapter()).notifyDataSetChanged();
    }

    public class ViewHolder {
        TextView twDesc;
        ImageButton btnRemove;
        ImageView imgView;
        EditText editText;
        ImageButton buttonAdd;
    }
}
