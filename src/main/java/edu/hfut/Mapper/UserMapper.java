package edu.hfut.Mapper;

import edu.hfut.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
   User selectUserById(String uid);
   User selectUserByPhone(String phone);
   int insertUser(User user);
}
