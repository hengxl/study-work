package com.hxl.mapper;

import com.hxl.domain.entity.UserFunc;
import com.hxl.domain.entity.UserFunction;
import com.hxl.hande.MapResultHandler;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**TODO
 *  是 MyBatis 框架提供的注解，用于标识该接口是 MyBatis 的映射器（Mapper）接口。
 *  MyBatis 会自动扫描并识别带有 @Mapper 的接口，为其生成代理实现类（无需手动编写实现类），
 *  从而可以通过接口直接操作数据库（配合 XML 映射文件或注解 SQL 使用）。
 */
@Mapper
public interface UserMapper {
    /**
     * 根据用户ID集合查询meeting权限
     * @param userList 用户ID集合
     * @param mapResultHandler 将结果以 userId为key，功能权限为value保存在MapResultHandler中
     */
    void selectFuncMapByUserIds(@Param("userList") List<Integer> userList, MapResultHandler<Integer, String> mapResultHandler);

    /**
     * 查询用户的会议功能权限 并且以code形式返回
     * 例如
     */
    Optional<UserFunction> selectFuncSetByUserId(Integer userId);

    /**
     *
     * @param type
     * @return
     */
    List<UserFunc> queryByFuncType(String type);
}
