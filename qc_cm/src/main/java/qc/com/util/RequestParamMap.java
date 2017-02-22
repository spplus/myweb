package qc.com.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * RequestParamMap.
 * 
 * @author chengxx
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParamMap {
    public Pages pages() default @Pages(pageParam = false, pageResult = false);// 请求参数是否需要分页处理

    public String resultKey() default "RESULT";
    
    public boolean needRequest() default false;
    
    public boolean needResponse() default false;
}
