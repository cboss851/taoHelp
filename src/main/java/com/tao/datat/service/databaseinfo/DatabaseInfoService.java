package com.tao.datat.service.databaseinfo;

import com.tao.datat.service.database.DataSource;
import com.tao.datat.service.database.Database;
import com.tao.datat.service.database.Table;
import com.tao.datat.service.databaseinfo.dto.TableQueryReq;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseInfoService {
    Database queryDatabaseInfo(DataSource ds) throws SQLException;

    List<Table> queryTableInfo(TableQueryReq req) throws SQLException;
}
