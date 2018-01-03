package cn.net.gxht.cms.pictures.service.impl;

import cn.net.gxht.cms.pictures.dao.PicturesDao;
import cn.net.gxht.cms.pictures.service.PicturesService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 10604 on 2017/8/28.
 */
@Service
@Transactional
    public class PicturesServiceImpl implements PicturesService {
    @Resource
    private PicturesDao picturesDao;

}
