package services;

import android.util.Log;

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
import pojo.CommerceItem;
import pojo.ShoppingCart;

/**
 * Created by Fabiano on 23/05/2016.
 */
public class ShoppingCartService {

    private static final String URL_STRING_SHOPPING = "http://192.168.0.26:8080/developerTest/shoppingcart";
    private static final String URL_STRING_SHOPPING_ITENS = "http://192.168.0.26:8080/developerTest/shoppingcart/items";

    public ArrayList<ShoppingCart> shoppingcartGet(){
        ArrayList<ShoppingCart> shoppingcart = new ArrayList<ShoppingCart>();

        HttpClient client = new DefaultHttpClient();
        Utils _utils= new Utils();
        HttpResponse response = null;
        InputStream instream = null;
        String ret = null;

        try {

            URL url = new URL(URL_STRING_SHOPPING);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(1000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.connect();
            conn.getResponseCode();
            instream=conn.getInputStream();
            ret = _utils.convertStream2String(instream);

            JSONObject json = new JSONObject(ret);
            JSONArray items = json.getJSONArray("shoppingCart");

            for(int i=0;i < items.length() ;i++) {
                ShoppingCart _shoppingCart = new ShoppingCart();
                JSONObject _shoppingCartObject = items.getJSONObject(i);
                _shoppingCart.setItems(_shoppingCartObject.getString("items"));
                _shoppingCart.setAmount(BigDecimal.valueOf(_shoppingCartObject.getDouble("amount")));

                shoppingcart.add(_shoppingCart);
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
                    exc.printStackTrace();
                }
            }
            return shoppingcart;
        }

    }

    public void  shoppingcartItemsIdDelete (String id){
        Log.v("Retorno Delete " , id );

        HttpClient client = new DefaultHttpClient();
        Utils _utils= new Utils();
        HttpResponse response = null;
        InputStream instream = null;
        String ret = null;

        try {
            URL url = new URL(URL_STRING_SHOPPING);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(1000);
            conn.setRequestMethod("DELETE");
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.connect();
            conn.getResponseCode();
            instream=conn.getInputStream();



        }catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();

        }finally {
            if (instream != null) {
                try {
                    instream.close();
                } catch (Exception exc) {

                }
            }
        }

    }

    public ArrayList<CommerceItem> items(String idprodutcs,int qtd ){
        ArrayList<CommerceItem> commerceitemList = new ArrayList<CommerceItem>();
        Log.v("Retorno idprodutcs " , idprodutcs );

        HttpClient client = new DefaultHttpClient();
        Utils _utils= new Utils();
        HttpResponse response = null;
        InputStream instream = null;
        String ret = null;

        try {

            URL url = new URL(URL_STRING_SHOPPING_ITENS);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(1000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.connect();
            conn.getResponseCode();
            instream=conn.getInputStream();
            ret = _utils.convertStream2String(instream);
            JSONObject json = new JSONObject(ret);
            JSONArray items = json.getJSONArray("commerceItem");


            for(int i=0;i < items.length() ;i++) {
                CommerceItem _commerceitem = new CommerceItem();
                JSONObject commerceitemObject = items.getJSONObject(i);
                _commerceitem.setId(commerceitemObject.getString("id"));
                _commerceitem.setProduct_id(commerceitemObject.getString("product_id"));
                _commerceitem.setQuantity(commerceitemObject.getInt("quantity"));
                _commerceitem.setAmount(BigDecimal.valueOf(commerceitemObject.getDouble("amount")));

                commerceitemList.add(_commerceitem);
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
           // return commerceitem;
        }

        return commerceitemList;


    }


}
