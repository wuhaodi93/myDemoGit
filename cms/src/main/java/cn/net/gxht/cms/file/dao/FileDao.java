package cn.net.gxht.cms.file.dao;

import cn.net.gxht.cms.file.entity.App;
import cn.net.gxht.cms.file.entity.FileEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by Administrator on 2017/10/13.
 */
public interface FileDao {
    Integer uploadFile(@Param(value = "clientId") Integer clientId, @Param(value = "fileName") String fileName);
    FileEntity downloadFile(Integer clientId);
    Integer insertAppFile(App app);
    String findFileExists(@Param(value="digest") String digest);
    Integer findVersionExists(@Param(value = "version")String version);
    Map<String,Object> findNewestFile();
    Integer updateFileDownloadTimes(@Param(value="id") Integer id);
    App [] findAppVersionInfo();
}
