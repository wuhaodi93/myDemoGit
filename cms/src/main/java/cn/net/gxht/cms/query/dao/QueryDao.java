package cn.net.gxht.cms.query.dao;

import cn.net.gxht.cms.common.entity.Page;
import cn.net.gxht.cms.query.entity.QueryPhone;
import cn.net.gxht.cms.query.entity.QueryUserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * Created by Administrator on 2017/11/17.
 */
public interface QueryDao {
    @Insert("insert into cms_queryUserInfo (name,phone,cardNo,qq,email,createTime,queryBy,InfoFilePath) values (#{queryUserInfo.name},#{queryUserInfo.phone},#{queryUserInfo.cardNo},#{queryUserInfo.query_qq},#{queryUserInfo.query_email},now(),#{queryBy},#{queryUserInfo.infoFilePath})")
    Integer insertQueryInfo(@Param(value = "queryUserInfo") QueryUserInfo queryUserInfo, @Param(value = "queryBy") String queryBy);
   //插入序列号查询记录
    @Insert("insert into cms_queryPhone (sn,createTime,queryBy,infoFilePath) values(#{queryPhone.sn},now(),#{queryPhone.queryBy},#{queryPhone.infoFilePath})")
    Integer insertQueryPhone(@Param(value="queryPhone")QueryPhone queryPhone);
    QueryUserInfo [] findUserQueryRecord(@Param(value = "page")Page page,@Param(value = "id")Integer id,@Param(value = "name") String name,@Param(value="phone")String phone,@Param(value = "idCard")String idCard);
    //查询序列号记录
    List<QueryPhone> findPhoneQueryRecord(@Param(value = "page")Page page,@Param(value ="sn")String sn);
    Integer getAmount();
    //获取序列号记录总数
    Integer getPhoneAmount(@Param(value ="sn")String sn);
}
