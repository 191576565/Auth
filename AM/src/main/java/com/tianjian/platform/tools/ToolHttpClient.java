package com.tianjian.platform.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.jfinal.log.Log;

/**
 *
 * http https请求 get 和 post
 * 
 * @author [hujian]
 * @version [1.0]
 * @date [2017年5月3日]
 */
public class ToolHttpClient {

	private static final Log log = Log.getLog(ToolHttpClient.class);

	/**
	 * HTTP请求方法GET
	 */
	public static final String http_method_get = "GET";

	/**
	 * HTTP请求方法POST
	 */
	public static final String http_method_post = "POST";

	/**
	 * 发送post请求
	 * 
	 * @param url
	 * @param parameters
	 * @param propertys
	 */
	public static String SendHttpPost(String url, Map<String, Object> parameters, Map<String, String> propertys) {
		if (url.length()>8){
			if (url.substring(0, 5).equals("https")){
				System.out.println("-----------");
				return httpRequest(true, url, "POST", parameters, propertys);

			}else{
				return httpRequest(false, url, "POST", parameters, propertys);
			}
		}else{
			log.error("url lenth is not right");
			return "url error";
		}
		
	}

	/**
	 * 发送GET请求,自动区分http和https
	 * 
	 * @param url
	 * @param parameters
	 * @param propertys
	 */
	public static String SendHttpGet(String url, Map<String, Object> parameters, Map<String, String> propertys) {
		if (url.length()>8){
			if (url.substring(0, 5).equals("https")){
				return httpRequest(true, url, "GET", parameters, propertys);

			}else{
				return httpRequest(false, url, "GET", parameters, propertys);
			}
		}else{
			log.error("url lenth is not right");
			return "url error";
		}
		
	}

	
	/**
	 * 原生方式请求
	 * 
	 * @param isHttps
	 *            是否https
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return
	 */
	public static String httpRequest(boolean isHttps, String requestUrl, String requestMethod,
			Map<String, Object> parameters, Map<String, String> propertys) {
		HttpURLConnection conn = null;

		OutputStream outputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		PrintWriter printWriter = null;

		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		// 跳过证书验证
		HostnameVerifier hv = new HostnameVerifier() {
			public boolean verify(String urlHostName, SSLSession session) {
				return true;
			}
		};
		// 拼接请求参数
		if (requestMethod == http_method_get && parameters != null) {
			StringBuffer param = new StringBuffer();
			int i = 0;
			for (String key : parameters.keySet()) {
				if (i == 0)
					param.append("?");
				else
					param.append("&");

				Object value = parameters.get(key);
				if (value == null)
					continue;

				// //如果是数据，说明需要拼接一个参数多个值
				// if (value.getClass().isArray())
				// param.append(concatParams(key, (Object[]) value));
				// else
				param.append(key).append("=").append(value);
				i++;
			}
			requestUrl += param.toString();
		}
		try {
			URL url = new URL(requestUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (isHttps) {

				HttpsURLConnection httpsConn = (HttpsURLConnection) conn;
				// 跳过证书验证
				httpsConn.setHostnameVerifier(hv);
				// 创建SSLContext对象，并使用我们指定的信任管理器初始化
				TrustManager[] tm = { new X509TrustManager() {
					@Override
					public void checkClientTrusted(X509Certificate[] chain, String authType)
							throws CertificateException {
						// 检查客户端证书
					}

					public void checkServerTrusted(X509Certificate[] chain, String authType)
							throws CertificateException {
						// 检查服务器端证书
					}

					public X509Certificate[] getAcceptedIssuers() {
						// 返回受信任的X509证书数组
						return null;
					}
				} };
				SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
				sslContext.init(null, tm, new java.security.SecureRandom());
				SSLSocketFactory ssf = sslContext.getSocketFactory();// 从上述SSLContext对象中得到SSLSocketFactory对象
				httpsConn.setSSLSocketFactory(ssf);
				conn = httpsConn;
			}

			// 超时设置，防止 网络异常的情况下，可能会导致程序僵死而不继续往下执行
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);

			// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
			// http正文内，因此需要设为true, 默认情况下是false;
			conn.setDoOutput(true);

			// 设置是否从httpUrlConnection读入，默认情况下是true;
			conn.setDoInput(true);

			// Post 请求不能使用缓存
			conn.setUseCaches(false);

			// 设定传送的内容类型是可序列化的java对象
			// (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			// 设置自定义的请求头参数
			if (propertys != null) {
				for (String key : propertys.keySet()) {
					// System.out.println("key= " + key + " and value= " +
					// headers.get(key));
					conn.setRequestProperty(key, propertys.get(key));
				}
			}
			// 设置请求方式（GET/POST），默认是GET
			System.out.println("requestMethod:" + requestMethod);
			conn.setRequestMethod(requestMethod);

			// 连接，上面对urlConn的所有配置必须要在connect之前完成，
			conn.connect();

			// 当outputStr不为null时向输出流写数据
			if (requestMethod == http_method_post && parameters != null) {
				StringBuffer param = new StringBuffer();
				for (String key : parameters.keySet()) {
					param.append("&");
					Object value = parameters.get(key);
					if (value == null)
						continue;

					// 如果是数据，说明需要拼接一个参数多个值
					// if (value.getClass().isArray())
					// param.append(concatParams(key, (Object[]) value));
					// else
					param.append(key).append("=").append(value);
				}
				outputStream = conn.getOutputStream();
				outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
				printWriter = new PrintWriter(outputStreamWriter);
				printWriter.write(param.toString());
				printWriter.flush();
				printWriter.close();
			}

			// 从输入流读取返回内容
			inputStream = conn.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, ToolString.encoding);
			bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuilder buffer = new StringBuilder();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str).append("\n");
			}
			System.out.println("code" + conn.getResponseCode());
			return buffer.toString();
		} catch (ConnectException ce) {
			if (log.isErrorEnabled())
				log.error("连接超时：{}", ce);
			return "exception";

		} catch (Exception e) {
			if (log.isErrorEnabled())
				log.error("https请求异常：{}", e);
			return "exception";

		} finally { // 释放资源
			if (null != outputStream) {
				try {
					outputStream.close();
				} catch (IOException e) {
					if (log.isErrorEnabled())
						log.error("outputStream.close()异常", e);
				}
				outputStream = null;
			}

			if (null != outputStreamWriter) {
				try {
					outputStreamWriter.close();
				} catch (IOException e) {
					if (log.isErrorEnabled())
						log.error("outputStreamWriter.close()异常", e);
				}
				outputStreamWriter = null;
			}

			if (null != printWriter) {
				printWriter.close();
				printWriter = null;
			}

			if (null != bufferedReader) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					if (log.isErrorEnabled())
						log.error("bufferedReader.close()异常", e);
				}
				bufferedReader = null;
			}

			if (null != inputStreamReader) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					if (log.isErrorEnabled())
						log.error("inputStreamReader.close()异常", e);
				}
				inputStreamReader = null;
			}

			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					if (log.isErrorEnabled())
						log.error("inputStream.close()异常", e);
				}
				inputStream = null;
			}

			if (null != conn) {
				conn.disconnect();
				conn = null;
			}
		}
	}

}
