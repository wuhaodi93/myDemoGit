package cn.net.gxht.cms.user.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/11/14.
 */
public class AuthDataEntity {
    List<Date> date;
    List<Integer> times;

    public List<Date> getDate() {
        return date;
    }

    public void setDate(List<Date> date) {
        this.date = date;
    }

    public List<Integer> getTimes() {
        return times;
    }

    public void setTimes(List<Integer> times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return "AuthDataEntity{" +
                "date=" + date +
                ", times=" + times +
                '}';
    }
}
