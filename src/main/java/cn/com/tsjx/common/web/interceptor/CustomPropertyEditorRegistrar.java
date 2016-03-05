package cn.com.tsjx.common.web.interceptor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * 自定义属性编辑器
 * @Type CustomPropertyEditorRegistrar
 * @Desc 
 * @author tianzhonghong
 * @date 2015年8月14日
 * @Version V1.0
 */
public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {

        registry.registerCustomEditor(String.class, new PropertyEditorSupport() {
            
            public void setAsText(String value) {
                // 字符串trim
                setValue(StringUtils.trim(value));
            }
        });
    }
}
