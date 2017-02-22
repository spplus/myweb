package qc.com.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Pages.
 * 
 * @author chengxx
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Pages {
	public boolean pageParam() default true;

	public boolean pageResult() default true;

	public int showCount() default 10;

	public int pageType() default 0;// 分页类型
}
