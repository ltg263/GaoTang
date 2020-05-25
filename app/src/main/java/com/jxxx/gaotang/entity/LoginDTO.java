package com.jxxx.gaotang.entity;

public class LoginDTO {

    /**
     * data : {"id":7,"loginPswd":"9C9C4509B8C32B45574F0D9D656CF98A","mobile":"13065863122","saltValue":"ADIL8ll4wf","status":1,"tokenId":"bf9f2275-aede-4cab-a64f-138c7f34d99d"}
     * status : 0
     */

    private DataBean data;
    private String status;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * id : 7
         * loginPswd : 9C9C4509B8C32B45574F0D9D656CF98A
         * mobile : 13065863122
         * saltValue : ADIL8ll4wf
         * status : 1
         * tokenId : bf9f2275-aede-4cab-a64f-138c7f34d99d
         */

        private String id;
        private String loginPswd;
        private String mobile;
        private String saltValue;
        private String status;
        private String tokenId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLoginPswd() {
            return loginPswd;
        }

        public void setLoginPswd(String loginPswd) {
            this.loginPswd = loginPswd;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getSaltValue() {
            return saltValue;
        }

        public void setSaltValue(String saltValue) {
            this.saltValue = saltValue;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTokenId() {
            return tokenId;
        }

        public void setTokenId(String tokenId) {
            this.tokenId = tokenId;
        }
    }
}
