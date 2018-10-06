package cn.com.tsjx.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import cn.com.tsjx.common.constants.IConstant;
import cn.com.tsjx.common.fastdfs.FastdfsUtils;
import cn.com.tsjx.common.model.Result;

/**
 * 
 * 
 * 
 * xyl (2015-9-25 下午4:43:22)
 */
public /*static */final class Utility {
	
	/**
	 * 
	 * 
	 * @return
	 * 
	 * xyl (2015-9-25 下午4:47:43)
	 */
	public static final Date getNow() {
		return new Date();
	}
	
//	@SuppressWarnings("deprecation")
	/**
	 * 
	 * 
	 * @return
	 * 
	 * xyl (2015-10-14 上午11:20:47)
	 */
	public static final Date getNowStart() {
		Date dt_nowStart;
		
		Calendar cld = Calendar.getInstance();
		
		cld.set(Calendar.HOUR_OF_DAY, 0);
		cld.set(Calendar.MINUTE, 0);
		cld.set(Calendar.SECOND, 0);
		cld.set(Calendar.MILLISECOND, 0);
		
		dt_nowStart = cld.getTime();
		
		return dt_nowStart;
	}
	
//	@SuppressWarnings("deprecation")
	/**
	 * 
	 * 
	 * @return
	 * 
	 * xyl (2015-10-14 上午11:20:47)
	 */
	public static final Date getNowStop() {
		Date dt_nowStop;
		
		Calendar cld = Calendar.getInstance();
		
		cld.set(Calendar.HOUR_OF_DAY, 23);
		cld.set(Calendar.MINUTE, 59);
		cld.set(Calendar.SECOND, 59);
		cld.set(Calendar.MILLISECOND, 999);
		
		dt_nowStop = cld.getTime();
		
		return dt_nowStop;
	}
	
	/**
	 * 
	 * 
	 * @param time
	 * @return
	 * 
	 * xyl (2015-10-26 下午8:01:56)
	 */
	public static final boolean isCurrentDay(Date time) {
		boolean flag = false;
		
		if (time == null) {
			return false;
		}
		
		Date dt_nowStart = getNowStart();
		Date dt_nowStop = getNowStop();
		
		if (
				(time.after(dt_nowStart) && time.before(dt_nowStop)) 
				|| 
				(time.equals(dt_nowStart) || time.equals(dt_nowStop))
				) {
			flag = true;
		}
		
		return flag;
	}
	
	private static final String STR_DATETIME = "yyyy-MM-dd HH:mm:ss";
	private static final String STR_PURITY_DATETIME = "yyyyMMddHHmmssSSS";
	public static final int I_PURITY_DATETIME_LEN = STR_PURITY_DATETIME.length();
	
	private static final java.text.SimpleDateFormat SDF = new java.text.SimpleDateFormat(STR_DATETIME);
	private static final SimpleDateFormat SDF_PURITY = new SimpleDateFormat(STR_PURITY_DATETIME);
	
	/**
	 * 
	 * 
	 * @return
	 * 
	 * xyl (2015-9-25 下午4:48:05)
	 */
	public static final String getNowStr() {
		return SDF.format(getNow());
	}
	
	/**
	 * 
	 * 
	 * @return
	 * 
	 * xyl (2015-9-28 下午11:09:11)
	 */
	public static final String getPurityNowStr() {
		return SDF_PURITY.format(getNow());
	}
	
	/**
	 * 
	 * 
	 * @param date
	 * @return
	 * 
	 * xyl (2015-11-27 下午4:23:41)
	 */
	public static final String format(Date date) {
		return SDF.format(date);
	}
	
	/**
	 * 
	 * 
	 * @param filePath
	 * @return
	 * 
	 * xyl (2015-11-21 下午7:53:51)
	 */
	public static final InputStream loadFile(String filePath) {
		return Utility.class.getClassLoader().getResourceAsStream(filePath);
	}
	
	/**
	 * 
	 * 
	 * @param src
	 * @param dst
	 * 
	 * xyl (2015-10-22 下午4:32:20)
	 */
	public static final void copyObject(Object src, Object dst) {
		try {
			PropertyUtils.copyProperties(dst, src);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static final String getFilePath(String name) {
		String str_getFilePath = null;
		
		try {
			java.net.URI uri = FastdfsUtils.class.getResource(name).toURI();
			str_getFilePath = uri.toString();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return str_getFilePath;
	}
	
	/**
	 * 
	 * @return
	 */
	public static final String getFilePath() {
		return getFilePath("/");
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static final String getSuffixName(String fileName) {
		if (fileName == null) {
			return null;
		}
		return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
	}
	
	/**
	 * 
	 * @param inputStream
	 * @return
	 */
	public static final long getInputStreamSize(java.io.InputStream inputStream) {
		long l_size = 0;
		
		try {
			if (inputStream.markSupported()) {
				inputStream.mark(0);
			}
			
			byte[] bArr = new byte[1024];
			int len;
			while ((len = inputStream.read(bArr, 0, bArr.length)) > -1) {
				l_size += len;
			}
			
			if (inputStream.markSupported()) {
				inputStream.reset();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return l_size;
	}
	
	/**
	 * 
	 * 
	 * @param request
	 * @return
	 * 
	 * xyl (2015-9-29 上午1:07:43)
	 */
	public static final Map<String, String> convertParameters(HttpServletRequest request) {
		Map<String,String> params = null;
		
		Map<String, String[]> requestParams = request.getParameterMap();
		if (requestParams != null && requestParams.size() > 0) {
			params = new HashMap<String,String>(requestParams.size());
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = iter.next();
				String[] values = requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}
		}
		
		return params;
	}
	
	/**
	 * 
	 * 
	 * @param request
	 * @return
	 * 
	 * xyl (2015-11-20 下午5:19:05)
	 */
	public static final InputStream getInputStreamFromRequest(HttpServletRequest request) {
		InputStream inputStream = null;
		
		try {
			inputStream = request.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return inputStream;
	}
	
	/**
	 * 
	 * 
	 * @param request
	 * @return
	 * 
	 * xyl (2015-11-20 下午5:19:05)
	 */
	public static final String getContentFromRequest(HttpServletRequest request) {
		String str_content = null;
		
		try {
			str_content = IOUtils.toString(request.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return str_content;
	}
	
	/**
	 * <ul>
	 * 	 <li>http://dev.chuanjinet.com:8006</li>
	 * 	 <li>http://chuanji.jios.org:8006</li>
	 * 	 <li>http://www.goyoto.net:80</li>
	 *   <li>http://chuanjinet.hicp.net</li>
	 * </ul>
	 * 
	 * @param request
	 * @return
	 * 
	 * xyl (2015-12-21 下午4:59:51)
	 */
	public static final String getBaseURL(HttpServletRequest request) {
		String str_baseURL;
		
		str_baseURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()/* + "/"*/;
		
		return str_baseURL;
	}
	
	/**
	 * 
	 * 
	 * @param result
	 * @param message
	 * 
	 * xyl (2015-12-2 下午2:42:40)
	 */
	public static final void setResultFalse(Result<?> result, String message) {
		result.setResult(Boolean.FALSE);
		result.setMessage(message);
	}
	
	/**
	 * 
	 * 
	 * @param result
	 * 
	 * xyl (2015-12-14 下午4:55:31)
	 */
	public static final void setResultInvalidateParameter(Result<?> result) {
		setResultFalse(result, "非法的参数！");
	}
	
	/**
	 * 
	 * 
	 * @param list
	 * @return
	 * 
	 * xyl (2015-10-15 下午12:20:40)
	 */
	public static final String list2String(List<?> list) {
		String str = null;
		
		if (list != null && list.size() > 0) {
			StringBuilder strBud = new StringBuilder();
			
			for (int i = 0; i < list.size(); i++) {
				Object obj = list.get(i);
				
				strBud.append(obj).append(IConstant.DEGREE);
			}
			strBud.deleteCharAt(strBud.length() - 1);
			
			str = strBud.toString();
		}
		
		return str;
	}
	
	private static final ObjectMapper om = new ObjectMapper();
	
	/**
	 * 
	 * 
	 * @param obj
	 * @return
	 * 
	 * xyl (2015-12-2 下午1:47:16)
	 */
	public static final String toString(Object obj) {
		String str_toString = null;
		
		try {
			str_toString = om.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return str_toString;
	}
	
	/**
	 * 
	 * 
	 * @param str
	 * @param funcName
	 * @return
	 * 
	 * xyl (2015-12-17 下午4:52:42)
	 */
	public static final String getJSONP(String str, String funcName) {
		String str_JSONP = null;
		
		str_JSONP = funcName + "(" + str + ");";
		
		return str_JSONP;
	}
	
	/**
	 * 大写开头
	 * 
	 * @param str
	 * @return
	 * 
	 * xyl (2015-12-15 下午12:06:24)
	 */
	public static final String getUppercaseStartString(String str) {
		return (char) (str.charAt(0) - 32) + str.substring(1);
	}
	
	/**
	 * 
	 * 可以到时候再做内存管理
	 * 
	 * xyl (2015-12-3 上午11:41:52)
	 */
	public static final class Reflect {
		
		public static final class Parameter {
			public Class<?>[] parameterTypes;
			public Object[] values;
			public Parameter(Class<?>[] parameterTypes, Object[] values) {
				//super();
				this.parameterTypes = parameterTypes;
				this.values = values;
			}
		};
		
		public static final String getClassName(Object obj) {
			return obj.getClass().getName();
		}
		
		/**
		 * 
		 * 
		 * @param name
		 * @return
		 * 
		 * xyl (2015-12-15 下午12:08:22)
		 */
		public static final String getSetterName(String name) {
			return IConstant.SET + getUppercaseStartString(name);
		}
		
		/**
		 * 
		 * 
		 * @param name
		 * @return
		 * 
		 * xyl (2015-12-15 下午12:08:30)
		 */
		public static final String getGetterName(String name) {
			return IConstant.GET + getUppercaseStartString(name);
		}
		
		/**
		 * 
		 * 
		 * @param names
		 * @return
		 * 
		 * xyl (2015-12-15 下午12:08:40)
		 */
		public static final String getGetterName(String[] names) {
			StringBuilder strBud = new StringBuilder(IConstant.GET);
			
			for (int i = 0; i < names.length; strBud.append(getUppercaseStartString(names[i++])));
			
			return strBud.toString();
		}
		
		public static final java.lang.reflect.Method getMethod(Object obj, Class<?> cls, String name, Class<?>... parameterTypes) {
			java.lang.reflect.Method method = null;
			
			try {
				method = cls.getDeclaredMethod(name, parameterTypes);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
			
			return method;
		}
		
		private static final Object invoke(Object obj, java.lang.Class<?> cls, String name, Parameter parameter) {
			Object obj_ret = null;
			
			Class<?>[] parameterTypes;
			Object[] values;
			if (parameter == null) {
				values = parameterTypes = null;
			} else {
				parameterTypes = parameter.parameterTypes;
				values = parameter.values;
			}
			
			java.lang.reflect.Method method = getMethod(obj, cls, name, parameterTypes);
			try {
				obj_ret = method.invoke(obj, values);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
			return obj_ret;
		}
		
		@SuppressWarnings("unchecked")
		public static final <T> T setValue(Object obj, String name, Parameter parameter) {
			T t = null;
			
			if (obj == null || name == null) {
				return null;
			}
			
			Class<?> cls = obj.getClass();
			t = (T) invoke(obj, cls, name, parameter);
			
			return t;
		}
		
		@SuppressWarnings("unchecked")
		public static final <T> T getValue(Object obj, String name) {
			T t = null;
			
			if (obj == null || name == null) {
				return null;
			}
			
			Class<?> cls = obj.getClass();
			t = (T) invoke(obj, cls, name, null);
			
			return t;
		}
		
	};
	
	/**
	 * 
	 * 
	 * @param flag
	 * @return
	 * 
	 * xyl (2015-12-17 下午2:16:25)
	 */
	public static final int getResultCode(boolean flag) {
		return flag ? IConstant.IResultCode.SUCCESS_CODE : IConstant.IResultCode.FAIL_CODE;
	}
	
	/**
	 * 
	 * 
	 * @param resultCode
	 * @return
	 * 
	 * xyl (2015-12-17 下午2:25:16)
	 */
	public static final boolean setResultCode(int resultCode) {
		return resultCode == IConstant.IResultCode.SUCCESS_CODE;
	}
	
	/**
	 * 
	 * 
	 * @param affect
	 * @return
	 * 
	 * xyl (2015-12-17 下午2:31:44)
	 */
	public static final boolean setResultCodeFromAffect(int affect) {
		return affect > IConstant.ZERO;
	}
	
	/**
	 * 
	 * 
	 * @param affect
	 * @return
	 * 
	 * xyl (2015-12-17 下午2:31:44)
	 */
	public static final int getResultCodeFromAffect(int affect) {
		return getResultCode(setResultCodeFromAffect(affect));
	}
	
	/**
	 * 
	 * 
	 * @param resultCode
	 * @return
	 * 
	 * xyl (2015-12-17 下午2:44:33)
	 */
	public static final String setResultCodeToString(int resultCode) {
		return setResultCode(resultCode) ? IConstant.IResultCode.SUCCESS : IConstant.IResultCode.FAIL;
	}
	
	/**
	 * for test .
	 * 
	 * @param args
	 * 
	 * xyl (2015-10-14 上午11:22:32)
	 */
	public static void main(String[] args) {
	}
	
}
