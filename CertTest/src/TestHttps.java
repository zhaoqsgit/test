import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;


public class TestHttps {
	public static void main(String[] args) throws Exception{  
        SSLContext sslcontext = SSLContext.getInstance("SSL","SunJSSE");
        MyX509TrustManager myX509TrustManager = new MyX509TrustManager();
        TrustManager[] trustManager = new TrustManager[]{myX509TrustManager};
        sslcontext.init(null, trustManager, new java.security.SecureRandom());  
//        URL serverUrl = new URL("https://euc.yonyoucloud.com/cas/login?sysid=ipu&service=https%3A%2F%2Fyc.yonyoucloud.com%2Fyuncai%2FSSO%2Flogin.jsp%3Fr%3DL3l1bmNhaS9wb3J0YWw");  
        URL serverUrl = new URL("https://euc.yonyoucloud.com");  
        HttpsURLConnection conn = (HttpsURLConnection) serverUrl.openConnection();  
        conn.setSSLSocketFactory(sslcontext.getSocketFactory());  
        conn.setRequestMethod("GET");  
        conn.setRequestProperty("Content-type", "application/json");  
        //必须设置false，否则会自动redirect到重定向后的地址  
        conn.setInstanceFollowRedirects(false);  
        conn.connect();  
        String result = getReturn(conn);
        //
        System.out.println("start 1.6...");
        System.out.println(result);
        System.out.println("end===================================");
    }  
  
    /*请求url获取返回的内容*/  
    public static String getReturn(HttpsURLConnection connection) throws IOException{  
        StringBuffer buffer = new StringBuffer();
        String result = "";
        //将返回的输入流转换成字符串  
        try {
        	InputStream inputStream = connection.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            result = buffer.toString();  
		} catch (Exception e) {
			System.out.println("error...............");
			System.out.println(e);
			e.printStackTrace();
		}
		 return result;  
    }  
}  

