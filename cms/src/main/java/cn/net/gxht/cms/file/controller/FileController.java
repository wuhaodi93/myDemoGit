package cn.net.gxht.cms.file.controller;

import cn.net.gxht.cms.common.entity.JsonResult;
import cn.net.gxht.cms.common.entity.Page;
import cn.net.gxht.cms.file.entity.App;
import cn.net.gxht.cms.file.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/13.
 */
@Controller
public class FileController {
    @Resource
    private FileService fileService;

    /**
     * 下载app
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping("/downloadApp")
    @ResponseBody
    public JsonResult testDownloadApp(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        fileService.downloadAPP(request, response);
        return new JsonResult();
    }

    /**
     * 更新app版本
     *
     * @param multipartFile
     * @param app
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/updateAppVersion")
    @ResponseBody
    public JsonResult updateAppVersion(MultipartFile multipartFile, App app, HttpServletRequest request) throws IOException {
        fileService.updateAppVersion(multipartFile, app, request);
        return new JsonResult();
    }

    /**
     * 获取app版本的历史信息
     *
     * @param page
     * @return
     */
    @RequestMapping("/getAppVersionInfo")
    @ResponseBody
    public JsonResult getAppVersionInfo(Page page) {
        Map<String, Object> map = fileService.findAppVersionInfo(page);
        return new JsonResult(map);
    }
}
