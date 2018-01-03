package cms;

import cn.net.gxht.cms.common.entity.Page;
import cn.net.gxht.cms.query.dao.QueryDao;
import cn.net.gxht.cms.query.entity.QueryPhone;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-mvc.xml", "classpath*:spring-mybatis.xml"})
public class InsertTest {
    @Resource
    protected ApplicationContext ctx;

    @Test
    public void insertTest() {
        QueryDao queryDao = ctx.getBean("queryDao", QueryDao.class);
        System.out.println(queryDao);
        QueryPhone queryPhone = new QueryPhone();
        Md5Hash md5Hash = new Md5Hash("F2LV689YJCLP");
        queryPhone.setSn(md5Hash.toString());
        queryPhone.setQueryBy("测试");
        String path = "D:" + File.separator + "queryInfo" + File.separator + "20171229" + File.separator + md5Hash.toString() + ".txt";
        queryPhone.setInfoFilePath(path);
        queryDao.insertQueryPhone(queryPhone);
    }

    @Test
    public void selectTest() {
       /* QueryDao queryDao = ctx.getBean("queryDao", QueryDao.class);
         List<QueryPhone> queryPhone = queryDao.findPhoneQueryRecord("F2LV689YJCL1");
        for (QueryPhone qh : queryPhone) {
            System.out.println(qh.toString());
        }*/
    }
    @Test
    public void PageTest(){
        QueryDao queryDao = ctx.getBean("queryDao", QueryDao.class);
        Page page=new Page();
        page.setStartIndex(1);
        page.setPageSize(2);
       List<QueryPhone> list =queryDao.findPhoneQueryRecord(page,"359153075684625");
        System.out.println(list.toString());
    }
}
