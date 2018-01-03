package cn.net.gxht.cms.merchant.service.impl;

import cn.net.gxht.cms.common.entity.Page;
import cn.net.gxht.cms.common.entity.PageUtil;
import cn.net.gxht.cms.merchant.dao.MerchantDao;
import cn.net.gxht.cms.merchant.entity.ApplyInfo;
import cn.net.gxht.cms.merchant.service.MerchantService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static cn.net.gxht.cms.common.entity.PageUtil.byPage;

/**
 * Created by Administrator on 2017/9/11.
 */
@Service
@Transactional
public class MerchantServiceImpl implements MerchantService {
    @Resource
    private MerchantDao merchantDao;

    @Override
    public void updateMerchantState(Integer state, Integer id) {
        Integer status = merchantDao.updateMerchantState(state, id);
        if (status < 1) {
            throw new RuntimeException("修改用户状态失败");
        }
    }

    @Override
    public Map<String, Object> findMerchantEnterApplyInfo(Page page, Integer state, Integer applyInfoId, String keyword, Integer merchantType) {
        page.setPageSize(5);
        ApplyInfo[] applyInfos = merchantDao.findMerchantEnterApplyInfo(state, applyInfoId, keyword, merchantType);
        Map<String, Object> applyInfoMaps = PageUtil.byPage(applyInfos, page);
        return applyInfoMaps;
    }

    @Override
    public void appUpdateMerchantApplyState(Integer state, Integer id, String message) {
        Integer status = merchantDao.appUpdateMerchantApplyState(state, id);
        if (status < 1) {
            throw new RuntimeException("修改用户状态失败");
        }
        if (state == 3) {
            Integer updateState = merchantDao.appUpdateMerchantApplyFailedReason(message, id);
            if (updateState < 1) {
                throw new RuntimeException("修改用户状态失败");
            }
        }
    }

    @Override
    public Map<String, Object> findMerchantEnterData() {
        String lineName = "入驻数量";
        List<Map<String, Object>> list = merchantDao.findMerchantEnterData();
        int size = list.size();
        Long[] times = new Long[size];
        String[] dates = new String[size];
        int i = 0;
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, Object> map : list) {
            times[i] = (Long) map.get("times");
            dates[i] = sim.format((Date) map.get("date"));
            i++;
        }
        Map<String, Object> resultMap = new HashedMap();
        JSONObject jsonData = new JSONObject();
        JSONArray dataArray = new JSONArray();
        jsonData.put("name", lineName);
        jsonData.put("data", times);
        dataArray.add(jsonData);
        resultMap.put("categories", dates);
        resultMap.put("series", dataArray);
        return resultMap;
    }
}
