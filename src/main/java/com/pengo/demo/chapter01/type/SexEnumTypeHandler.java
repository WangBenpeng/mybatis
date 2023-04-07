package com.pengo.demo.chapter01.type;

import com.pengo.demo.chapter01.Sex;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Benpeng
 * @date 2023/3/22
 */
@Slf4j
public class SexEnumTypeHandler implements TypeHandler<Sex> {
    @Override
    public void setParameter(PreparedStatement ps, int i, Sex parameter, JdbcType jdbcType) throws SQLException {
        log.info("enum type handler set parameter");
        ps.setInt(i, parameter.getId());
    }

    @Override
    public Sex getResult(ResultSet rs, String columnName) throws SQLException {
        log.info("enum type handler get result by column name");
        int id = rs.getInt(columnName);
        return Sex.getSex(id);
    }

    @Override
    public Sex getResult(ResultSet rs, int columnIndex) throws SQLException {
        log.info("enum type handler get result by column index");
        int id = rs.getInt(columnIndex);
        return Sex.getSex(id);
    }

    @Override
    public Sex getResult(CallableStatement cs, int columnIndex) throws SQLException {
        log.info("enum type handler get result by column index");
        int id = cs.getInt(columnIndex);
        return Sex.getSex(id);
    }

}
