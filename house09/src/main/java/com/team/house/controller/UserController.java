package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.entity.Users;
import com.team.house.service.TypeService;
import com.team.house.service.UserService;
import com.team.house.util.PageUtil;
import com.team.house.util.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;



@Controller // @Controller+ @ResponseBody
@RequestMapping("/admin/")  //指定请求前缀
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("getUserData")   //condition 包含:page ,rows 、查询条件
    public Map<String,Object> getUserData(UserCondition condition){
        //调用业务区域数据
        PageInfo<Users> pageInfo=userService.getUserByPage(condition);
        //封装返回数据
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

}
