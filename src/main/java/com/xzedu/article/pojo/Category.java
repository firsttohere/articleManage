package com.xzedu.article.pojo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * @ClassName : Category
 * @Description : 文章分类
 * @Author : Xxxxx
 * @Date: 2023-11-18 20:25
 */
@Data
public class Category {
    private Integer id;
    @NotEmpty
    private String categoryName;
    @NotEmpty
    private String categoryAlias;
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
