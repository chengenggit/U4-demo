package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.DistrictExample;
import com.team.house.mapper.DistrictMapper;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.DistritctService;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class DistrictServiceImpl implements DistritctService {

    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private StreetMapper streetMapper;


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)  //支持事务  都成功，都不成功
    public PageInfo<District> getDistrictByPage(PageUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        //查询
        DistrictExample districtExample=new DistrictExample();
        List<District> list= districtMapper.selectByExample(districtExample);
        return new PageInfo<>(list);
    }

    @Override
    public int addDistrict(District district) {
        return districtMapper.insertSelective(district);
    }

    @Override
    public District getDistrict(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    @Override
    @Transactional  //支持事务  都成功，都不成功
    //如果方法不报错则事务管理器会提交，报错时，事务管理器会回滚
    public void deleteDistrict(Integer id) {
        //第一步:删除街道   编写dao层提供删除区域下街道的方法
        streetMapper.delStreetByDistritc(id);

         //int i=0,j=10;
         //j=j/i;  //报错

        //第二步:删除区域
        districtMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteMoreDistrict(Integer[] ids) {
        return districtMapper.deleteMoreDistrict(ids);
    }

    @Override
    public List<District> getAllDistrict() {
        return districtMapper.selectByExample(new DistrictExample());
    }
}
