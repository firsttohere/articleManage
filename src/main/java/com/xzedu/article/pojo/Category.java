package com.xzedu.article.pojo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @ClassName : Category
 * @Description : 文章分类
 * @Author : Xxxxx
 * @Date: 2023-11-18 20:25
 */
@Data
public class Category {
    // id 是修改和删除时的必传参数 指定groups即可
    @NotNull(groups = {Modify.class, Delete.class})
    private Integer id;
    // 分类名称和别名是 添加和修改接口的必传参数
    @NotEmpty(groups = {Add.class, Modify.class})
    private String categoryName;
    @NotEmpty(groups = {Add.class, Modify.class})
    private String categoryAlias;
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
    //validation框架提供了校验分组的功能
    // 需要在实体类中定义多个接口，每个接口代表一个组
    public interface Add {
        
    }
    public interface Modify {
        
    }
    public interface Delete {
        
    }
}
