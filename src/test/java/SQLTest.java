import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import org.junit.Test;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

/**
 * @Author：cboss
 * @Date：2023/4/7
 */
public class SQLTest {
    @Test
    public void test() throws SQLSyntaxErrorException {

        String sql = "SELECT\n" +
                "\texample_nice.example_id,\n" +
                "\texample_nice.NAME,\n" +
                "\texample_nice.type,\n" +
                "\texample_nice.create_time\n" +
                "FROM\n" +
                "\texample_nice\n" +
                "\twhere example_nice.NAME > 'a'\n" +
                "\tand example_nice.create_time>'2023-04-01 15:50:05'";

        String dbType = "mysql";

        System.out.println("原始SQL 为 ： " + sql);

        String result = SQLUtils.format(sql, dbType);

        System.out.println("格式后："+result);

        SQLSelectStatement statement = (SQLSelectStatement) parser(sql, dbType);

        SQLSelect select = statement.getSelect();

        SQLSelectQueryBlock query = (SQLSelectQueryBlock) select.getQuery();

// 这里新增的条件，如果语法不正确会报错。如果条件不正确，需要执行了sql后才会报错。

        query.addCondition("name like 'admin%'");

        SQLExprTableSource tableSource = (SQLExprTableSource) query.getFrom();

        String tableName = tableSource.getExpr().toString();

        System.out.println("获取的表名为 tableName ：" + tableName);

//修改表名为acct_1

        tableSource.setExpr("acct_1");

        System.out.println("修改表名后的SQL 为 ： [" + statement.toString() + "]");

    }


    public SQLStatement parser(String sql, String dbType) throws SQLSyntaxErrorException {

        List<SQLStatement> list = SQLUtils.parseStatements(sql, dbType);

        if (list.size() > 1) {

            throw new SQLSyntaxErrorException("MultiQueries is not supported,use single query instead ");

        }

        return list.get(0);

    }

}
