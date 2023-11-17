package com.xzedu.article.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName : SysTableProps
 * @Description : SysTableProps
 * @Author : Xxxxx
 * @Date: 2023-11-17 12:02
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SysTableProps implements Serializable {
    @JsonIgnore
    private Integer propsNo;
    private Boolean stripe;
    private Boolean border;
    private String ref;
}
