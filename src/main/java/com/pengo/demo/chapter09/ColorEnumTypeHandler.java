package com.pengo.demo.chapter09;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Benpeng
 * @since 2023/4/7 16:44
 */
public class ColorEnumTypeHandler implements TypeHandler<Color> {
    @Override
    public void setParameter(PreparedStatement ps, int i, Color parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public Color getResult(ResultSet rs, String columnName) throws SQLException {
        int result = rs.getInt(columnName);
        return Color.getEnumByType(result);
    }

    @Override
    public Color getResult(ResultSet rs, int columnIndex) throws SQLException {
        int anInt = rs.getInt(columnIndex);
        return Color.getEnumByType(anInt);
    }

    @Override
    public Color getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int anInt = cs.getInt(columnIndex);
        return Color.getEnumByType(anInt);
    }
}
