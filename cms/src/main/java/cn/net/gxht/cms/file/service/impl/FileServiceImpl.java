package cn.net.gxht.cms.file.service.impl;

import cn.net.gxht.cms.common.entity.Page;
import cn.net.gxht.cms.common.entity.PageUtil;
import cn.net.gxht.cms.file.dao.FileDao;
import cn.net.gxht.cms.file.entity.App;
import cn.net.gxht.cms.file.entity.FileEntity;
import cn.net.gxht.cms.file.entity.FileUtil;
import cn.net.gxht.cms.file.entity.ZipUtil;
import cn.net.gxht.cms.file.service.FileService;
import cn.net.gxht.cms.user.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import static org.apache.logging.log4j.web.WebLoggerContextUtils.getServletContext;

/**
 * Created by Administrator on 2017/10/13.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FileServiceImpl implements FileService {
    @Resource
    private FileDao fileDao;
    private Logger logger = LogManager.getLogger(FileServiceImpl.class);

    @Override
    public void uploadFile(String realPath, MultipartFile[] mFiles, Integer clientId) {
        for (MultipartFile mFile : mFiles) {
            uploadFileUtil(realPath, mFile, clientId);
        }
    }

    public void uploadFileUtil(String realPath, MultipartFile mFile, Integer clientId) {
        String oFileName = mFile.getOriginalFilename();
        logger.debug(oFileName + "开始文件上传...");
        System.out.println(oFileName);
        String newFileName = realPath + oFileName;
        System.out.println("newFileName:" + newFileName);
        File file = new File(newFileName);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            try {
                parentFile.mkdir();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
        try {
            System.out.println(mFile + "," + file);
            byte[] fileBytes = mFile.getBytes();
            mFile.transferTo(file);
        } catch (Exception e) {
            logger.error("文件上传失败" + e);
            throw new RuntimeException("图片上傳失敗");
        }
        System.out.println("oFileName=" + oFileName);
        fileDao.uploadFile(clientId, oFileName);
        logger.debug("文件上传成功");
    }

    @Override
    public void downLoadFileImpl(HttpServletRequest request, HttpServletResponse response, Integer clientId) throws IOException {
        logger.debug("开始文件下载...");
        FileEntity fileEntity = fileDao.downloadFile(clientId);
        if (fileEntity == null) {
            throw new RuntimeException("该用户没有上传相关资料");
        }
        String zipName = "clientAuthInfo" + fileEntity.getId() + ".zip";
        List<String> paths = fileEntity.getPath();
        String realPath = request.getServletContext().getRealPath("/") + "clientAuthInfo" + File.separator + "clientId" + fileEntity.getId() + File.separator;
        downLoadFileUtil(response, zipName, paths, realPath);
        System.out.println("request = [" + request + "], response = [" + response + "], clientId = [" + clientId + "]");
        logger.debug("文件下载成功...");
    }

    public void downLoadFileUtil(HttpServletResponse response, String zipName, List<String> paths, String realPath) throws IOException {
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=" + zipName);
        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
        try {
            for (String path : paths) {
                ZipUtil.doCompress(realPath + path, out);
                response.flushBuffer();
            }
        } catch (Exception e) {
            logger.debug("文件下载失败");
            out.close();
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    @Override
    public void downloadAPP(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer fileId = null;
        try {
            /**
             * 此处应该从数据库查找文件名称，设置为下载最新版本的文件,根据上传的时间来查找文件是否是最新的文件,
             * 返回数据的id,文件名称
             */
            /**
             * 下载完成应该更新相应版本的下载数量
             * 更具id来更新数据
             */
            Map<String, Object> fileMap = fileDao.findNewestFile();
            String fileName = (String) fileMap.get("name");
            fileId = (Integer) fileMap.get("id");
            String version = (String) fileMap.get("version");
            // path是指欲下载的文件的路径。
            String path = request.getServletContext().getRealPath("/") + "file" + File.separator + "app" + File.separator + version + File.separator + fileName;
            File file = new File(path);
            // 取得文件的后缀名。
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            /**
             * 解决文件中中文乱码的问题
             */
            fileName = URLEncoder.encode(fileName, "UTF-8");
            if (fileName.length() > 150) {
                String guessCharset = "gb2312";
                fileName = new String(fileName.getBytes(guessCharset), "ISO8859-1");
            }
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            //必须要设置内容的长度,否则在文件上传完后浏览器自动关掉下载,导致服务器报错
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //下载完成,根据id来更新下载量
        Integer updateState = fileDao.updateFileDownloadTimes(fileId);
        if (updateState < 1) {
            throw new RuntimeException("系统异常");
        }
    }

    @Override
    public void updateAppVersion(MultipartFile multipartFile, App app, HttpServletRequest request) throws IOException {
        /**
         * 找出当前操作人,版本号,备注,上传文件应该根据md5生成的摘要控制用户不能上传已经上传过的文件
         * 若已经上传过了，则返回之前上传的版本号
         */
        byte[] uploadFileBytes = multipartFile.getBytes();
        Md5Hash md5Hash = new Md5Hash(uploadFileBytes);
        String fileDigest = md5Hash.toString();
        String existsVersion = fileDao.findFileExists(fileDigest);
        if (existsVersion != null) {
            throw new RuntimeException("抱歉,相同的文件已经上传过了,之前上传的版本号为:" + existsVersion);
        }
        /**
         * 看用户版本号是否重复
         */
        Integer versionState = fileDao.findVersionExists(app.getVersion());
        if (versionState >= 1) {
            throw new RuntimeException("抱歉,版本号重复了");
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user = (User) session.getAttribute("currentUser");
        String userName = user.getName();
        String realPath = request.getServletContext().getRealPath("/") + "file" + File.separator + "app" + File.separator + app.getVersion() + File.separator;
        String fileName = multipartFile.getOriginalFilename();
//        fileName = new String(fileName.getBytes("iso8859-1"), "utf-8");
        FileUtil.uploadFileUtil(realPath, multipartFile);
        /**
         * 将当前上传使用 文件名 文件版本 文件备注这些信息存入数据库
         */
        app.setName(fileName);
        app.setUploadUser(userName);
        app.setDigest(fileDigest);
        Integer i = fileDao.insertAppFile(app);
        if (i <= 0) {
            throw new RuntimeException("由于未知原因文件上传失败，请联系技术人员解决");
        }
    }

    @Override
    public Map<String, Object> findAppVersionInfo(Page page) {
        App[] apps = fileDao.findAppVersionInfo();
        Map<String, Object> pageRsultMap = PageUtil.byPage(apps, page);
        return pageRsultMap;
    }
}