package cn.com.tsjx.common.constants;

import java.util.HashMap;
import java.util.Map;

public interface ImageConstant {
	/**
	 * JPEG
	 */
    String JPEG="JPEG";
   /**
    * GIF
    */
   String GIF="GIF";
   /**
    * PNG
    */
   String PNG="PNG";
   /**
    * BMP
    */
   String BMP="BMP";
   
   Map<String, String> INFO = new HashMap<String, String>(){/**
	 * 
	 */
	private static final long serialVersionUID = 7963234143688622143L;

{
	put(JPEG,JPEG);
	put(GIF,GIF);
	put(PNG,PNG);
	put(BMP,BMP);
	}};
}
