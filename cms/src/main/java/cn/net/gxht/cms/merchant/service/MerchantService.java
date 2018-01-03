package cn.net.gxht.cms.merchant.service;

import cn.net.gxht.cms.common.entity.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/11.
 */

public interface MerchantService {
    void updateMerchantState(Integer state,Integer id);
    Map<String,Object> findMerchantEnterApplyInfo(Page page,Integer state,Integer applyInfoId,String keyword,Integer merchantType);
    void appUpdateMerchantApplyState(Integer state,Integer id,String message);
    Map<String,Object> findMerchantEnterData();
}
