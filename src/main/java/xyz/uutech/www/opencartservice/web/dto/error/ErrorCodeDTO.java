package xyz.uutech.www.opencartservice.web.dto.error;

/**
 * 错误码DTO
 *
 * @author chenx
 * @date 2016/4/21
 */
public class ErrorCodeDTO {
    private int errorCode;

    public ErrorCodeDTO(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
