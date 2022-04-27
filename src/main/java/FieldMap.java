package tv.lazygoat.spark.common;

import java.lang.annotation.*;  

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface FieldMap {
    public String targetName() default "";
}

