package com.huing.controller;

import com.github.pagehelper.PageInfo;
import com.huing.pojo.ProductInfo;
import com.huing.service.ProductInfoService;
import com.huing.utils.FileNameUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author huing
 * @create 2022-06-07 16:09
 */
@Controller
@RequestMapping("/prod")
public class ProductInfoController {

    //每页记录数
    public static final int PAGE_SIZE = 5;
    //文件异步上传文件名称
    String saveFileName = "";

    @Autowired
    ProductInfoService productInfoService;

    /**
     * 显示全部商品不分页
     * @param model
     * @return
     */
    @RequestMapping("/getAll")
    public String getAll(Model model) {
        List<ProductInfo> list = productInfoService.getAll();

        model.addAttribute("list", list);
        return "product";
    }

    /**
     * 显示第一页的5条记录
     * @param model
     * @return
     */
    @RequestMapping("/split")
    public String split(Model model) {
        PageInfo pageInfo = productInfoService.splitPage(0, PAGE_SIZE);

        model.addAttribute("info", pageInfo);
        return "product";
    }


    @ResponseBody
    @RequestMapping("/ajaxsplit")
    public void ajaxsplit(int page, HttpSession session) {
        PageInfo pageInfo = productInfoService.splitPage(page, PAGE_SIZE);

        session.setAttribute("info", pageInfo);
        return;
    }

    /**
     *异步ajax文件上传处理
     * @param pimage
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajaxImg")
    public Object ajaxImg(MultipartFile pimage, HttpServletRequest request){

        //提取生成文件名UUID+上传文件图片的后缀
        saveFileName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(pimage.getOriginalFilename());
        //得到项目中图片存储的路径
        String path = request.getServletContext().getRealPath("/image_big");
        //转存
        try {
            pimage.transferTo(new File(path+File.separator+saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //返回客户端JSON对象，封装图片的路径，为了在页面实现立即回显
        JSONObject object = new JSONObject();
        object.put("imgurl",saveFileName);

        System.err.println(path);

        return object.toString();
    }

    @RequestMapping("/save")
    public String save(ProductInfo info,Model model){
        info.setpImage(saveFileName);
        info.setpDate(new Date());
        int result = -1;
        try {
            result = productInfoService.save(info);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (result > 0){
            model.addAttribute("msg","增加成功");
        }else {
            model.addAttribute("msg","增加失败");
        }
        //增加成功后，应该重新访问数据库
        return "forward:/prod/split.action";
    }
}
