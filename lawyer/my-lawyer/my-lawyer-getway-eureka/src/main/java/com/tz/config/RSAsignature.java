package com.tz.config;
import java.security.*;  
import java.security.interfaces.RSAPrivateKey;  
import java.security.interfaces.RSAPublicKey;  
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;  
import java.util.Map;

import sun.misc.BASE64Decoder;  
  
/**
 * 签名验签类 
 * @author mnenglin
 * 2018年1月3日11:09:21
 */
public class RSAsignature {  
    private static final String PUBLIC_KEY = "publickey1514887142685";  
    private static final String PRIVATE_KEY = "privatekey1514887142682"; 
    
    
/*	public static final String parameter = "name=mengxx&age=18&email=784645@qq.com&timestamp=1514880713284";*/
    /* 
     *RSA生成密钥对 
     */  
    public static Map<String,Object> GenerateKeyPair(String keyType, int keySize) throws Exception {  
        KeyPairGenerator pairGenerator = KeyPairGenerator.getInstance(keyType);  
        pairGenerator.initialize(keySize);  
        //生成秘钥对  
        KeyPair keyPair = pairGenerator.generateKeyPair();  
        //获得公钥和私钥  
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
        Map<String,Object> map = new HashMap<String,Object>();  
        
       /* System.out.println(publicKey+"----"+privateKey);*/
        System.out.println(fromBytesToHexString(publicKey.getEncoded())); 
        System.out.println(fromBytesToHexString(privateKey.getEncoded()));  
        
        map.put(PUBLIC_KEY,publicKey);  
        map.put(PRIVATE_KEY,privateKey);  
        return  map;  
    }  
    /* 
    *从Map中获得密钥对 
    */  
    public static RSAPublicKey GetPublicKey(Map<String,Object> map){  
        RSAPublicKey publicKey = (RSAPublicKey) map.get(PUBLIC_KEY);  
        return publicKey;  
    }  
    public static RSAPrivateKey GetPrivateKey(Map<String,Object> map){  
        RSAPrivateKey privateKey = (RSAPrivateKey) map.get(PRIVATE_KEY);  
        return privateKey;  
    }  
    /* 
     *私钥对原始数据进行签名 
     */  
    public static byte[] signature(RSAPrivateKey privateKey ,byte[] data,String keyType) throws Exception {  
//        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());  
//        KeyFactory factory = KeyFactory.getInstance(keyType);  
//        PrivateKey privatekey = (PrivateKey) factory.generatePrivate(keySpec);  
  
        Signature signature = Signature.getInstance("MD5withRSA");  
        signature.initSign(privateKey);  
        signature.update(data);  
        return signature.sign();  
    }  
    /* 
     * 公钥、原始数据、原始签名数据进行验证 
     */  
    public static boolean verify(RSAPublicKey publicKey,byte[] data,byte[] sign) throws Exception {  
        Signature signature = Signature.getInstance("MD5withRSA");  
        signature.initVerify(publicKey);  
        signature.update(data);  
        return signature.verify(sign);  
    }  
    /**
     * key 转 公钥
     * @param key
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }
   /**
    * key 转私钥
    * @param key
    * @return
    * @throws Exception
    */
    public static PublicKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey privateKey = keyFactory.generatePublic(keySpec);
        return privateKey;
    } 
    
    public static void main(String agrs[]) throws Exception {  
    	PublicKey publicKey = getPublicKey("");
		System.out.println(publicKey);
/*    	boolean verify= false;
    	try {
    		
    		
    			Map<String,Object> map = GenerateKeyPair("RSA",1024);
	        RSAPublicKey publicKey = RSAsignature.GetPublicKey(map);  
	        Map<String,Object> map2 = GenerateKeyPair("RSA",1024);
	        RSAPrivateKey privateKey = RSAsignature.GetPrivateKey(map2);  
	        
	        byte[] sign = signature(privateKey,"abcd21312".getBytes(),"RSA");
	        
	        System.out.println("-----publicKey:"+publicKey+"----privateKey:"+privateKey);
	        verify = verify(publicKey,"abcd21312".getBytes(),sign);  
	        	
	        System.out.println(fromBytesToHexString(publicKey.getEncoded()));  
	        System.out.println(fromBytesToHexString(privateKey.getEncoded()));  
	        System.out.println(fromBytesToHexString(sign));   
		} catch (Exception e) {
			// TODO: handle exception
			verify = false;
 		}
    	System.out.println(verify);*/
       
    }  
    public static String fromBytesToHexString(byte[] data){  
        StringBuilder str = new StringBuilder();  
        for (int i = 0; i < data.length; i++) {  
            String s = Integer.toHexString(0xFF & data[i]);  
            if (s.length() == 1){  
                str.append(0).append(s);  
            }else {  
                str.append(s);  
            }  
        }  
        return str.toString();  
    }  
}  