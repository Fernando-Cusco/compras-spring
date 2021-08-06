package ec.edu.ups.compras.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.research.ws.wadl.Include;

import java.util.Date;

@JsonInclude(content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiMessage {
    private String message;
    private int code;
    private boolean status;

    public ApiMessage() {
    }

    public ApiMessage(String message, int code, boolean status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ApiMessage{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", status=" + status +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
