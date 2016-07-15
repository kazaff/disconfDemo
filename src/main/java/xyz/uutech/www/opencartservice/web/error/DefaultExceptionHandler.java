package xyz.uutech.www.opencartservice.web.error;

import xyz.uutech.www.opencartservice.exception.AuthException;
import xyz.uutech.www.opencartservice.exception.NotFoundException;
import xyz.uutech.www.opencartservice.web.dto.error.ErrorCodeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.List;

/**
 * 异常处理类
 *
 * @author chenx
 * @date 2016/4/21
 */
@ControllerAdvice
public class DefaultExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandler.class); //日志记录器

    /**
     * 获取自定义授权异常
     *
     * @param ex 异常类
     * @return json
     *
     * @author chenx
     * @date 2016/4/21
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorCodeDTO notFoundErrorHandler(Exception ex) {
        NotFoundException e = (NotFoundException) ex;

        writeRuntimeExceptionLog(e);

        return new ErrorCodeDTO(e.getErrorCode());
    }

    /**
     * 获取自定义授权异常
     *
     * @param ex 异常类
     * @return json
     *
     * @author chenx
     * @date 2016/4/21
     */
    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorCodeDTO authErrorHandler(Exception ex) {
        AuthException e = (AuthException) ex;

        writeRuntimeExceptionLog(e);

        return new ErrorCodeDTO(e.getErrorCode());
    }

    /**
     * 捕获自定义运行时异常
     * @param ex 异常类
     * @return json
     *
     * @author cx
     * @since 2015-06-08
     */
    @ExceptionHandler(xyz.uutech.www.opencartservice.exception.RuntimeException.class)
    @ResponseBody
    public ErrorCodeDTO runtimeErrorHandler(Exception ex) {
        xyz.uutech.www.opencartservice.exception.RuntimeException e = (xyz.uutech.www.opencartservice.exception.RuntimeException) ex;

        writeRuntimeExceptionLog(e);

        return new ErrorCodeDTO(e.getErrorCode());
    }

    /**
     * 输出自定义运行时异常日志
     *
     * @param e 自定义运行时异常
     *
     * @author chenx
     * @date 2016/4/21
     */
    private void writeRuntimeExceptionLog(xyz.uutech.www.opencartservice.exception.RuntimeException e) {
        String exMsg = e.getMessage();
        String logMsg = e.getErrorCode() + ";";
        if (exMsg != null && !exMsg.isEmpty()) {
            logMsg += exMsg;
        }

        if (e.getCause() == null) {
            LOGGER.error(logMsg);
        }
        else {
            LOGGER.error(logMsg, e);
        }
    }

    /**
     * 参数异常捕获
     * @param ex 异常类
     * @return json
     *
     * @author cx
     * @since 2015/6/13
     */
    @ExceptionHandler({MissingServletRequestParameterException.class, TypeMismatchException.class, IllegalArgumentException.class, IllegalStateException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorCodeDTO conversionErrorHandler(Exception ex) {
        //记录日志
        LOGGER.error("arg error", ex);
        return new ErrorCodeDTO(1007);
    }

    /**
     * 文件上传过大异常捕获
     * @param ex 异常类
     * @return json
     *
     * @author cx
     * @since 2015/12/2
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorCodeDTO uploadMaxErrorHandler(Exception ex) {
        //记录日志
        LOGGER.error("upload file is too large", ex);
        return new ErrorCodeDTO(1009);
    }

    /**
     * 捕获注解校验异常
     * @param ex 异常类
     * @return json
     *
     * @author cx
     * @since 2015-06-08
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorCodeDTO argsErrorHandler(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        //获取错误字段的第一项作为提示信息使用
        FieldError fisrtOne = fieldErrors.get(0);

        LOGGER.error(fisrtOne.getDefaultMessage(), fieldErrors);
        return new ErrorCodeDTO(1007);
    }

    /**
     * 统一捕获异常
     * @param ex 异常类
     * @return json
     *
     * @author cx
     * @since 2015-6-8
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorCodeDTO defaultErrorHandler(Exception ex) {
        //记录日志
        LOGGER.error("server error", ex);
        return new ErrorCodeDTO(1001);
    }
}
