package cn.net.gxht.cms.common.service.impl;

import cn.net.gxht.cms.common.dao.CommonDao;
import cn.net.gxht.cms.common.entity.Page;
import cn.net.gxht.cms.common.service.CommonService;
import cn.net.gxht.cms.pictures.entity.Picture;
import cn.net.gxht.cms.user.entity.User;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/31.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CommonServiceImpl implements CommonService {
    @Resource
    private CommonDao commonDao;

    public Map<String, Object> findUnAuthenticationMerchants(Integer state, Page page) {
        List<Map<String, Object>> clients = commonDao.findUnAuthenticationMerchants(state);
        System.out.println(clients);
        String[] phones = null;
        for (Map<String, Object> map : clients) {
            Integer clientId = (Integer) map.get("id");
            phones = commonDao.findPhoneByClientId(clientId);
            phones = (String[]) ArrayUtils.removeElement(phones, map.get("phone"));
            String phone = null;
            System.out.println(phones);
            for (int i = 0; i < phones.length; i++) {
                if (i != 0) {
                    phone += ",";
                }
                if (i == 0) {
                    phone = phones[0];
                } else {
                    phone += phones[i];
                }
            }
            if (phone == null) {
                phone = "";
            }
            map.put("OtherPhone", phone);
        }
        System.out.println(clients);
        List<Map<String, Object>> clientsByPage = new ArrayList<Map<String, Object>>();
        page.setAmount(clients.size());
        /**
         * 获得总数之后就可以获得总页数
         */
        page.setAllPage();
        /**
         * 获得起始条目
         */
        page.setStartIndex(page.getStartIndex());
        /**
         * 获得结束条目
         */
        page.setEndIndex(page.getEndIndex());
        System.out.println(page);
        int index = 0;
        for (Map<String, Object> map : clients) {
            if (index >= page.getStartIndex() && index <= page.getEndIndex()) {
                clientsByPage.add(map);
            }
            index++;
        }
        Map<String, Object> map = new HashedMap();
        map.put("pageObject", page);
        map.put("pageInfo", clientsByPage);
        return map;
    }

    public void updateClientState(Integer state, Integer id) {
        Integer status = commonDao.updateClientState(state, id);
        if (status < 1) {
            throw new RuntimeException("修改用户状态失败");
        }
    }
}
