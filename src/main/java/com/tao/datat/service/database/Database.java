package com.tao.datat.service.database;

import com.tao.datat.service.database.enums.DatabaseTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Database implements Serializable {
    private String name;
    private String url;
    private DatabaseTypeEnum type;
    private String productVersion;
    private String driverName;
    private String driverVersion;
    private String driverClassName;
    private static final long serialVersionUID = 1L;
}