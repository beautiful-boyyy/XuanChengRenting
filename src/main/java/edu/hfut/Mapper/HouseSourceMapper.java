package edu.hfut.Mapper;

import edu.hfut.pojo.HouseSource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface HouseSourceMapper {
    List<HouseSource> selectAll();
    List<HouseSource> selectByhPhone(String hPhone);
    List<HouseSource> selectByTitle(String title);
    HouseSource selectByHid(int hid);
    int deleteHouseSource(int hid);
    void addHouseSource(HouseSource houseSource);
    int updateHouseSource(HouseSource houseSource);
    List<HouseSource> selectByTitleAndPhone(Map<String, String> map);
}
