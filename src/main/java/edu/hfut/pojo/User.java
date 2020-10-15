package edu.hfut.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //用户名
    private String uid;
    private String username;
    private String pwd;
    private String phone;
}
