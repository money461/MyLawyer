package com.tz.sdk.weixin;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.security.MessageDigest;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tz.sdk.weixin.WXPayConstants.SignType;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WXPayUtil {

    /**
     * XML格式字符串转换为Map
     *
     * @param strXML XML字符串
     * @return XML数据转换后的Map
     * @throws Exception
     */
    public static Map<String, String> xmlToMap(String strXML) throws Exception {
        try {
            Map<String, String> data = new HashMap<String, String>();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
            org.w3c.dom.Document doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            try {
                stream.close();
            } catch (Exception ex) {
                // do nothing
            }
            return data;
        } catch (Exception ex) {
            WXPayUtil.getLogger().warn("Invalid XML, can not convert to map. Error message: {}. XML content: {}", ex.getMessage(), strXML);
            throw ex;
        }

    }

    /**
     * 将Map转换为XML格式的字符串
     *
     * @param data Map类型数据
     * @return XML格式的字符串
     * @throws Exception
     */
    public static String mapToXml(Map<String, String> data) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
        org.w3c.dom.Document document = documentBuilder.newDocument();
        org.w3c.dom.Element root = document.createElement("xml");
        document.appendChild(root);
        for (String key: data.keySet()) {
            String value = data.get(key);
            if (value == null) {
                value = "";
            }
            value = value.trim();
            org.w3c.dom.Element filed = document.createElement(key);
            filed.appendChild(document.createTextNode(value));
            root.appendChild(filed);
        }
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(document);
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        String output = writer.getBuffer().toString(); //.replaceAll("\n|\r", "");
        try {
            writer.close();
        }
        catch (Exception ex) {
        }
        return output;
    }


    /**
     * 生成带有 sign 的 XML 格式字符串
     *
     * @param data Map类型数据
     * @param key API密钥
     * @return 含有sign字段的XML
     */
    public static String generateSignedXml(final Map<String, String> data, String key) throws Exception {
        return generateSignedXml(data, key, SignType.MD5);
    }

    /**
     * 生成带有 sign 的 XML 格式字符串
     *
     * @param data Map类型数据
     * @param key API密钥
     * @param signType 签名类型
     * @return 含有sign字段的XML
     */
    public static String generateSignedXml(final Map<String, String> data, String key, SignType signType) throws Exception {
        String sign = generateSignature(data, key, signType);
        data.put(WXPayConstants.FIELD_SIGN, sign);
        return mapToXml(data);
    }


    /**
     * 判断签名是否正确
     *
     * @param xmlStr XML格式数据
     * @param key API密钥
     * @return 签名是否正确
     * @throws Exception
     */
    public static boolean isSignatureValid(String xmlStr, String key) throws Exception {
        Map<String, String> data = xmlToMap(xmlStr);
        if (!data.containsKey(WXPayConstants.FIELD_SIGN) ) {
            return false;
        }
        String sign = data.get(WXPayConstants.FIELD_SIGN);
        return generateSignature(data, key).equals(sign);
    }

    /**
     * 判断签名是否正确，必须包含sign字段，否则返回false。使用MD5签名。
     *
     * @param data Map类型数据
     * @param key API密钥
     * @return 签名是否正确
     * @throws Exception
     */
    public static boolean isSignatureValid(Map<String, String> data, String key) throws Exception {
        return isSignatureValid(data, key, SignType.MD5);
    }

    /**
     * 判断签名是否正确，必须包含sign字段，否则返回false。
     *
     * @param data Map类型数据
     * @param key API密钥
     * @param signType 签名方式
     * @return 签名是否正确
     * @throws Exception
     */
    public static boolean isSignatureValid(Map<String, String> data, String key, SignType signType) throws Exception {
        if (!data.containsKey(WXPayConstants.FIELD_SIGN) ) {
            return false;
        }
        String sign = data.get(WXPayConstants.FIELD_SIGN);
        return generateSignature(data, key, signType).equals(sign);
    }

    /**
     * 生成签名
     *
     * @param data 待签名数据
     * @param key API密钥
     * @return 签名
     */
    public static String generateSignature(final Map<String, String> data, String key) throws Exception {
        return generateSignature(data, key, SignType.MD5);
    }

    /**
     * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
     *
     * @param data 待签名数据
     * @param key API密钥
     * @param signType 签名方式
     * @return 签名
     */
    public static String generateSignature(final Map<String, String> data, String key, SignType signType) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals(WXPayConstants.FIELD_SIGN)) {
                continue;
            }
            if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
        }
        sb.append("key=").append(key);
        if (SignType.MD5.equals(signType)) {
            return MD5(sb.toString()).toUpperCase();
        }
        else if (SignType.HMACSHA256.equals(signType)) {
            return HMACSHA256(sb.toString(), key);
        }
        else {
            throw new Exception(String.format("Invalid sign_type: %s", signType));
        }
    }


    /**
     * 获取随机字符串 Nonce Str
     *
     * @return String 随机字符串
     */
    public static String generateNonceStr() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }


    /**
     * 生成 MD5
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    public static String MD5(String data) throws Exception {
        java.security.MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 生成 HMACSHA256
     * @param data 待处理数据
     * @param key 密钥
     * @return 加密结果
     * @throws Exception
     */
    public static String HMACSHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 日志
     * @return
     */
    public static Logger getLogger() {
        Logger logger = LoggerFactory.getLogger("wxpay java sdk");
        return logger;
    }

    /**
     * 获取当前时间戳，单位秒
     * @return
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis()/1000;
    }

    /**
     * 获取当前时间戳，单位毫秒
     * @return
     */
    public static long getCurrentTimestampMs() {
        return System.currentTimeMillis();
    }

    /**
     * 生成 uuid， 即用来标识一笔单，也用做 nonce_str
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }
 
    /**
     * POST方式发起http请求
     */
    public static String requestByPostMethod(String url,String type,String requestXML){
        CloseableHttpClient httpClient = getHttpClient();
        String str = "";
        try {
            HttpPost post = new HttpPost(url);          //这里用上本机的某个工程做测试
            //创建参数列表
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("j_username", "requestXML"));
            list.add(new BasicNameValuePair("j_password", "admin"));
            //url格式编码
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list,"UTF-8");
            post.setEntity(uefEntity);
            System.out.println("POST 请求...." + post.getURI());
            //执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(post);
            try{
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity){
                    System.out.println("-------------------------------------------------------");
                    System.out.println(EntityUtils.toString(uefEntity));
                    str = EntityUtils.toString(uefEntity);
                    System.out.println("-------------------------------------------------------");
                }
            } finally{
                httpResponse.close();
            }

        } catch( UnsupportedEncodingException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try{
                closeHttpClient(httpClient);                
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        
		return str;
    }
    private static CloseableHttpClient getHttpClient(){
        return HttpClients.createDefault();
    }
    private static void closeHttpClient(CloseableHttpClient client) throws IOException{
        if (client != null){
            client.close();
        }
    }
    /**
     * http 请求
     * @param parameters
     * @return
     */
    public static String getRequestXml(LinkedHashMap<String, String> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set<?> es = parameters.entrySet();
        Iterator<?> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k)
                    || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }
    /*public static String httpsRequest(String requestUrl, String requestMethod,
 			String outputStr) {
    	String result = null;
    	URL serverUrl;
		try {
			serverUrl = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
	        conn.setRequestMethod(requestMethod);
	        conn.setRequestProperty("Content-type", "application/json");
	        //必须设置false，否则会自动redirect到重定向后的地址
	        conn.setInstanceFollowRedirects(false);
	        conn.connect();
	        if (null != outputStr) {
 				OutputStream outputStream = conn.getOutputStream();
 				// 注意编码格式
 				outputStream.write(outputStr.getBytes("UTF-8"));
 				outputStream.close();
 			}
	        result = getReturn(conn, "UTF-8"); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
 	}*/
    /*请求url获取返回的内容*/
    public static String getReturn(HttpURLConnection connection,String CHARSET) throws IOException{
        StringBuffer buffer = new StringBuffer();
        //将返回的输入流转换成字符串
        InputStream  inputStream= null;
        InputStreamReader inputStreamReader = null;
        String result = "";
        try {
        	
        	inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream,CHARSET);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            result = buffer.toString();
           
        }catch (Exception e) {
			// TODO: handle exception
        	inputStreamReader.close();
			inputStream.close();
		}finally {
			inputStreamReader.close();
			inputStream.close();
		}
        return result;
    }
    public static String httpRequest(String requestUrl,String requestMethod,String outputStr){  
        StringBuffer buffer=null;  
        try{  
        URL url=new URL(requestUrl);  
        HttpURLConnection conn=(HttpURLConnection)url.openConnection();  
        conn.setDoOutput(true);  
        conn.setDoInput(true);  
        conn.setRequestMethod(requestMethod);  
        conn.connect();  
       /* conn.setRequestProperty("connection", "keep-alive"); */
 
        //往服务器端写内容 也就是发起http请求需要带的参数  
        if(null!=outputStr){  
            OutputStream os=conn.getOutputStream();  
            os.write(outputStr.getBytes("utf-8"));  
            os.close();  
        }  
          
        //读取服务器端返回的内容  
        InputStream is=conn.getInputStream();  
        InputStreamReader isr=new InputStreamReader(is,"utf-8");  
        BufferedReader br=new BufferedReader(isr);  
        buffer=new StringBuffer();  
        String line=null;  
        while((line=br.readLine())!=null){  
            buffer.append(line);  
        }  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        return buffer.toString();  
    }  
    
    /**
     * 发送https请求
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return 返回微信服务器响应的信息
     */
    public static String httpsRequest2(String requestUrl, String requestMethod, String outputStr,String authorization) {
        try {
        	 System.out.println("in ----------------zzz");
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            //conn.setSSLSocketFactory(ssf);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            if(org.apache.commons.lang3.StringUtils.isNotEmpty(authorization)) {
            	conn.setRequestProperty("authorization",authorization); 
            }
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded"); 
            
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            System.out.println(inputStream+"----------------zzz");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            return buffer.toString();
        } catch (ConnectException ce) {
//          log.error("连接超时：{}", ce);
        } catch (Exception e) {
//          log.error("https请求异常：{}", e);
        }
        return null;
    }
    public static String getIpAddress(HttpServletRequest request) { 
        String ip = request.getHeader("x-forwarded-for"); 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
          ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
          ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
          ip = request.getHeader("HTTP_CLIENT_IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
          ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
          ip = request.getRemoteAddr(); 
        } 
        return ip; 
      } 

    
}
