package com.xzedu.article.pojo;

import com.xzedu.article.annotations.State;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**
 * @ClassName : Article
 * @Description : Article
 * @Author : Xxxxx
 * @Date: 2023-11-19 12:17
 */
@Data
public class Article {
    @NotNull(groups = {Modify.class})
    private Integer id;
    @NotEmpty(groups = {Add.class, Modify.class})
    @Pattern(groups = {Add.class, Modify.class},regexp = "^\\S{1,10}$")
    private String title;
    @NotEmpty(groups = {Add.class, Modify.class})
    private String content;
    @NotEmpty(groups = {Add.class, Modify.class})
    @URL(groups = {Add.class, Modify.class})
    private String coverImg;
    // 这个字段需要校验 必须是 "已发布" 和 "草稿" 这两种  需要用到自定义校验
    @State(groups = {Add.class, Modify.class})
    private String state;
    @NotNull(groups = {Add.class, Modify.class})
    private Integer categoryId;
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public interface Add {

    }
    public interface Modify {

    }
}
