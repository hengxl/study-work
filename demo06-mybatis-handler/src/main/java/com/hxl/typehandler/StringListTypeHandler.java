package com.hxl.typehandler;

import com.hxl.enums.FunctionType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author hengxiaolaing
 * @description 自定义的 TypeHandler 来实现 List<String> 类型与数据库列值之间的转换
 * 功能：实现Java List<String>与数据库字符串(逗号分隔)之间的类型转换
 */
public class StringListTypeHandler extends BaseTypeHandler<List<String>> {
    // 定义字符串分隔符
    private static final String DELIMITER = ",";

    /**
     * 设置非空参数：将List<String>转换为逗号分隔的字符串存入数据库
     * @param ps PreparedStatement对象
     * @param i 参数位置索引
     * @param parameter 要转换的List<String>参数
     * @param jdbcType JDBC类型
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        // 将 List<String> 转换为以逗号分隔的字符串
        String codeString = String.join(DELIMITER, parameter);
        // 将字符串存储到数据库中
        ps.setString(i, codeString);
    }

    /**
     * 从结果集中通过列名获取值并转换为List<String>
     * @param rs 结果集
     * @param columnName 列名
     * @return 转换后的List<String>对象
     */
    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // 从 ResultSet 中获取字符串值
        String codeString = rs.getString(columnName);
        // 将字符串转换为 List<String>
        return parseCodeString(codeString);
    }

    /**
     * 从结果集中通过列索引获取值并转换为List<String>
     * @param rs 结果集
     * @param columnIndex 列索引
     * @return 转换后的List<String>对象
     */
    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String codeString = rs.getString(columnIndex);
        return parseCodeString(codeString);
    }

    /**
     * 从存储过程中获取值并转换为List<String>
     * @param cs 调用语句
     * @param columnIndex 列索引
     * @return 转换后的List<String>对象
     */
    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String codeString = cs.getString(columnIndex);
        return parseCodeString(codeString);
    }

    /**
     * 解析数据库中的数字代码字符串为功能名称列表
     * @param codeString 从数据库读取的逗号分隔的数字代码字符串
     * @return 功能名称列表，空值返回空列表
     */
    private List<String> parseCodeString(String codeString) {
        if (codeString == null || codeString.isEmpty()) {
            return Collections.emptyList();
        }

        // 将字符串按逗号分隔转换为数字代码数组
        String[] codes = codeString.split(DELIMITER);
        List<String> funcList = new ArrayList<>();

        // 将每个数字代码转换为对应的功能名称
        for (String code : codes) {
            funcList.add(FunctionType.getNameByCode(code.trim()));
        }

        return funcList;
    }
}