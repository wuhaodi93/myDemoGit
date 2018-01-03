package cn.net.gxht.cms.pictures.controller;

import cn.net.gxht.cms.pictures.service.PicturesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/28.
 */
@Controller
public class PicturesController {
    @Resource
    private PicturesService picturesService;
}
