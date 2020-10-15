package edu.hfut.service;

import edu.hfut.Mapper.HouseSourceMapper;
import edu.hfut.Mapper.UserMapper;
import edu.hfut.pojo.HouseSource;
import edu.hfut.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("RentServiceImpl")
public class RentServiceImpl implements RentService {
    private UserMapper userMapper;
    private HouseSourceMapper houseSourceMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Autowired
    public void setHouseSourceMapper(HouseSourceMapper houseSourceMapper) {
        this.houseSourceMapper = houseSourceMapper;
    }

    public List<HouseSource> selectAll() {
        return houseSourceMapper.selectAll();
    }


    public List<HouseSource> selectByhPhone(String hPhone) {
        return houseSourceMapper.selectByhPhone(hPhone);
    }


    public HouseSource selectByHid(int hid) {
        return houseSourceMapper.selectByHid(hid);
    }


    public void deleteHouseSource(int hid) {
        houseSourceMapper.deleteHouseSource(hid);
    }


    public void addHouseSource(HouseSource houseSource) {
        houseSourceMapper.addHouseSource(houseSource);
    }

    public void updateHouseSource(HouseSource houseSource) {
        houseSourceMapper.updateHouseSource(houseSource);
    }


    public User selectUserById(String uid) {
        return userMapper.selectUserById(uid);
    }

    public User selectUserByPhone(String phone) {
        return userMapper.selectUserByPhone(phone);
    }

    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    public List<HouseSource> selectByTitle(String title) {
        return houseSourceMapper.selectByTitle(title);
    }

    @Override
    public List<HouseSource> selectByTitleAndPhone(Map<String, String> map) {
        return houseSourceMapper.selectByTitleAndPhone(map);
    }

}
