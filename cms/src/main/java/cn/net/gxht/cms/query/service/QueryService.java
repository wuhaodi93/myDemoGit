package cn.net.gxht.cms.query.service;

import cn.net.gxht.cms.common.entity.Page;
import cn.net.gxht.cms.query.entity.QueryPhone;
import cn.net.gxht.cms.query.entity.QueryUserInfo;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/17.
 */
public interface QueryService {
    //获取蜜罐数据
    JSONObject getQueryInfo(QueryUserInfo queryUserInfo) throws IOException;

    Map<String, Object> findQueryRecord(Page page, String keyword);

    JSONObject readObjectFile(Integer id) throws IOException, ClassNotFoundException;
    /**获取序列号/IMEI码查询结果
     * @param sn 序列号或IMEI码
     */
    String getSericaAndIEMIInfo(String sn);
    /**
     * 通过序列号/IMEI查询历史记录
     */
    Map<String, Object> findQueryPhone(Page page,String sn);
    JSONObject readPhoneFile(String path);
}
