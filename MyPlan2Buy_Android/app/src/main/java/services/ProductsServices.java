package services;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import Util.Utils;
import plan2buy.com.myplan2buy_developertest.R;
import pojo.CommerceItem;
import pojo.Produtcs;
import pojo.ShoppingCart;

/**
 * Created by Fabiano on 08/05/2016.
 */
public class ProductsServices {

   private static final String URL_STRING_PRODUCTS = "http://192.168.1.1:8080/developerTest/produtcs";
//mageview.setImageDrawable(getResources().getDrawable(R.drawable.frnd_inactive));




    public  ArrayList<Produtcs> getProdutcsById(String Id){
        ArrayList<Produtcs> listPrd = new ArrayList<Produtcs>();
        String id = "";

        listPrd = new ArrayList<>();
        ArrayList<Produtcs> ret =  this.getAllProdutcs();
        for (Produtcs produ: ret  ) {
            Produtcs _retP= new Produtcs();
            if (produ.getId().equals(Id)){
               // _retP.setName();
               // listPrd.add(new Produtcs(produ.getId(),produ.getName(),produ.getProdutcsPrice(),produ.getImage(),produ.getPrice(),produ.getQtsTotal()));
            }

        }

        return listPrd;
    }

    public ArrayList<Produtcs> getAllProdutcs(){
        ArrayList<Produtcs> productsList = new ArrayList<Produtcs>();
        HttpClient client = new DefaultHttpClient();
        Utils _utils= new Utils();
        HttpResponse response = null;
        InputStream instream = null;
        String ret = null;

        try {

            URL url = new URL(URL_STRING_PRODUCTS);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(1000);
          // conn.setConnectTimeout(30000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.connect();
            conn.getResponseCode();
            instream=conn.getInputStream();
            ret = _utils.convertStream2String(instream);
            JSONObject json = new JSONObject(ret);
            JSONArray items = json.getJSONArray("produtcs");

            for(int i=0;i < items.length() ;i++) {
                Produtcs prds = new Produtcs();
                JSONObject productsObject = items.getJSONObject(i);
                prds.setId(productsObject.getString("id"));
                prds.setImage(productsObject.getString("image"));
                prds.setName(productsObject.getString("name"));
                prds.setPrice(BigDecimal.valueOf(productsObject.getDouble("price")));

                productsList.add(prds);
            }
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            if (instream != null) {
                try {
                    instream.close();
                } catch (Exception exc) {

                }
            }
           // return products;
        }
        return productsList;
    }




}
