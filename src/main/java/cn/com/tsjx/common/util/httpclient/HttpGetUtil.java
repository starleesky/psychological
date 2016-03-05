package cn.com.tsjx.common.util.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import cn.com.tsjx.common.constants.BizExceptionConstant;
import cn.com.tsjx.common.constants.CharsetConstant;
import cn.com.tsjx.common.util.exception.BizException;
import cn.com.tsjx.common.util.lang.StringUtil;
/**
 * httpGet请求工具类
 * @author crazy_cabbage
 *
 */
@SuppressWarnings("deprecation")
public class HttpGetUtil {
	/**
	 * 获得请求内容
	 * @param uri 请求路径
	 * @param charset 请求/回传 字符集
	 * @return 回传内容
	 */
	public static String getContent(String uri,String charset) {
		BasicHttpParams httpParams = new BasicHttpParams();
		httpParams.setParameter(HttpProtocolParams.HTTP_CONTENT_CHARSET, charset);
		httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
		httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT,new Integer(3000));
		HttpClient httpClient = new DefaultHttpClient(httpParams);
		HttpGet httpGet = new HttpGet(uri);
		try {
			HttpResponse responseBody = httpClient.execute(httpGet);
			return EntityUtils.toString(responseBody.getEntity(),charset);
		} catch (Exception e) {
			throw new BizException(BizExceptionConstant.URL_CONNECTION_EXCEPTION, StringUtil.EMPTY);
		}finally{
			httpClient.getConnectionManager().shutdown();
		}
	}
	/**
	 * 以ISO-8859-1字符集请求内容
	 * @param uri请求路径
	 * @return 回传内容
	 */
	public static String getContentISO(String uri) {
		return getContent(uri, CharsetConstant.ISO_8859_1);
	}
	/**
	 * 以UTF-8 字符集请求内容
	 * @param uri 请求路径
	 * @return  回传内容
	 */
	public static String getContentUTF_8(String uri) {
		return getContent(uri, CharsetConstant.UTF_8);
	}
	/**
	 * 以GBK字符集请求内容
	 * @param uri 请求路径
	 * @return 回传内容
	 */
	public static String getContentGBK(String uri) {
		return getContent(uri, CharsetConstant.GBK);
	}
}
