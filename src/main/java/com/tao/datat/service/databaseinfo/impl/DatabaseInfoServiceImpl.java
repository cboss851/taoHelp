package com.tao.datat.service.databaseinfo.impl;

import com.tao.datat.service.database.*;
import com.tao.datat.service.databaseinfo.DatabaseInfoService;
import com.tao.datat.service.databaseinfo.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DatabaseInfoServiceImpl implements DatabaseInfoService {
    private final DatabaseMetaService databaseMetaService;
    private final DatabaseTemplateService databaseTemplateService;

    @Override
    public Database queryDatabaseInfo(DataSource ds) throws SQLException {
        //数据库信息
        Database database = databaseMetaService.getDatabase(ds);

        return database;
    }

    @Override
    public List<Table> queryTableInfo(TableQueryReq req) throws SQLException {
        //查询表元数据信息
        List<Table> tables = databaseMetaService.getTables(req.getDs(), req.getSchema(), req.getTableName());
        return tables;
    }
}