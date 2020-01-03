package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.util.PageUtil;

import java.util.List;


public interface TypeService {
     /**
      * 查询区域带分页
      * @param pageUtil
      *   page属性接收页码，rows接收页大小
      * @return
      */
     public PageInfo<Type> getTypeByPage(PageUtil pageUtil);

     /**
      * 添加区域
      * @param Type 区域实体信息
      * @return 影响行数
      */
     public int addType(Type Type);

     /**
      * 通过主键查询单条
      * @param id  主键编号
      * @return  单条实体对象
      */
     public Type getType(Integer id);

     /**
      * 修改区域
      * @param Type  区域实体
      * @return 影响行数
      */
     public int updateType(Type Type);


     /**
      * 删除区域
      * @param id  区域编号
      * @return  影响行数
      */
     public void deleteType(Integer id);



     /**
      * 实现区域批量删除
      * @param ids 批量删除的id
      * @return 影响行数
      */
     public int deleteMoreType(Integer[] ids);

     /**
      * 查询所有类型
      * @return
      */
     public List<Type> getAllType();

}
