package com.xzedu.article.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName : SysTableColumns
 * @Description : SysTableColumns
 * @Author : Xxxxx
 * @Date: 2023-11-17 12:06
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SysTableColumns implements Serializable {
    @JsonIgnore
    private Integer columnNo;
    private String type;
    private String title;
    private String key;
    @JsonIgnore
    private Integer width;
    private String align;
    private String fixed;
    private Boolean ellipsis;
    private Boolean sortable;
    private String slot;
}
