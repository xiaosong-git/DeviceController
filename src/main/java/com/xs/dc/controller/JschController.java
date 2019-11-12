package com.xs.dc.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.Session;
import com.xs.dc.JSch.ShellUtils;
import com.xs.dc.controller.base.BaseController;

/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年10月21日 下午5:22:59 
* 类说明 
*/
@Controller
@RequestMapping("/jsch")
public class JschController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(JschController.class);
	
	    public static ChannelShell channelShell;
	
	@RequestMapping("")
	public String jsch(HttpServletRequest request, HttpServletResponse response) {
        try{
        	//开启一个session 链接 自定义的getSession 方法
        	Session session = ShellUtils.getSession("2a3ad45592813896.natapp.cc", "root", "xsax0598", 8500);
//        链接session  设置session 长链接
            channelShell = ShellUtils.ChannelShell(session);
            request.setAttribute("result", "success");
        }catch (Exception e){
        	request.setAttribute("result", "fail");
        }
		return "jsch";
	}
	
	@RequestMapping("/linux")
	@ResponseBody
    public List<String> getLinux(@RequestParam(value = "cmd", required = false) String cmd) throws UnsupportedEncodingException {
//        控制台 打印 模拟linux 黑窗口
        String res="";
//        判断 输入的命令  如果是 这三个 展示命令 会将 渲染的颜色取消  否则 变成 字符串 会出现乱码
        if(cmd.equals("ls") || cmd.equals("ll") ||cmd.equals("vi")){
            res = ShellUtils.executeNewFlow(channelShell, cmd +" --color=never"+ " \n");
        }else {
//            否则就正常输出命令
             res = ShellUtils.executeNewFlow(channelShell, cmd + " \n");

        }
//        遍历执行结果 按照 \r\n 分隔
        String[] str ={} ;
        str = res.split("\r\n");
        List<String> list = new ArrayList();
        for (String st:str){
            list.add(st);
        }
//            将结果返回给前台页面
            return  list;

        }

}
