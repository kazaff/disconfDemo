package xyz.uutech.www.opencartservice.exception;

/**
 * 自定义运行时异常
 *
 * @author chenx
 * @date 2016/4/20
 */
public class RuntimeException extends java.lang.RuntimeException {
    //错误代码
    private int errorCode;

    /**
     * 构造函数
     *
     * @param errorCode 错误码
     * @param msg 日志异常信息
     * @param cause 异常原因
     *
     * @author chenx
     * @date 2016/04/20
     */
    public RuntimeException(int errorCode, String msg, Throwable cause) {
        super(msg, cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
