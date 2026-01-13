package com.hxl.aspect.database;

public interface IDatabaseService {

    /**
     * 判断表是否存在
     */
    boolean needCreateTable(Throwable ex);

     /**
      * 创建表
      */
    void createTable(String tableName, String tableTemplate);
}
