package org.example.fdplatform.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BooleanAndTinyIntHandler extends BaseTypeHandler<Boolean> {

    //从java到数据库
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
        if (parameter) {
            ps.setInt(i, 1);
        } else {
            ps.setInt(i, 0);
        }
    }

    //从DB->Java代码
    @Override
    public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int man = rs.getInt(columnName);
        return man == 1 ? true : false;
    }

    @Override
    public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int man = rs.getInt(columnIndex);
        return man == 1 ? true : false;
    }

    //通过存储过程取数据
    @Override
    public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int man = cs.getInt(columnIndex);
        return man == 1 ? true : false;
    }
}
