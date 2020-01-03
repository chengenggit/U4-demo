package com.team.house.pcontroller;

import com.team.house.entity.Type;
import com.team.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("typeController2")
@RequestMapping("/page/")
public class TypeController {

    //调用业务
    @Autowired
    private TypeService typeService;

    @RequestMapping("getTypeData")
    @ResponseBody
    public List<Type> getTypeData(){
      return  typeService.getAllType();
    }

}
