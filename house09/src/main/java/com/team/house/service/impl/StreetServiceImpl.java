package com.team.house.service.impl;

import com.team.house.entity.Street;
import com.team.house.entity.StreetExample;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value ="streetServiceImpl2" )
public class StreetServiceImpl implements StreetService {


    @Autowired
    private StreetMapper streetMapper;
    public List<Street> getStreetByDistrict(Integer did) {
        StreetExample streetExample=new StreetExample();
        StreetExample.Criteria criteria=streetExample.createCriteria();
        //添加条件
        criteria.andDistrictIdEqualTo(did);
        return streetMapper.selectByExample(streetExample);
    }



}
