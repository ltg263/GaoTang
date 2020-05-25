package com.jxxx.gaotang.entity;

public class UpFilesDTO {

    /**
     * data : https://y.nbyangliao.com/blackcat/upload/43F62DF2850987A2722304C23E66947A.jpg
     * status : 0
     */

    private String url;
    private String status;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
