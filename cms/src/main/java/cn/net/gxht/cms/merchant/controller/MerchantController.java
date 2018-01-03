package cn.net.gxht.cms.merchant.controller;

import cn.net.gxht.cms.common.entity.JsonResult;
import cn.net.gxht.cms.common.entity.Page;
import cn.net.gxht.cms.merchant.service.MerchantService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/11.
 */
@Controller
public class MerchantController {
    @Resource
    MerchantService merchantService;
    /**
     * 获取商家入驻申请的信息
     * @param page 分页
     * @param state 商家入驻的状态
     * @param applyInfoId 入驻信息的id对应app_applyInfo表的id
     * @param keyword 关键字(模糊查询)
     * @param merchantType 商家类型
     * @return
     */
    @RequestMapping("/findMerchantEnterApplyInfo")
    @ResponseBody
    public JsonResult findUserApplyInfo(Page page, Integer state, Integer applyInfoId, String keyword, Integer merchantType) {
        return new JsonResult(merchantService.findMerchantEnterApplyInfo(page, state, applyInfoId, keyword, merchantType));
    }

    /**
     * 更改app商家入驻的状态
     * @param state 状态
     * @param id 商家的id
     * @param failedMessage 驳回时填写的失败信息
     * @return
     */
    @RequestMapping("/appChangeMerchantState")
    @ResponseBody
    public JsonResult appUpdateMerchantApplyState(Integer state, Integer id, String failedMessage) {
        merchantService.appUpdateMerchantApplyState(state, id, failedMessage);
        return new JsonResult();
    }

    /**
     * 获取一周之内商家入驻的数据
     * @return
     */
    @RequestMapping("/findMerchantEnterDataInMonth")
    @ResponseBody
    public JsonResult findAuthDataInMonth() {
        Map<String, Object> map = merchantService.findMerchantEnterData();
        return new JsonResult(map);
    }
}
