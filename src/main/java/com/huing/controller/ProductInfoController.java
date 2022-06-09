package com.huing.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huing.pojo.ProductInfo;
import com.huing.pojo.vo.ProductInfoVo;
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
import java.util.ArrayList;
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
     *
     * @param model
     * @return
     */
    @RequestMapping("/getAll")
    public String getAll(Model model) {
        List<ProductInfo> list = productInfoService.getAll();

        model.addAttribute("list", list);
        return "product.jsp";
    }

    /**
     * 显示第一页的5条记录或者（当前页面）
     *
     * @param model
     * @return
     */
    @RequestMapping("/split")
    public String split(Model model, HttpSession session) {

        PageInfo pageInfo = null;

        Object vo = session.getAttribute("prodvo");
        Object vo_delete = session.getAttribute("deleteProdVo");
        if (vo != null) {
            pageInfo = productInfoService.splitPageVo((ProductInfoVo) vo, PAGE_SIZE);
            session.removeAttribute("prodvo");
        }if (vo_delete != null){
            pageInfo = productInfoService.splitPageVo((ProductInfoVo) vo_delete, PAGE_SIZE);
            session.removeAttribute("deleteProdVo");
        } else {
            pageInfo = productInfoService.splitPage(0, PAGE_SIZE);
        }

        model.addAttribute("info", pageInfo);
        return "product.jsp";
    }


    /**
     * ajax分页翻页处理
     *
     * @param vo
     * @param session
     */
    @ResponseBody
    @RequestMapping("/ajaxsplit")
    public void ajaxsplit(ProductInfoVo vo, HttpSession session) {
        System.err.println(vo);
        PageInfo pageInfo = productInfoService.splitPageVo(vo, PAGE_SIZE);
        session.setAttribute("info", pageInfo);
        return;
    }

    /**
     * 异步ajax文件上传处理
     *
     * @param pimage
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajaxImg")
    public Object ajaxImg(MultipartFile pimage, HttpServletRequest request) {

        //提取生成文件名UUID+上传文件图片的后缀
        saveFileName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(pimage.getOriginalFilename());
        //得到项目中图片存储的路径
        String path = request.getServletContext().getRealPath("/image_big");
        //转存
        try {
            pimage.transferTo(new File(path + File.separator + saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //返回客户端JSON对象，封装图片的路径，为了在页面实现立即回显
        JSONObject object = new JSONObject();
        object.put("imgurl", saveFileName);

        System.err.println(path);

        return object.toString();
    }

    /**
     * 新增商品
     *
     * @param info
     * @param model
     * @return
     */
    @RequestMapping("/save")
    public String save(ProductInfo info, Model model) {
        info.setpImage(saveFileName);
        info.setpDate(new Date());
        int result = -1;
        try {
            result = productInfoService.save(info);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result > 0) {
            model.addAttribute("msg", "增加成功");
        } else {
            model.addAttribute("msg", "增加失败");
        }

        //清空saveFileName,为了下次增加和修改
        saveFileName = "";

        //增加成功后，应该重新访问数据库
        return "forward:/prod/split.action";
    }

    /**
     * 根据Id查询商品信息
     *
     * @param pid
     * @param model
     * @return
     */
    @RequestMapping("/one")
    public String one(int pid, ProductInfoVo vo, Model model, HttpSession session) {
        ProductInfo info = productInfoService.getById(pid);
        model.addAttribute("prod", info);

        //将多条件及页码放入session中，更新结束后读取条件和页码
        session.setAttribute("prodvo", vo);

        return "update.jsp";
    }

    /**
     * 更新数据
     *
     * @param productInfo
     * @param model
     * @return
     */
    @RequestMapping("/update")
    public String update(ProductInfo productInfo, Model model) {

        if (!saveFileName.equals("")) {
            productInfo.setpImage(saveFileName);
        }
        int result = -1;
        try {
            result = productInfoService.update(productInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result > 0) {
            model.addAttribute("msg", "修改成功");
        } else {
            model.addAttribute("msg", "修改失败");
        }

        saveFileName = "";

        return "forward:/prod/split.action";
    }

    /**
     * 删除
     *
     * @param pid
     * @param model
     * @return
     */
    @RequestMapping("/delete")
    public String delete(int pid, Model model, HttpSession session, ProductInfoVo vo) {
        int result = -1;
        try {
            result = productInfoService.deleteById(pid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result > 0) {
            model.addAttribute("msg", "删除成功");
            //删除成功后，需要携带当前页码和多条件
            session.setAttribute("deleteProdVo",vo);
        } else {
            model.addAttribute("msg", "该商品已经在订单中，不可删除");
        }
        return "forward:/prod/split.action";
    }

    /**
     * 批量删除
     *
     * @param pids
     * @param model
     * @return
     */
    @RequestMapping("/deletebatch")
    public String deleteBatch(String pids, Model model) {
        String[] ps = pids.split(",");
        try {
            int result = productInfoService.deleteBatch(ps);
            if (result > 0) {
                model.addAttribute("msg", "批量删除成功");
            } else {
                model.addAttribute("msg", "批量删除失败");
            }
        } catch (Exception e) {
            model.addAttribute("msg", "该商品已经在订单中，不可删除");
        }
        return "forward:/prod/split.action";
    }


    /**
     * 多条件查询，未分页（弃）
     *
     * @param vo
     * @param session
     */
    @ResponseBody
    @RequestMapping("/condition")
    public void condition(ProductInfoVo vo, HttpSession session) {
        List<ProductInfo> list = productInfoService.selectCondition(vo);

        System.err.println(vo);
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);
        session.setAttribute("info", pageInfo);
        return;
    }


}
