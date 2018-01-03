package cn.net.gxht.cms.file.service;

import cn.net.gxht.cms.common.entity.Page;
import cn.net.gxht.cms.file.entity.App;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/13.
 */
public interface FileService {
    void uploadFile(String realPath,MultipartFile [] mFile,Integer clientId);
    void downLoadFileImpl(HttpServletRequest request, HttpServletResponse response, Integer clientId) throws IOException;
    void downloadAPP(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
    void updateAppVersion(MultipartFile multipartFile, App app, HttpServletRequest request) throws IOException;
    Map<String,Object> findAppVersionInfo(Page page);
}
