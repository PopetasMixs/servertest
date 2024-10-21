package com.crud.utilities;

import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Response {

    public static final String CODE_OK="200";
    public static final String CODE_BAD="400";
    public static final String FAILE_REQUEST="Error al ejecutar la petcion";
    public static final String STATUS="Succes";
    public static final String MENSAJE="Acciòn realizada con exito";

    public static Map<String, Object> getRespose(Object data,String status, String code, String msm, int size, int page, int items){
        Map<String, Object> response=new HashMap<>();
        response.put("fecha",new Date());
        response.put("code",code);
        response.put("status",status);
        response.put("mensaje",msm);
        response.put("currentPage",page);
        response.put("totalItems", items);
        response.put("totalPages", size);
        response.put("data",data);
        return response;
    }
    public static Map<String, Object> getRespose(String code,String status, String msm, int items){
        Map<String, Object> response=new HashMap<>();
        response.put("fecha", new Date());
        response.put("code",code);
        response.put("status",status);
        response.put("mensaje",msm);
        response.put("Total registros",items);
        return response;
    }

    //Error
    public static Map<String,Object> responseHttpError(String result,HttpStatus codeMessage,String data){
        Map<String,Object> response = new HashMap<>();
        response.put("date",new Date());
        response.put("code",codeMessage.value());
        response.put("message",result);
        response.put("data",data);
        return response;
    }
}
