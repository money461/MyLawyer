package com.tz.config;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RSAtest {
	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	private static final int KEY_SIZE = 1024;
	private static final String PUBLIC_KEY = "RSAPublicKey";
	private static final String PRIVATE_KEY = "RSAPrivateKey";
	public static String str_pubK = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCX8N5qU7IrbKKyvoxZ/WiUgvh9DsWXaUBYFpkFeaLlwe7QXkqWGkt1OGjncHbYLBANGiQHYIGBG2EGd390MY/pwoKFoVtkM3J/ptNTYC5ZgDqMghsrgSu/IQXtLDtcTtVceod6GgGdgzQASsHp+WVeminEfWIXO1UoVlwzLNpPmQIDAQAB";
	public static String str_priK = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJfw3mpTsitsorK+jFn9aJSC+H0OxZdpQFgWmQV5ouXB7tBeSpYaS3U4aOdwdtgsEA0aJAdggYEbYQZ3f3Qxj+nCgoWhW2Qzcn+m01NgLlmAOoyCGyuBK78hBe0sO1xO1Vx6h3oaAZ2DNABKwen5ZV6aKcR9Yhc7VShWXDMs2k+ZAgMBAAECgYBdelNcFyIGlU+DqLewpbcxp2I5jl0CSj7L8xsxwyP1Nts2VsyMjKDf/VSNDyQO2w0FApAn+bezNqT61YoUcmmW9GuHZujtFSum9q6uvuyQZo2OsdNguFnFcan9WN1IrzvylRqM/rFpCh/jTUo+/mnm2b9YRNlEuReI8OimHNKAAQJBANIQCDFGyvDRf7+uZSWIIl3SZGww6WMo9IFylTzuO4c0C9gw+KVK6Cunywg1fgn10rcslw0w5XJIDw8EMUsYomECQQC5Kv/CQzOvX5oY/N7nlKUiN3aD0cJy+Q2ZLd0y/hVSknjBIxbVB1HQlUQCDmAYn6pW9lu8RXN/UpJRMAn5Xyg5AkEAgJkR+uDE8qK+m2EvSMfLijZ4Bgf3iR4UeX7lQBT0WnQRYDuyrLZr5fXmBNOCoqRcaIkivA+YF9tnZKEDcF7XYQJAWCE3CWaiut1tomb4UJBW6Cotph+3xLD03+sMVLDJxTshbgsdWtbZAcWJUNE2u4GCuVEX6EvF5NuP+EQ8+wtnQQJAKgSvuvqR2edlfFiKdLOHaSw37ViZnwl0eSnmjZr1oQjF4Eda+AHC4UribjUhh8TEc5YuSwmcO14qT9jtknPhig==";

	/**
	 * 使用getPublicKey得到公钥,返回类型为PublicKey
	 * 
	 * @param base64
	 *            String to PublicKey
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
	 * 转换私钥
	 * 
	 * @param base64
	 *            String to PrivateKey
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = (new BASE64Decoder()).decodeBuffer(key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	// ***************************签名和验证*******************************
	public static byte[] sign(byte[] data) throws Exception {
		PrivateKey priK = getPrivateKey(str_priK);
		Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
		sig.initSign(priK);
		sig.update(data);
		return sig.sign();
	}

	public static boolean verify(byte[] data, byte[] sign) throws Exception {
		PublicKey pubK = getPublicKey(str_pubK);
		Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
		sig.initVerify(pubK);
		sig.update(data);
		return sig.verify(sign);
	}

	// ************************加密解密**************************
	public static byte[] encrypt(byte[] bt_plaintext) throws Exception {
		PublicKey publicKey = getPublicKey(str_pubK);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] bt_encrypted = cipher.doFinal(bt_plaintext);
		return bt_encrypted;
	}

	public static byte[] decrypt(byte[] bt_encrypted) throws Exception {
		PrivateKey privateKey = getPrivateKey(str_priK);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] bt_original = cipher.doFinal(bt_encrypted);
		return bt_original;
	}

	// ********************main函数：加密解密和签名验证*********************
	public static void main(String[] args) throws Exception {

		/*
		 * String str_plaintext = "这是一段用来测试密钥转换的明文"; System.err.println("明文：" +
		 * str_plaintext); byte[] bt_cipher = encrypt(str_plaintext.getBytes());
		 * System.out.println("加密后：" + Base64.encodeBase64String(bt_cipher));
		 * 
		 * byte[] bt_original = decrypt(bt_cipher); String str_original = new
		 * String(bt_original); System.out.println("解密结果:" + str_original);
		 */

		String str = "name=mengxx&age=18&email=784645@qq.com&timestamp=151488071328&sign=f/nd+SAeUqsbW5UKNmZTKLgSsFshiJz+F2Phho5ViDeN4EdTN3/VGug4kbRc6svjA0QUjLtWGTQ6nd/0yAMcWKD4yBSvFH4cDIY58nOI+gqUePXrRCdZzukRGrnIPgAB4CR8qVlxRNtnNjRShYvGk4ZqPrQ7AWPA2ncv0ZjorTk=";

		String str2 = "type=forget&userPhone=1986234814334&key=MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCX8N5qU7IrbKKyvoxZ/WiUgvh9DsWXaUBYFpkFeaLlwe7QXkqWGkt1OGjncHbYLBANGiQHYIGBG2EGd390MY/pwoKFoVtkM3J/ptNTYC5ZgDqMghsrgSu/IQXtLDtcTtVceod6GgGdgzQASsHp+WVeminEfWIXO1UoVlwzLNpPmQIDAQAB";
		
		//String str3="managerAccount=admin&managerPassword=123&key=MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCX8N5qU7IrbKKyvoxZ/WiUgvh9DsWXaUBYFpkFeaLlwe7QXkqWGkt1OGjncHbYLBANGiQHYIGBG2EGd390MY/pwoKFoVtkM3J/ptNTYC5ZgDqMghsrgSu/IQXtLDtcTtVceod6GgGdgzQASsHp+WVeminEfWIXO1UoVlwzLNpPmQIDAQAB";
		String str3="userId=1&key=MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCX8N5qU7IrbKKyvoxZ/WiUgvh9DsWXaUBYFpkFeaLlwe7QXkqWGkt1OGjncHbYLBANGiQHYIGBG2EGd390MY/pwoKFoVtkM3J/ptNTYC5ZgDqMghsrgSu/IQXtLDtcTtVceod6GgGdgzQASsHp+WVeminEfWIXO1UoVlwzLNpPmQIDAQAB";
		System.err.println("\n原文:" + str3);
		byte[] signature = sign(str3.getBytes("UTF-8"));
		String strSignature = Base64.encodeBase64String(signature);
		System.out.println("产生签名：" + strSignature);
		byte[] signature2 = Base64.decodeBase64(strSignature);

		
		boolean status = verify(str3.getBytes("UTF-8"), signature2);

		System.out.println("验证情况：" + status);

		/*
		 * System.out.println(str.substring(str.indexOf("&sign=sssssds") + 6,
		 * str.length())); System.out.println(str.indexOf("&sign="));
		 */
		/*
		 * Map<String, String> data = new HashMap<>(); String name = "张三" ; String age =
		 * "18" ; String email = "784645@qq.com" ; String sign = "sadfsafsdfsdfsd" ;
		 * data.put("name", name); data.put("age", age); data.put("email", email);
		 * data.put("sign", sign); System.out.println(formatUrlMap(data));
		 */

	}
	public static Map<String, String> getParameterStringMap(Map<String, String[]> properties) {
		Map<String, String> returnMap = new HashMap<String, String>();
		String name = "";
		String value = "";
		for (Map.Entry<String, String[]> entry : properties.entrySet()) {
			name = entry.getKey();
			String[] values = entry.getValue();
			if (null == values) {
				value = "";
			} else if (values.length > 1) {
				for (int i = 0; i < values.length; i++) { // 用于请求参数中有多个相同名称
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = values[0];// 用于请求参数中请求参数名唯一
			}
			returnMap.put(name, value);

		}
		return returnMap;
	}
	//ask码 处理
	public static String formatUrlMap(Map<String, String> data) throws Exception {
		Set<String> keySet = data.keySet();
		String[] keyArray = keySet.toArray(new String[keySet.size()]);
		Arrays.sort(keyArray);
		StringBuilder sb = new StringBuilder();
		String signVal = "";
		for (String k : keyArray) {
			if (k.equals("sign")) {
				signVal = data.get(k).trim();
				continue;
			}
			if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
				sb.append(k).append("=").append(data.get(k).trim()).append("&");
		}
		sb.append("key=").append(str_pubK);
		if (signVal.trim().length() > 0) {
			sb.append("&sign=").append(signVal);
		}
		return sb.toString();
	}

}