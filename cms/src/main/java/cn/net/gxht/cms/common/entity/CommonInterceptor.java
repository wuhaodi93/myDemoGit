package cn.net.gxht.cms.common.entity;

import cn.net.gxht.cms.common.dao.CommonDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Administrator on 2017/9/19.
 */

/**
 * 这个类的作用是检查session里面是否有token的记录
 * 没有token则从请求对象取出token,然后从数据库中查询这个token的相关信息再将这个token放入session，
 * 以及从数据库查询的信息放入token
 */
@Component
public class CommonInterceptor implements HandlerInterceptor {
    @Resource
    CommonDao commonDao;
    Logger logger = LogManager.getLogger(CommonInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
