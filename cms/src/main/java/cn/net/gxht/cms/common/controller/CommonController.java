package cn.net.gxht.cms.common.controller;

import cn.net.gxht.cms.common.dao.CommonDao;
import cn.net.gxht.cms.common.entity.JsonResult;
import cn.net.gxht.cms.common.entity.Page;
import cn.net.gxht.cms.common.service.CommonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/29.
 */
@Controller
public class CommonController {
    @Resource
    private CommonService commonService;
    @Resource
    private CommonDao commonDao;

    /**
     * 获取首页
     *
     * @return
     */
    @RequestMapping("/findIndexPage")
    public String findIndexPage() {
        return "/index";
    }

    /**
     * 用同一个模态框展现用户和商家的数据
     *
     * @param target 目标对像的的类型是用户还是商家
     * @return
     */
    @RequestMapping("/getTargetInfoPage")
    public String getTargetInfoPage(String target) {
        if ("user".equals(target)) {
            return "/userApplyInfo/userEdit";
        } else if ("merchant".equals(target)) {
            return "/userApplyInfo/merchantEdit";
        }
        return null;
    }

    /**
     * 寻找所有用户(可以根据
     * 状态来返回)
     *
     * @param state 用户状态
     * @param page  分页
     * @return
     */
    @RequestMapping("/findUnAuthenticationClients")
    @ResponseBody
    public JsonResult findUnAuthenticationClients(Integer state, Page page) {
        Map<String, Object> clients = commonService.findUnAuthenticationMerchants(state, page);
        return new JsonResult(clients);
    }

    /**
     * 此接口已废弃
     *
     * @param state 状态
     * @param id
     * @return
     */
    @RequestMapping("/changeClientState")
    @ResponseBody
    public JsonResult changeClientState(Integer state, Integer id) {
        commonService.updateClientState(state, id);
        return new JsonResult();
    }

    /**
     * 返回首页顶部html
     *
     * @return
     */
    @RequestMapping("/findTopJsp")
    public String findTopJsp() {
        return "/top";
    }

    /**
     * 返回首页左边的html
     *
     * @return
     */
    @RequestMapping("/findLeftJsp")
    public String findLeftJsp() {
        return "/left";
    }
}
