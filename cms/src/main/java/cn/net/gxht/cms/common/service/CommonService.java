package cn.net.gxht.cms.common.service;

import cn.net.gxht.cms.common.entity.Page;
import cn.net.gxht.cms.pictures.entity.Picture;
import cn.net.gxht.cms.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/29.
 */
public interface CommonService{
    Map<String,Object> findUnAuthenticationMerchants(Integer state, Page page);
    void updateClientState(Integer state,Integer id);
//    void UpdatePageInfo(String classType, Integer id, Picture picture, MultipartFile multipartFile);
}
