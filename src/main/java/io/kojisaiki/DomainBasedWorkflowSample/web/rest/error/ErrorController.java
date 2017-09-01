package io.kojisaiki.DomainBasedWorkflowSample.web.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
