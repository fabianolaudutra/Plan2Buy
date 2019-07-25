package adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.InjectView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import Util.LoadImage;
import Util.Utils;
import plan2buy.com.myplan2buy_developertest.MainActivity;
import plan2buy.com.myplan2buy_developertest.R;
import pojo.CommerceItem;
import pojo.Produtcs;
import services.ShoppingCartService;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import android.net.Uri;
/**
 * Created by Fabiano.
 */
public class ProdutcsAdapter extends BaseAdapter {
    customButtonListener customListner;
    private Context context;
    public List<Produtcs> listaProdutos;
    ArrayList<CommerceItem> commerceItem;
    Produtcs prdsBean ;
    ShoppingCartService shoppingCartservice = new ShoppingCartService();
    ViewHolder viewHolder = new ViewHolder();
    private Produtcs _produtcs;
    private SimpleDraweeView sdvImage;
    private LayoutInflater inflater;
    @InjectView(R.id.editTextQtd)
    EditText editTextQtd;

    public interface customButtonListener {
        public void onButtonClickListnerAdd(int position,String value, int qtd);
    }

    public void setCustomButtonListner(customButtonListener listener) {
        this.customListner = listener;
    }

    @Override
    public int getCount() {
        return listaProdutos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaProdutos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ProdutcsAdapter(Context context, List<Produtcs> prds) {
        this.context = context;
        this.listaProdutos = prds;

    }
    public void setListaProdutos(List<Produtcs> listaprodutos){
        this.listaProdutos = listaprodutos;
    }





    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        Produtcs _prds = listaProdutos.get(position);

        final Uri uriFromPath = Uri.parse(_prds.getImage());

        if (convertView == null) {
             inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.holder_listview, null);
            viewHolder.twDesc = (TextView)convertView.findViewById(R.id.twDesc);
            viewHolder.buttonAdd = (ImageButton)convertView.findViewById(R.id.btnAdd);
            viewHolder.editTextQtd = (EditText)convertView.findViewById(R.id.editTextQtd);
            viewHolder.imgView = (SimpleDraweeView)convertView.findViewById(R.id.imageView);
              if(uriFromPath != null){
                viewHolder.imgView.setImageURI(uriFromPath);
            }

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.twDesc.setText(_prds.getName()+"                        $: " + decimalFormat.format(_prds.getPrice()));
         viewHolder.buttonAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (customListner != null) {

                    final String input=  viewHolder.editTextQtd.getText().toString();

                    customListner.onButtonClickListnerAdd(position,listaProdutos.get(position).getName(),Integer.parseInt(input));
                    Log.v("Retorno position ADD " ,String.valueOf(position));
                    Log.v("Retorno Nome ADD" ,listaProdutos.get(position).getName() );

                   commerceItem =  shoppingCartservice.items(listaProdutos.get(position).getId(),Integer.parseInt(input));
                }

            }
        });

      return convertView;
    }



    public class ViewHolder {
        TextView twDesc;
        SimpleDraweeView imgView;
        EditText editTextQtd;
        ImageButton buttonAdd;
    }


}


