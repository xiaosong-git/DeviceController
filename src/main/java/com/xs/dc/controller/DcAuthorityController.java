package com.xs.dc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xs.dc.bean.DcAuthority;
import com.xs.dc.bean.DcRole;
import com.xs.dc.bean.EasyUITree;
import com.xs.dc.bean.compose.Result;
import com.xs.dc.bean.compose.ResultData;
import com.xs.dc.controller.base.BaseController;
import com.xs.dc.service.DcAuthorityService;

/**
 * @author 作者 : xiaojf
 * @Date 创建时间：2019年9月30日 上午10:48:29 类说明
 */
@Controller
@RequestMapping("authority")
public class DcAuthorityController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(DcAuthorityController.class);

	@Autowired
	private DcAuthorityService authorityservice;
	
	@RequestMapping("")
	public String dcAuthority() {
		return "dcAuthority";
	}

	/**
	 * 获取菜单列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("authorityList")
	@ResponseBody
	public Result<Object> authorityList(HttpServletRequest request, HttpServletResponse response) {
		List<DcAuthority> list = authorityservice.getAuthorityList();
		return ResultData.dataResult(list);
	}
	
	@RequestMapping("loadAuthority")
    @ResponseBody
    public List<EasyUITree> loadMenu(@RequestParam(value = "roleId", required = false) Long roleId){
        //findByRoleId(roleid)获取当前角色权限,并为其selected
        List<DcAuthority> chekedList = authorityservice.findByRoleId(roleId);
        //从数据库中查询的保存tree的集合、比如id、父类id、text等等、可自己扩展  
        List<DcAuthority> list = authorityservice.findAll();  
        List<EasyUITree> eList = getEasyUITreeList(list,chekedList);  
        return eList;
    }

    private List<EasyUITree> getEasyUITreeList(List<DcAuthority> list,List<DcAuthority> chekedList) {
        //用于前台显示的tree的属性、比如id、state、text、checked等等  
        List<EasyUITree> eList = new ArrayList<EasyUITree>();  
        Map<Long,EasyUITree> eMap = new HashMap<Long,EasyUITree>();
        if(list.size() != 0){  
            for(DcAuthority p : list){  
                EasyUITree e = new EasyUITree();  
                e.setId(p.getId()); 
                e.setText(p.getAuthorityName());  
                //e.setChecked(true);
                //e.setAttributes(p.getUrl());  为元素追加一个自定义属性
                int count = p.getChildren().size(); 
                if(count > 0){  
                    for(DcAuthority pchild:p.getChildren()){
                        EasyUITree echild = new EasyUITree();
                        echild.setId(pchild.getId());
                        echild.setText(pchild.getAuthorityName());
                        //echild.setChecked(true);
                        //echild.setAttributes(pchild.getUrl());
                        e.getChildren().add(echild);
                    }
                }  
                eMap.put(e.getId(), e);
            } 
            for(DcAuthority p : chekedList){  
                EasyUITree e = new EasyUITree();  
                e.setId(p.getId()); 
                e.setText(p.getAuthorityName());  
                e.setChecked(true);
                int count = p.getChildren().size(); 
                if(count > 0){  
                    for(DcAuthority pchild:p.getChildren()){
                        EasyUITree echild = new EasyUITree();
                        echild.setId(pchild.getId());
                        echild.setText(pchild.getAuthorityName());
                        echild.setChecked(true);
                        e.getChildren().add(echild);
                    }
                }  
                eMap.put(e.getId(), e);
            } 
        }
        eList.addAll(eMap.values());
        return eList;
    }
    
    @RequestMapping("insertAuthority")
	@ResponseBody
	public Map<String, String> insertAuthority(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute DcAuthority authority){
		int result = authorityservice.insertAuthority(authority);
		Map<String, String> map = new HashMap<String, String>();
		if (result >= 1) {
			map.put("result", "添加成功");
			return map;
		} else {
			map.put("result", "添加失败");
			return map;
		}
		
	}
	
	@RequestMapping("deleteAuthority")
	@ResponseBody
	public Map<String, String> deleteAuthority(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = false) long id){
		int result = authorityservice.deleteAuthority(id);
		Map<String, String> map = new HashMap<String, String>();
		if (result >= 1) {
			map.put("result", "删除成功");
			return map;
		} else {
			map.put("result", "删除失败");
			return map;
		}
		
	}
}
