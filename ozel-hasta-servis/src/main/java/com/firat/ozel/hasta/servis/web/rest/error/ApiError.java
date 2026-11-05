package com.firat.ozel.hasta.servis.web.rest.error;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiError {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> RuntimeExceptionHandler(RuntimeException e) throws JSONException {
        JSONObject response = new JSONObject();
        response.put("message", e.getMessage());
        return new ResponseEntity<String>(response.toString(), HttpStatus.BAD_REQUEST);
    }
}
