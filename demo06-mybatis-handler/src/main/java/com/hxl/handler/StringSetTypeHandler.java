package com.hxl.handler;

import com.hxl.constant.FunctionType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author hengxiaolaing
 * @description 自定义的 TypeHandler 来实现 Set<String> 类型与数据库列值之间的转换
 * 功能：实现Java Set<String>与数据库字符串(逗号分隔)之间的类型转换
 */
public class StringSetTypeHandler extends BaseTypeHandler<Set<String>> {
    // 定义字符串分隔符
    private static final String DELIMITER = ",";

    /**
     * 设置非空参数：将Set<String>转换为逗号分隔的字符串存入数据库
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Set<String> parameter, JdbcType jdbcType) throws SQLException {
        String typeString = String.join(DELIMITER, parameter);
        ps.setString(i, typeString);
    }

    /**
     * 从结果集中通过列名获取值并转换为Set<String>
     */
    @Override
    public Set<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String typeString = rs.getString(columnName);
        return parseTypeString(typeString);
    }

    /**
     * 从结果集中通过列索引获取值并转换为Set<String>
     */
    @Override
    public Set<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String typeString = rs.getString(columnIndex);
        return parseTypeString(typeString);
    }

    /**
     * 从存储过程中获取值并转换为Set<String>
     * @param cs          调用语句
     * @param columnIndex 列索引
     * @return 转换后的Set<String>对象
     */
    @Override
    public Set<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String typeString = cs.getString(columnIndex);
        return parseTypeString(typeString);
    }

    /**
     * 解析数据库中的数字代码字符串为功能名称集合
     * @param typeString 从数据库读取的逗号分隔的数字代码字符串
     * @return 功能名称集合，空值返回空集合
     */
    private Set<String> parseTypeString(String typeString) {
        if (typeString == null || typeString.isEmpty()) {
            return Collections.emptySet();
        }

        // 将字符串按逗号分隔转换为数字代码数组
        String[] types = typeString.split(DELIMITER);
        Set<String> priSet = new HashSet<>();

        // 将每个数字代码转换为对应的功能名称
        for (String type : types) {
            priSet.add(FunctionType.getCodeByType(type.trim()));
        }

        return priSet;
    }
}