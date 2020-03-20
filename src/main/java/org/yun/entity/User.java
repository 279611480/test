package org.yun.entity;

import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @ClassName User
 * @Author 芸
 * @Date 2020/2/28 16:56
 * @Description TODO
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private String name;
    private Integer age;
    private String sex;
    private Date date;
    private Timestamp date1;
    
}
