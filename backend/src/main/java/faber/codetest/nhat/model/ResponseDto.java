package faber.codetest.nhat.model;

import org.apache.commons.lang3.StringUtils;

public class ResponseDto {
    private Boolean success;

    private Object data;

    private String message = StringUtils.EMPTY;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
