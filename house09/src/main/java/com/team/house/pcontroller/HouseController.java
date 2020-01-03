package com.team.house.pcontroller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.entity.Users;
import com.team.house.service.HouseService;
import com.team.house.util.FileUploadUtil;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;


@Controller(value = "houseController")
@RequestMapping("/page/")
public class HouseController {

    @Autowired
    private HouseService houseService;


    //处理请求的方法
    @RequestMapping("addHouse")
    //一个表单对象对应一个参数或者实体
    //一个文件域对象与一个CommonsMultipartFile对象对应
    public String addHouse(HttpSession session, House house, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile){
        try {
            //1.实现文件上传
           /* System.out.println("上传文件名称:"+pfile.getOriginalFilename());
            System.out.println("上传文件大小:"+pfile.getSize());
            System.out.println("上传文件的类型:"+pfile.getContentType());*/
            //上传
            //获取文件的扩展名
            String saveFileName= FileUploadUtil.upload(pfile);

            //2.将数据添加到数据库
            //修改house实体，添加额外的属性值
            //设置编号
            house.setId(System.currentTimeMillis()+"");
            //设置所属用户   重点理解
            Users user=(Users)session.getAttribute("loginInfo");
            house.setUserId(user.getId());
            //设置图片路径
            house.setPath(saveFileName);

            //调用业务实现添加
            houseService.addHouse(house);
            return "fabu";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }


    //显示发布出租房信息
    @RequestMapping("showHouse")    //pageUtile只用接收页码即可  page
    public String showHouse(PageUtil pageUtil, HttpSession session, Model model){
        //设置页大小   选择设置默认值
        pageUtil.setRows(5);

        //调用业务获取数据
        //获取用户登入的id
        Users user=(Users)session.getAttribute("loginInfo");
        //Integer usreid=1007;  //固定法
        PageInfo<House> pageInfo=houseService.getHouseByUser(user.getId(),pageUtil);
        //将数据填充到作用域
        model.addAttribute("pageInfo",pageInfo);
        return "guanli";
    }

    //显示修改出租房信息
    @RequestMapping("editHouse")
    public  String editHouse(String id,Model model){
        //调用业务获取数据
        House house=houseService.getHouse(id);
        model.addAttribute("house",house);
        return "upfabu";
    }



    //处理请求的方法
    @RequestMapping("upHouse")
    //一个表单对象对应一个参数或者实体
    //一个文件域对象与一个CommonsMultipartFile对象对应
    public String upHouse(House house,String oldPicPath, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile){
        try {
            //1.判断图片有没有选择   :如果选中图片上传， 否则不上传
            if(!pfile.getOriginalFilename().equals("")){
                //System.out.println("上传图片");
                String saveFileName=FileUploadUtil.upload(pfile);
                //设置修改实体图片的路径
                house.setPath(saveFileName);
                //删除旧图
                // File file=new File("d:\\images\\"+oldPicPath);
                //file.delete();
                FileUploadUtil.deleteFile(oldPicPath);
            }

            //2.修改数据库信息
            //调用业务实现添加
            houseService.updateHouse(house);

            return "redirect:showHouse";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    //删除出租房
    @RequestMapping("deleteHouse")  //id为出租房编号
    public  String deleteHouse(String id){
        try {
            //调用业务删除数据
            int temp=houseService.deleleHouse(id,1);  //1表示删除
            return "redirect:showHouse";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }


    //恢复出租房
    @RequestMapping("backDelHouse")  //id为出租房编号
    public  String backDelHouse(String id){
        try {
            //调用业务删除数据
            int temp=houseService.deleleHouse(id,0);  //1表示删除
            return "redirect:showHouse";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }
}
