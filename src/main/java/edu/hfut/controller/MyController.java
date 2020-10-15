package edu.hfut.controller;

import edu.hfut.pojo.HouseSource;
import edu.hfut.pojo.User;
import edu.hfut.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MyController {
    @Autowired
    @Qualifier("RentServiceImpl")
    private RentService rentService;
    @RequestMapping("/index")
    public String showIndex()
    {
        return "login";
    }
    @RequestMapping("/selectAll")
    public String selectAll(Model model)
    {
        List<HouseSource> list = rentService.selectAll();
        model.addAttribute("lists",list);
        return "list";
    }
    @RequestMapping("/login")
    public String login(
            @RequestParam("uid") String uid,
            @RequestParam("pwd") String pwd,
            Model model, HttpServletRequest  request)
    {
        User user = rentService.selectUserById(uid);
        if (null != user && user.getPwd().equals(pwd))
        {
            HttpSession session = request.getSession();
            session.setAttribute("phone",user.getPhone());
            return "redirect:/selectAll";
        }
        else
        {
            model.addAttribute("msg","用户名或者密码错误");
            return "login";
        }
    }
    @RequestMapping("/toRegPage")
    public String showRegPage()
    {
        return "reg";
    }
    @RequestMapping("/Reg")
    public String reg(HttpServletRequest request, Model model)
    {
//        //System.out.println(info.toString());
        String str1 = request.getParameter("uid");
        String str2 = request.getParameter("pwd");
        String str3 = request.getParameter("rPwd");
        String str4 = request.getParameter("phone");
        String str5 = request.getParameter("username");
        User user = new User();
        user.setUid(str1);
        user.setUsername(str5);
        user.setPhone(str4);
        user.setPwd(str2);
        if (rentService.selectUserById(str1) != null)
       {
           model.addAttribute("msg","该账号已被注册");
           return "reg";
       }
       else if(!str2.equals(str3))
       {
           model.addAttribute("msg","两次输入的密码不一致");
           return "reg";
       }
       else if (rentService.selectUserByPhone(str4) != null)
       {
           model.addAttribute("msg","该电话号码已被使用");
           return "reg";
       }
       else
       {
           rentService.insertUser(user);
           return "login";
       }
    }
    @RequestMapping("/manage")
    public String showManagementCenter(String phone, Model model)
    {
        List<HouseSource> list = rentService.selectByhPhone(phone);
        model.addAttribute("myList",list);
        return "guanli";
    }
    @RequestMapping("/toPublishPage")
    public String toPublishPage(Model model)
    {
        return "fabu";
    }
    @RequestMapping("/publish")
    public String publish(HttpServletRequest request) throws ParseException {
        String hDate = request.getParameter("hDate");
        String location = request.getParameter("location");
        String location1 = request.getParameter("location1");
        String title = request.getParameter("title");
        String price = request.getParameter("price");
        String shape = request.getParameter("shape");
        String area = request.getParameter("area");
        String hPhone = request.getParameter("hPhone");
        String description = request.getParameter("description");
        HouseSource houseSource = new HouseSource();
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse(hDate);

        houseSource.setHDate(date);
        houseSource.setArea(Double.parseDouble(area.toString()));
        houseSource.setDescription(description);
        System.out.println(hPhone);
        houseSource.setHPhone(hPhone);
        houseSource.setLocation(location + location1);
        houseSource.setPrice(Double.parseDouble(price.toString()));
        houseSource.setShape(shape);
        houseSource.setTitle(title);
        rentService.addHouseSource(houseSource);
        return "redirect:/selectAll";
    }
    @RequestMapping("/showDetail")
    public String showDetail(int hid,Model model)
    {
        HouseSource list = rentService.selectByHid(hid);
        model.addAttribute("detail",list);
        return "details";
    }
    @RequestMapping("/updateItem")
    public String updateItem(int hid, Model model)
    {
        HouseSource list = rentService.selectByHid(hid);
        model.addAttribute("items",list);
        return "update";
    }
    @RequestMapping("/realUpdateItem")
    public String realUpdateItem(HttpServletRequest request, Model model) throws ParseException {
        String hDate = request.getParameter("hDate");
        String location = request.getParameter("location");
        String location1 = request.getParameter("location1");
        String title = request.getParameter("title");
        String price = request.getParameter("price");
        String shape = request.getParameter("shape");
        String area = request.getParameter("area");
        String hPhone = request.getParameter("hPhone");
        String description = request.getParameter("description");
        String hid = request.getParameter("hid");
        HouseSource houseSource = new HouseSource();
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse(hDate);

        houseSource.setHDate(date);
        houseSource.setArea(Double.parseDouble(area.toString()));
        houseSource.setDescription(description);
        System.out.println(hPhone);
        houseSource.setHPhone(hPhone);
        houseSource.setLocation(location + location1);
        houseSource.setPrice(Double.parseDouble(price.toString()));
        houseSource.setShape(shape);
        houseSource.setTitle(title);
        houseSource.setHid(Integer.parseInt(hid));
        System.out.println(houseSource.toString());
        rentService.updateHouseSource(houseSource);
        return "redirect:/selectAll";
    }
    @RequestMapping("/deleteItem")
    public String deleteItem(int hid)
    {
        rentService.deleteHouseSource(hid);
        return "redirect:/selectAll";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session)
    {
        System.out.println("logout");
        //session失效

        session.removeAttribute("phone");
        return "redirect:/index";
    }
    @RequestMapping("/search")
    public String search(HttpServletRequest request, Model model)
    {

        String str1 = request.getParameter("search") + "%";
        List<HouseSource> list = rentService.selectByTitle(str1);
        model.addAttribute("lists",list);
        return "list";
    }

    @RequestMapping("/searchInCenter")
    public String searchInCenter(HttpServletRequest request, Model model)
    {
        HttpSession session = request.getSession();
        String str1 = session.getAttribute("phone").toString();
        String str2 = request.getParameter("search") + "%";
        Map<String, String> map = new HashMap<String, String>();
        map.put("title",str2);
        map.put("hPhone",str1);
        List<HouseSource> list = rentService.selectByTitleAndPhone(map);
        System.out.println(list.toString());
        model.addAttribute("myList",list);
        return "guanli";
    }
}
