import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Test {
	public static void main(String[] args) throws Exception{
        URL serverUrl = new URL("https://euc.yonyoucloud.com/cas/login?sysid=ipu&service=https%3A%2F%2Fyc.yonyoucloud.com%2Fyuncai%2FSSO%2Flogin.jsp%3Fr%3DL3l1bmNhaS9wb3J0YWw");
        HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        // 
        conn.setInstanceFollowRedirects(false);
        conn.connect(); 
        String result = getReturn(conn);
        System.out.println("start 1.6...");
        System.out.println(result);
        System.out.println("end===================================");
    }

    /*request*/
    public static String getReturn(HttpURLConnection connection) {
        StringBuffer buffer = new StringBuffer();
        //return
        try {
        	InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            String result = buffer.toString();
            return result;
		} catch (Exception e) {
			System.out.println("error...............");
			System.out.println(e);
		}
		return "OK";
}
}


