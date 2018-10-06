package cn.com.tsjx.common.util.httpclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import cn.com.tsjx.common.constants.BizExceptionConstant;
import cn.com.tsjx.common.constants.CharsetConstant;
import cn.com.tsjx.common.util.exception.BizException;
import cn.com.tsjx.common.util.lang.StringUtil;
/**
 * HttpPost请求
 * @author crazy_cabbage
 *
 */
@SuppressWarnings("deprecation")
public class HttpPostUtil {
	/**
	 * POST 请求
	 * @param uri 请求地址
 	 * @param headers 请求头
	 * @param content 请求内容
	 * @param charset 请求/响应字符集
	 * @return 回传字符串
	 */
	public static String post(String uri, Map<String, String> headers, String content, String charset) {
		BasicHttpParams httpParams = new BasicHttpParams();
		httpParams.setParameter(HttpProtocolParams.HTTP_CONTENT_CHARSET, charset);
		httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
		httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT,new Integer(3000));
		HttpClient httpClient = new DefaultHttpClient(httpParams);
		HttpPost httpPost = new HttpPost(uri);
		try {
			HttpEntity httpEntity = new StringEntity(content, charset);
			httpPost.setEntity(httpEntity);
			if (headers != null) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}
			HttpResponse responseBody = httpClient.execute(httpPost);
			return EntityUtils.toString(responseBody.getEntity(),charset);
		} catch (Exception e) {
			throw new BizException(BizExceptionConstant.URL_CONNECTION_EXCEPTION, StringUtil.EMPTY, e);
		}finally{
			httpClient.getConnectionManager().shutdown();
		}
	}
	/**
	 * 以ISO-8859-1 字符集请求
	 * @param uri 请求地址
	 * @param headers 请求头
	 * @param content 请求内容
	 * @return 回传字符串
	 */
	public static String postISO(String uri, Map<String, String> headers, String content) {
		return post(uri, headers, content, CharsetConstant.ISO_8859_1);
	}
	/**
	 * 以UTF-8字符集请求
	 * @param uri 请求地址
	 * @param headers 请求头
	 * @param content 请求内容 
	 * @return 回传字符串
	 */
	public static String postUTF_8(String uri, Map<String, String> headers, String content) {
		return post(uri, headers, content, CharsetConstant.UTF_8);
	}
	/**
	 * 以GBK字符集请求
	 * @param uri 请求地址
	 * @param headers 请求头
	 * @param content 请求内容
	 * @return 回传字符串
	 */
	public static String postGBK(String uri, Map<String, String> headers, String content) {
		return post(uri, headers, content, CharsetConstant.GBK);
	}
	/**
	 * 键值对的 POST请求
	 * @param uri 请求路径
	 * @param headers 请求头
	 * @param nameValue 请求键值
	 * @return 回传字符串
	 */
	public static String postNameValue(String uri,Map<String,String> headers,Map<String,String[]> nameValue,String charset){
		BasicHttpParams httpParams = new BasicHttpParams();
		httpParams.setParameter(HttpProtocolParams.HTTP_CONTENT_CHARSET, charset);
		httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
		httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT,new Integer(5000));
		HttpClient httpClient = new DefaultHttpClient(httpParams);
		HttpPost httpPost = new HttpPost(uri);
		   List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		   if(nameValue!=null){
			   for(Map.Entry<String, String[]> entry:nameValue.entrySet())
			   { 
				   for(String value:entry.getValue()){
					   parameters.add(new BasicNameValuePair(entry.getKey(), value));
				   }
			   }
		   }
		try {
		    HttpEntity httpEntity = new UrlEncodedFormEntity(parameters, charset);
			httpPost.setEntity(httpEntity);
			if (headers != null) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}
			HttpResponse responseBody = httpClient.execute(httpPost);
			return EntityUtils.toString(responseBody.getEntity(),charset);
		} catch (Exception e) {
			throw new BizException(BizExceptionConstant.URL_CONNECTION_EXCEPTION, StringUtil.EMPTY, e);
		}finally{
			httpClient.getConnectionManager().shutdown();
		}
	}
	/**
	 * 以ISO-8859-1 字符集请求回传  
	 * @param uri 请求路径
	 * @param headers 请求头
	 * @param nameValue 请求键值
	 * @return  请求回传字符串
	 */
	public static String postNameValueISO(String uri,Map<String,String> headers,Map<String,String[]> nameValue){
		return postNameValue( uri, headers,nameValue,CharsetConstant.ISO_8859_1);
	}
	/**
	 * 以UTF-8 字符集请求回传  
	 * @param uri 请求路径
	 * @param headers 请求头
	 * @param nameValue 请求键值
	 * @return  请求回传字符串
	 */
	public static String postNameValueUTF_8(String uri,Map<String,String> headers,Map<String,String[]> nameValue){
		return postNameValue( uri, headers,nameValue,CharsetConstant.UTF_8);
	}
	/**
	 * 以GBK字符集请求回传  
	 * @param uri 请求路径
	 * @param headers 请求头
	 * @param nameValue 请求键值
	 * @return  请求回传字符串
	 */
	public static String postNameValueGBK(String uri,Map<String,String> headers,Map<String,String[]> nameValue){
		return postNameValue( uri, headers,nameValue,CharsetConstant.GBK);
	}

}
