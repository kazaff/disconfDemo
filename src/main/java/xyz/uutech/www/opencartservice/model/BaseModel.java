package xyz.uutech.www.opencartservice.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * model基类
 *
 * @author chenx
 * @date 2016/4/25
 */
public class BaseModel implements Serializable {
    /**
     * 重写toString方法，方便调试
     * @return String
     *
     * @author chenx
     * @date 2016/4/25
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
