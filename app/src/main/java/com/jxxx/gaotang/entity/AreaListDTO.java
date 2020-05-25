package com.jxxx.gaotang.entity;

import java.util.List;

public class AreaListDTO {

    /**
     * ID : 120000
     * n : 天津市
     * c : [{"ID":"120100","n":"天津市","d":[{"ID":"120101","n":"和平区"},{"ID":"120102","n":"河东区"},{"ID":"120103","n":"河西区"},{"ID":"120104","n":"南开区"},{"ID":"120105","n":"河北区"},{"ID":"120106","n":"红桥区"},{"ID":"120110","n":"东丽区"},{"ID":"120111","n":"西青区"},{"ID":"120112","n":"津南区"},{"ID":"120113","n":"北辰区"},{"ID":"120114","n":"武清区"},{"ID":"120115","n":"宝坻区"},{"ID":"120116","n":"滨海新区"},{"ID":"120221","n":"宁河县"},{"ID":"120223","n":"静海县"},{"ID":"120225","n":"蓟县"}]}]
     */

    private String ID;
    private String n;
    private List<CBean> c;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public List<CBean> getC() {
        return c;
    }

    public void setC(List<CBean> c) {
        this.c = c;
    }

    public static class CBean {
        /**
         * ID : 120100
         * n : 天津市
         * d : [{"ID":"120101","n":"和平区"},{"ID":"120102","n":"河东区"},{"ID":"120103","n":"河西区"},{"ID":"120104","n":"南开区"},{"ID":"120105","n":"河北区"},{"ID":"120106","n":"红桥区"},{"ID":"120110","n":"东丽区"},{"ID":"120111","n":"西青区"},{"ID":"120112","n":"津南区"},{"ID":"120113","n":"北辰区"},{"ID":"120114","n":"武清区"},{"ID":"120115","n":"宝坻区"},{"ID":"120116","n":"滨海新区"},{"ID":"120221","n":"宁河县"},{"ID":"120223","n":"静海县"},{"ID":"120225","n":"蓟县"}]
         */

        private String ID;
        private String n;
        private List<DBean> d;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }

        public List<DBean> getD() {
            return d;
        }

        public void setD(List<DBean> d) {
            this.d = d;
        }

        public static class DBean {
            /**
             * ID : 120101
             * n : 和平区
             */

            private String ID;
            private String n;

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getN() {
                return n;
            }

            public void setN(String n) {
                this.n = n;
            }
        }
    }
}
