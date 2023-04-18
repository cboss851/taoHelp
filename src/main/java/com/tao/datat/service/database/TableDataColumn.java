package com.tao.datat.service.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 列
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableDataColumn implements Serializable {
    private TableField field;
    private Object value;

    private static final long serialVersionUID = 1L;
}
