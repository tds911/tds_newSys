package com.tds.common.framework.aspectj.lang.annotation;

import com.tds.common.framework.aspectj.lang.enums.BusinessType;
import com.tds.common.framework.aspectj.lang.enums.OperatorType;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    public String title()default "";
    public BusinessType businessType() default  BusinessType.OTHER;
    public OperatorType operatorType() default OperatorType.MANAGE;
    public String methodTile()default "";
    public boolean isSaveRequestData() default true;
}
