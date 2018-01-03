package cn.net.gxht.cms.common.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/29.
 */
public interface CommonDao {
    List<Map<String, Object>> findUnAuthenticationMerchants(Integer state);

    String[] findPhoneByClientId(Integer id);

    Integer updateClientState(@Param(value = "state") Integer state, @Param(value = "id") Integer id);

    String getUrl();
}
