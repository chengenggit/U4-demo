package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.entity.Type;
import com.team.house.util.PageUtil;

import java.util.List;


public interface StreetService {
     /**
      * 查询对应区域下的街道
      * @return
      */
     public List<Street> getStreetByDistrict(Integer did);

}
