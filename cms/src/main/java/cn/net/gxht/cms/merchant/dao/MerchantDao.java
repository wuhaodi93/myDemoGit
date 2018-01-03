package cn.net.gxht.cms.merchant.dao;

import cn.net.gxht.cms.common.entity.Page;
import cn.net.gxht.cms.merchant.entity.ApplyInfo;
import cn.net.gxht.cms.query.entity.QueryUserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/11.
 */
public interface MerchantDao {
    Integer updateMerchantState(@Param(value = "state") Integer state, @Param(value = "id") Integer id);
    ApplyInfo [] findMerchantEnterApplyInfo(@Param(value = "state") Integer state,@Param(value = "applyInfoId") Integer applyInfoId,@Param(value = "keyword")String keyword,@Param(value = "merchantType")Integer merchantType);
    Integer appUpdateMerchantApplyState(@Param(value = "state") Integer state, @Param(value = "id") Integer id);
    Integer  appUpdateMerchantApplyFailedReason(@Param(value="message") String message,@Param(value="id") Integer id);
    List<Map<String,Object>> findMerchantEnterData();


}
