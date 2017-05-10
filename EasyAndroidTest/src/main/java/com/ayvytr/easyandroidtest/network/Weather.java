package com.ayvytr.easyandroidtest.network;

import java.util.List;

/**
 * Created by Do on 2017/5/10.
 */

public class Weather
{
    /**
     * desc : OK
     * status : 1000
     * data : {"wendu":"25","ganmao":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。","forecast":[{"fengxiang":"南风","fengli":"3-4级","high":"高温 30℃","type":"晴","low":"低温 15℃","date":"10日星期三"},{"fengxiang":"无持续风向","fengli":"5-6级","high":"高温 31℃","type":"晴","low":"低温 13℃","date":"11日星期四"},{"fengxiang":"西南风","fengli":"3-4级","high":"高温 30℃","type":"多云","low":"低温 15℃","date":"12日星期五"},{"fengxiang":"北风","fengli":"3-4级","high":"高温 28℃","type":"多云","low":"低温 16℃","date":"13日星期六"},{"fengxiang":"北风","fengli":"3-4级","high":"高温 27℃","type":"多云","low":"低温 15℃","date":"14日星期天"}],"yesterday":{"fl":"3-4级","fx":"南风","high":"高温 29℃","type":"多云","low":"低温 15℃","date":"9日星期二"},"aqi":"76","city":"北京"}
     */

    private String desc;
    private int status;
    private DataBean data;

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public DataBean getData()
    {
        return data;
    }

    public void setData(DataBean data)
    {
        this.data = data;
    }

    public static class DataBean
    {
        /**
         * wendu : 25
         * ganmao : 各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。
         * forecast : [{"fengxiang":"南风","fengli":"3-4级","high":"高温 30℃","type":"晴","low":"低温 15℃","date":"10日星期三"},{"fengxiang":"无持续风向","fengli":"5-6级","high":"高温 31℃","type":"晴","low":"低温 13℃","date":"11日星期四"},{"fengxiang":"西南风","fengli":"3-4级","high":"高温 30℃","type":"多云","low":"低温 15℃","date":"12日星期五"},{"fengxiang":"北风","fengli":"3-4级","high":"高温 28℃","type":"多云","low":"低温 16℃","date":"13日星期六"},{"fengxiang":"北风","fengli":"3-4级","high":"高温 27℃","type":"多云","low":"低温 15℃","date":"14日星期天"}]
         * yesterday : {"fl":"3-4级","fx":"南风","high":"高温 29℃","type":"多云","low":"低温 15℃","date":"9日星期二"}
         * aqi : 76
         * city : 北京
         */

        private String wendu;
        private String ganmao;
        private YesterdayBean yesterday;
        private String aqi;
        private String city;
        private List<ForecastBean> forecast;

        public String getWendu()
        {
            return wendu;
        }

        public void setWendu(String wendu)
        {
            this.wendu = wendu;
        }

        public String getGanmao()
        {
            return ganmao;
        }

        public void setGanmao(String ganmao)
        {
            this.ganmao = ganmao;
        }

        public YesterdayBean getYesterday()
        {
            return yesterday;
        }

        public void setYesterday(YesterdayBean yesterday)
        {
            this.yesterday = yesterday;
        }

        public String getAqi()
        {
            return aqi;
        }

        public void setAqi(String aqi)
        {
            this.aqi = aqi;
        }

        public String getCity()
        {
            return city;
        }

        public void setCity(String city)
        {
            this.city = city;
        }

        public List<ForecastBean> getForecast()
        {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast)
        {
            this.forecast = forecast;
        }

        public static class YesterdayBean
        {
            /**
             * fl : 3-4级
             * fx : 南风
             * high : 高温 29℃
             * type : 多云
             * low : 低温 15℃
             * date : 9日星期二
             */

            private String fl;
            private String fx;
            private String high;
            private String type;
            private String low;
            private String date;

            public String getFl()
            {
                return fl;
            }

            public void setFl(String fl)
            {
                this.fl = fl;
            }

            public String getFx()
            {
                return fx;
            }

            public void setFx(String fx)
            {
                this.fx = fx;
            }

            public String getHigh()
            {
                return high;
            }

            public void setHigh(String high)
            {
                this.high = high;
            }

            public String getType()
            {
                return type;
            }

            public void setType(String type)
            {
                this.type = type;
            }

            public String getLow()
            {
                return low;
            }

            public void setLow(String low)
            {
                this.low = low;
            }

            public String getDate()
            {
                return date;
            }

            public void setDate(String date)
            {
                this.date = date;
            }
        }

        public static class ForecastBean
        {
            /**
             * fengxiang : 南风
             * fengli : 3-4级
             * high : 高温 30℃
             * type : 晴
             * low : 低温 15℃
             * date : 10日星期三
             */

            private String fengxiang;
            private String fengli;
            private String high;
            private String type;
            private String low;
            private String date;

            public String getFengxiang()
            {
                return fengxiang;
            }

            public void setFengxiang(String fengxiang)
            {
                this.fengxiang = fengxiang;
            }

            public String getFengli()
            {
                return fengli;
            }

            public void setFengli(String fengli)
            {
                this.fengli = fengli;
            }

            public String getHigh()
            {
                return high;
            }

            public void setHigh(String high)
            {
                this.high = high;
            }

            public String getType()
            {
                return type;
            }

            public void setType(String type)
            {
                this.type = type;
            }

            public String getLow()
            {
                return low;
            }

            public void setLow(String low)
            {
                this.low = low;
            }

            public String getDate()
            {
                return date;
            }

            public void setDate(String date)
            {
                this.date = date;
            }
        }
    }
}
