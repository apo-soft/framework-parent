package cn.aposoft.framework.httpclient.tuitorial.fundamentals.responsehandler;

import java.io.Serializable;

import com.google.gson.Gson;

public class MyJsonObject implements Serializable {
    private static final long serialVersionUID = 5961313069552176616L;

    private int state;// 状态码

    private String message;// 信息

    private String showMessage;// 显示信息

    private Object data;// 数据

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getShowMessage() {
        return showMessage;
    }

    public void setShowMessage(String showMessage) {
        this.showMessage = showMessage;
    }

    public <T> T getData() {
        @SuppressWarnings("unchecked")
        T _data = (T) data;
        return _data;
    }

    public <T> void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return (new Gson()).toJson(this);
    }

    public static class EventCity implements java.io.Serializable {

        private static final long serialVersionUID = -6452557191454071583L;

        private Integer id;

        private String cityName;

        private String cityCode;

        private Boolean enable;

        public EventCity(Integer id, String cityName, String cityCode, Boolean enable) {
            setId(id);
            setCityName(cityName);
            setCityCode(cityCode);
            setEnable(enable);
        }

        public EventCity() {
            //
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public Boolean getEnable() {
            return enable;
        }

        public void setEnable(Boolean enable) {
            this.enable = enable;
        }

        @Override
        public String toString() {
            return (new Gson()).toJson(this);
        }
    }
}
