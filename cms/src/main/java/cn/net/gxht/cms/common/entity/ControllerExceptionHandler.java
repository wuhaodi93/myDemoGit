package cn.net.gxht.cms.common.entity;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 10604 on 2017/8/5.
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    Logger logger= LogManager.getLogger(ControllerExceptionHandler.class);
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult handleException(Exception e){
        e.printStackTrace();
        logger.debug(e.getMessage());
        return new JsonResult(e);
    }
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public JsonResult handleException(RuntimeException e){
        e.printStackTrace();
        logger.debug(e.getMessage());
        return new JsonResult(e);
    }
}

