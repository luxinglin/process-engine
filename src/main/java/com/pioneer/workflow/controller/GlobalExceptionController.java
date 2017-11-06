package com.pioneer.workflow.controller;


import com.pioneer.workflow.common.RestRetn;
import com.pioneer.workflow.common.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Rest Controller 公共异常处理类
 */
@ControllerAdvice
public class GlobalExceptionController {
    private static final String IOEX_CODE = "88";
    private static final String ILAE_CODE = "89";
    private static final String CE_CODE = "90";
    private static final String NFE_CODE = "91";
    private static final String SQLE_CODE = "92";
    private static final String ITE_CODE = "93";
    private static final String IAE_CODE = "94";
    private static final String NE_CODE = "95";
    private static final String IOE_CODE = "96";
    private static final String UE_CODE = "97";
    private static final String PE_CODE = "98";
    private static final String E_CODE = "99";
    protected static Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);

    @ExceptionHandler(UserException.class)
    @ResponseBody
    public RestRetn handleCustomException(UserException ex) {
        RestRetn restRetn = new RestRetn();
        restRetn.setErrorCode(UE_CODE);
        restRetn.setMessage(ex.getMessage());
        //记录错误日志
        logger.error(ex.getMessage());
        //打印控制台堆栈，调试使用
        ex.printStackTrace();
        return restRetn;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public RestRetn handleAllException(Throwable ex) {
        RestRetn restRetn = new RestRetn();
        if (ex instanceof InvocationTargetException) {
            restRetn.setErrorCode(ITE_CODE);
            restRetn.setMessage("操作失败，发生调用目标异常！");
        } else if (ex instanceof IllegalAccessException) {
            restRetn.setErrorCode(IAE_CODE);
            restRetn.setMessage("操作失败，发生不允许访问某类异常！");
        } else if (ex instanceof NullPointerException) {
            restRetn.setErrorCode(NE_CODE);
            restRetn.setMessage("操作失败，发生空指针异常！");
        } else if (ex instanceof IndexOutOfBoundsException) {
            restRetn.setErrorCode(IOE_CODE);
            restRetn.setMessage("操作失败，发生数组下标越界异常！");
        } else if (ex instanceof ParseException) {
            restRetn.setErrorCode(PE_CODE);
            restRetn.setMessage("操作失败，发生数据转换异常！");
        } else if (ex instanceof NumberFormatException) {
            restRetn.setErrorCode(NFE_CODE);
            restRetn.setMessage("操作失败，发生字符串转换为数字抛出的异常！");
        } else if (ex instanceof IllegalArgumentException) {
            restRetn.setErrorCode(ILAE_CODE);
            restRetn.setMessage("操作失败，发生非法参数异常！");
        } else if (ex instanceof ClassCastException) {
            restRetn.setErrorCode(CE_CODE);
            restRetn.setMessage("操作失败，发生类型转换异常类！");
        } else if (ex instanceof IOException) {
            restRetn.setErrorCode(IOEX_CODE);
            restRetn.setMessage("操作失败，发生文件读写异常！");
        } else if (ex instanceof SQLException) {
            restRetn.setErrorCode(SQLE_CODE);
            restRetn.setMessage("操作失败，发生操作数据库异常类！");
        } else {
            restRetn.setErrorCode(E_CODE);
            restRetn.setMessage("操作失败，发生未知异常！");
        }
        //记录错误日志
        logger.error(ex.getMessage());
        //打印控制台堆栈，调试使用
        ex.printStackTrace();
        return restRetn;
    }

}