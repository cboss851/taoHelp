package com.tao.datat.service.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableData implements Serializable {
    //表
    private Table table;
    //一行数据
    private List<List<TableDataColumn>> rows;

    private static final long serialVersionUID = 1L;
}