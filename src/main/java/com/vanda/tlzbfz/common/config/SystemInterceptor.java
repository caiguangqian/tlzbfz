package com.vanda.tlzbfz.common.config;

import com.google.gson.Gson;
import com.vanda.tlzbfz.bean.CodeEnum;
import com.vanda.tlzbfz.bean.ResultData;
import com.vanda.tlzbfz.common.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

//@Component
public class SystemInterceptor  implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

        /**
         * 预处理回调方法，实现处理器的预处理
         * 返回值：true表示继续流程；false表示流程中断，不会继续调用其他的拦截器或处理器
         */
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            // 处理OPTIONS请求(预检请求)，具体看参阅“参考链接>>>http的协议的跨域cors 和 options请求的一些理解”
           /* response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");
            response.setHeader("Access-Control-Max-Age", "86400");
            response.setHeader("Access-Control-Allow-Headers", "*");
            response.setHeader("Content-Type", "text/html;charset=utf-8");
            if (request.getMethod().equals("OPTIONS")) {
                response.setStatus(HttpServletResponse.SC_OK);
                return false;
            }*/
            // token 信息校验
            String userToken = request.getHeader("accept_token");
            if(userToken != null && !"".equals(userToken)) {
                if(redisUtil.hasKey(userToken)){
                    redisUtil.expire(userToken, 1800);
                    return true;
                }else{
                    ResultData resultData=new ResultData(CodeEnum.EXTOKEN.getCm(),null);
                    String resultjson=new Gson().toJson(resultData);
                    response.setStatus(401);
                    PrintWriter out = response.getWriter();
                    out.println(resultjson);
                    out.close();
                    return false;
                }
            }else{
                ResultData resultData=new ResultData(CodeEnum.EXTOKEN.getCm(),null);
                String resultjson=new Gson().toJson(resultData);
                PrintWriter out = response.getWriter();
                out.println(resultjson);
                out.close();
                return false;
            }

        }

        /**
         * 后处理回调方法，实现处理器（controller）的后处理，但在渲染视图之前
         * 此时我们可以通过modelAndView对模型数据进行处理或对视图进行处理
         */
        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        }

        /**
         * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，
         * 如性能监控中我们可以在此记录结束时间并输出消耗时间，
         * 还可以进行一些资源清理，类似于try-catch-finally中的finally，
         * 但仅调用处理器执行链中
         */
        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        }

    }

