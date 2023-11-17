package com.xzedu.article.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName : SysTable
 * @Description : 系统的列表
 * @Author : Xxxxx
 * @Date: 2023-11-17 12:01
 */
@Data
// 如果某些字段的值为空或者空字符串就不响应
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SysTable implements Serializable {

    @JsonIgnore
    private Integer tableNo;// 列表编号

    @JsonIgnore
    private String tableName;// 列表名字

    @JsonIgnore
    private Integer propsNo;//列表样式

    private SysTableProps tableProps;// 列表属性

    private List<SysTableColumns> tableColumns;// 列表列集合
}
