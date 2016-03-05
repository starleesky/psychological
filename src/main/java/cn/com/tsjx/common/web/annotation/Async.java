package cn.com.tsjx.common.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标注Action中的方法是否为异步调用。
 * <p>
 * 该注解用于异步方法时对异常的统一捕获并处理。
 * </p>
 * <p>
 * 传统页面中，当Action出异常时经常是跳转到一个错误页面，页面中显示错误信息。
 * 而通过异步调用时，当Action出现异常时，通常是弹出提示框来警告用户，这时需要返回一个JSON格式的错误数据。
 * 在项目中我们一般通过拦截器来实现异常的处理，这里我们通过该注解来判断请求的调用类型，作出不一样的处理动作。
 * </p>
 * @author liwei
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Async {

}
