package cn.com.tsjx.common.context;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标注Action中的方法是否忽略权限认证。
 * <p>
 * 当一个请求过来后，首先看这个方法是否需要忽略权限认证，如果有忽略，则不经过权限认证直接调用。
 * </p>
 * <p>
 * 该注解标记在方法上表示该方法忽略权限认证，标记在类上表示该类中的所有方法忽略权限认证。
 * </p>
 * @author liwei
 */
@Documented
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface NoAuth {

}
