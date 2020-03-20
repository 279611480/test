package org.yun.entity;

import lombok.*;

/**
 * @ClassName Student
 * @Author 芸
 * @Date 2020/2/28 17:38
 * @Description TODO
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {

    private Integer id;
    private String name;
    private String age;
    private Double scoce;
}
