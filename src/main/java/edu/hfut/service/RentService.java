package edu.hfut.service;

import edu.hfut.pojo.HouseSource;
import edu.hfut.pojo.User;

import java.util.List;
import java.util.Map;

public interface RentService {
    List<HouseSource> selectAll();
    List<HouseSource> selectByhPhone(String hPhone);
    HouseSource selectByHid(int hid);
    void deleteHouseSource(int hid);
    void addHouseSource(HouseSource houseSource);
    void updateHouseSource(HouseSource houseSource);
    User selectUserById(String uid);
    User selectUserByPhone(String phone);
    void insertUser(User user);
    List<HouseSource> selectByTitle(String title);
    List<HouseSource> selectByTitleAndPhone(Map<String, String> map);
}
