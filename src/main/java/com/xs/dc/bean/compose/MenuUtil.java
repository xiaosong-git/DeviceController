package com.xs.dc.bean.compose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.xs.dc.bean.DcAuthority;

/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年10月25日 下午3:26:26 
* 类说明 
*/
public class MenuUtil {
	
	public static List<DcAuthority> getMenu(List<DcAuthority> list){
        Map<Long,DcAuthority> map = new HashMap<Long,DcAuthority>();
        for(DcAuthority p:list){
            if(p.getAuthorityGrade().equals("0")){
                map.put(p.getId(), p);
            }
        }
        for(DcAuthority p:list){
            if(p.getSuperId()==0){
                continue;
            }
            DcAuthority DcAuthority = map.get(p.getSuperId());
            DcAuthority.getChildren().add(p);
        }
        //将菜单装在list里面了。需要排序
        List<DcAuthority> menus = new ArrayList<DcAuthority>();
        Iterator<DcAuthority> iter = map.values().iterator();
        while(iter.hasNext()){
            menus.add(iter.next());
        }
		/*
		 * Collections.sort(menus); for(DcAuthority sp:menus){
		 * Collections.sort(sp.getChildren()); }
		 */
        return menus;
    }
}
