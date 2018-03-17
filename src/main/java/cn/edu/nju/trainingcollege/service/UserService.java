package cn.edu.nju.trainingcollege.service;


import cn.edu.nju.trainingcollege.entity.ClassEntity;
import cn.edu.nju.trainingcollege.entity.UserEntity;
import cn.edu.nju.trainingcollege.entity.UserInfoEntity;
import cn.edu.nju.trainingcollege.vo.ClassInfoVo;
import cn.edu.nju.trainingcollege.vo.MemberInfoVo;
import cn.edu.nju.trainingcollege.vo.OrderVo;

public interface UserService {

    void register(String mail, String password, String name,String phone,String sex);

    UserEntity findByMail(String mail);

    boolean login(String mail, String password);

    UserInfoEntity getUserInfoById(int id);

    MemberInfoVo getMemberInfo(int id);

    ClassInfoVo getClassInfo(int id);

    OrderVo generateordervo(int classid, int userid);

    void createorder(int classid,int userid,int people,int coupon);
}
