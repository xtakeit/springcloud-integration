package cn.edu.dgut.integration.common.exception;

import cn.edu.dgut.integration.common.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局统一异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 处理所有未知异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult handleException(Exception e) {
        log.info("系统异常信息",e);
        return JsonResult.errorException(e.getMessage());
    }

    /**
     * 处理业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceRuntimeException.class)
    @ResponseBody
    public JsonResult handleServiceRuntimeException(ServiceRuntimeException e) {
        log.info("业务异常信息",e);
        return JsonResult.errorMsg(e.getMessage());
    }


}
