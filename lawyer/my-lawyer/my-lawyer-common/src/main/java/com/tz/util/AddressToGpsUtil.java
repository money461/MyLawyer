package com.tz.util;

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.net.URL;  
import java.net.URLEncoder;  
import java.util.HashMap;  
import java.util.Map;  
  
  
import org.apache.commons.lang.StringUtils;  
 /**
  * 地址转经纬度 
  * @author Administrator
  *
  */
  
public class AddressToGpsUtil {  
  
  
    public static final String KEY_1 = "7d9fbeb43e975cd1e9477a7e5d5e192a";  
  
  
  
  
    /** 
     * 返回输入地址的经纬度坐标 
     * key lng(经度),lat(纬度) 
     */  
    public static Map<String,Double> getGeocoderLatitude(String address){  
        BufferedReader in = null;  
        try {  
            //将地址转换成utf-8的16进制  
            address = URLEncoder.encode(address, "UTF-8");  
            URL tirc = new URL("http://api.map.baidu.com/geocoder?address="+ address +"&output=json&key="+ KEY_1);  
  
  
            in = new BufferedReader(new InputStreamReader(tirc.openStream(),"UTF-8"));  
            String res;  
            StringBuilder sb = new StringBuilder("");  
            while((res = in.readLine())!=null){  
                sb.append(res.trim());  
            }  
            String str = sb.toString();  
            Map<String,Double> map = null;  
            if(StringUtils.isNotEmpty(str)){  
                int lngStart = str.indexOf("lng\":");  
                int lngEnd = str.indexOf(",\"lat");  
                int latEnd = str.indexOf("},\"precise");  
                if(lngStart > 0 && lngEnd > 0 && latEnd > 0){  
                    Double lon = Double.parseDouble(str.substring(lngStart+5, lngEnd));  
                    Double lat = Double.parseDouble(str.substring(lngEnd+7, latEnd));  
                    map = new HashMap<String,Double>();  
                    map.put("lon", lon);  
                    map.put("lat", lat);  
                    return map;  
                }  
            }  
        }catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return null;  
    }  
      
    public static void main(String args[]){  
        try {  
            Map<String, Double> json = AddressToGpsUtil.getGeocoderLatitude("湖北省黄冈市麻城市金龙大道");  
            System.out.println("lon : " + json.get("lon"));  
            System.out.println("lat : " + json.get("lat"));  
        }catch (Exception e ){  
           e.printStackTrace();  
        }  
    }  
  
  
}