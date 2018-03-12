package cn.edu.nju.trainingcollege.controller;


import cn.edu.nju.trainingcollege.entity.UserEntity;
import cn.edu.nju.trainingcollege.entity.UserInfoEntity;
import cn.edu.nju.trainingcollege.service.UserService;
import cn.edu.nju.trainingcollege.util.Helper;
import cn.edu.nju.trainingcollege.vo.PersonInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@RequestMapping(value = "/user")
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model,HttpSession session, @RequestParam("username") String mail, @RequestParam("password") String password) {

        if(userService.login(mail,password)==true){

           UserEntity userEntity = userService.findByMail(mail);
            UserInfoEntity userInfoEntity=userService.getUserInfoById(userEntity.getId());
            session.setAttribute("userid", userEntity.getId());

            Helper helper=new Helper();
            PersonInfoVo personInfoVo=new PersonInfoVo(userInfoEntity.getId(), mail, userInfoEntity.getName(), userInfoEntity.getMemberState().toString(), helper.timeToDateString(userInfoEntity.getRegistdate()),"../../static/portrait/timg.jpg",userInfoEntity.getSex(),userInfoEntity.getPhonenum());

            model.addAttribute("personinfo",personInfoVo);


            return "user/userinfo";
        }
        return "user/wrongpassword";

    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model, @RequestParam("mail") String mail,@RequestParam("password") String password,@RequestParam("name") String name,@RequestParam("phone") String phone,@RequestParam("sex") String sex) {

        userService.register(mail,password,name,phone,sex);


           return "/index";


    }




    @RequestMapping({"/", "/login" , "/index"})
    public String login() {
        return "user/login";
    }

    @RequestMapping("/register")
    public String register(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "user/register";
    }
}

