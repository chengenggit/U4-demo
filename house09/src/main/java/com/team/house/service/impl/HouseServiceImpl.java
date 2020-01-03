package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.mapper.HouseMapper;
import com.team.house.service.HouseService;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;

    @Override
    public int addHouse(House house) {
        return houseMapper.insertSelective(house);
    }

    @Override
    public PageInfo<House> getHouseByUser(Integer id, PageUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        List<House> list=houseMapper.getHouseByUser(id);
        return new PageInfo<House>(list);
    }

    @Override
    public House getHouse(String id) {
        return houseMapper.getHouse(id);
    }

    @Override
    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }
    @Override
    public int deleleHouse(String houseId, Integer delState) {
        House house=new House();
        house.setId(houseId);  //条件
        house.setIsdel(delState);  //更新状态
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getBackAllHouse(PageUtil pageUtil) {
        //开启分页
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        List<House> list=houseMapper.getBackAllHouse();
        return new PageInfo<House>(list);
    }

    @Override
    public int passHouse(String houseId, Integer passState) {
        House house=new House();
        house.setId(houseId);  //条件
        house.setIspass(passState);  //更新审核状态
        return houseMapper.updateByPrimaryKeySelective(house);
    }

}
