package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.Type;
import com.team.house.service.DistritctService;
import com.team.house.service.TypeService;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;



@RestController // @Controller+ @ResponseBody
@RequestMapping("/admin/")  //指定请求前缀
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping("getTypeData")   //page ,rows
    public Map<String,Object> getTypeData(PageUtil pageUtil){
        //调用业务区域数据
        PageInfo<Type> pageInfo=typeService.getTypeByPage(pageUtil);

        //封装返回数据
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }


    //添加
    @RequestMapping("addType")
    public String addType(Type type){
        try {
            //调用业务
            int result=typeService.addType(type);
            //封装返回数据
            // Map<String,Object> map=new HashMap<>();   //自动转json
            // map.put("result",result);
            return "{\"result\":"+result+"}";   //拼接的json
        }catch (Exception e){
            return "{\"result\":-1}";
        }
    }



    //获取单条数据
    @RequestMapping("getType")
    public Type getType(Integer id){
        try {
            Type type=typeService.getType(id);
            return type;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //修改区域
    @RequestMapping("updateType")  //一定要传id   ,name
    public String updateType(Type type){
        try {
            //调用业务
            int result=typeService.updateType(type);
            //封装返回数据
            // Map<String,Object> map=new HashMap<>();   //自动转json
            // map.put("result",result);
            return "{\"result\":"+result+"}";   //拼接的json
        }catch (Exception e){
            return "{\"result\":-1}";
        }
    }


    //删除某个区域
    @RequestMapping("delType")
    public String delType(Integer id){
        try {
            //调用业务
            typeService.deleteType(id);
            //封装返回数据
            // Map<String,Object> map=new HashMap<>();   //自动转json
            // map.put("result",result);
            return "{\"result\":1}";   //拼接的json
        }catch (Exception e){
            return "{\"result\":-1}";
        }
    }


    //批量删除区域
    //前台传递数据的格式:ids=1,2,3,4     后台: String ids变量接收数据
    //前台传递数据的格式:ids=1&ids=2&ids=3     后台: Integer []ids变量接收数据
    @RequestMapping("delMoreType")
    public String delMoreType(String ids){
        try {
            //将字符串转化为数据组
            String [] strList=ids.split(",");
            Integer [] idList=new Integer[strList.length];
            for (int i=0;i<strList.length;i++) {
                idList[i]=new Integer(strList[i]);
            }
            //调用业务
            int temp=typeService.deleteMoreType(idList);
            return "{\"result\":"+temp+"}";   //拼接的json
        }catch (Exception e){
            e.printStackTrace();   //使用日志工具记录
            return "{\"result\":-1}";
        }
    }
}
