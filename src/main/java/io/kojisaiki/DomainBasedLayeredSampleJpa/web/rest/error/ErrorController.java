package io.kojisaiki.DomainBasedLayeredSampleJpa.web.rest.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 例外処理コントローラ
 */
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleOtherException(Exception e) {
        return "exception occured!" + e.getMessage();
    }
}
