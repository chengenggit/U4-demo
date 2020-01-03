package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.service.HouseService;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController("houseController2")
@RequestMapping("/admin/")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @RequestMapping("getHouse")
    public Map<String,Object> getHouse(PageUtil pageUtil){
        //调用业务获取数据
      PageInfo<House> pageInfo=houseService.getBackAllHouse(pageUtil);

      //封装返回数据
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }


    //更新出租房的审核状诚
    @RequestMapping("updatePassState")
    public Map<String,Object> updatePassState(String id,Integer state){
        //调用业务获取数据
        int temp=houseService.passHouse(id,state);
        //封装返回数据
        Map<String,Object> map=new HashMap<>();
        map.put("result",temp);
        return map;
    }
}
