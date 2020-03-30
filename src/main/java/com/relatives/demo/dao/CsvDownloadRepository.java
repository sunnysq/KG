package com.relatives.demo.dao;

import com.relatives.demo.entity.TbCivilServant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import  org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CsvDownloadRepository {

    /**
     * @param tableName 要查询的表名
     * @Select("Select * FROM  #{tableName} into outfile 'D:\\neo4j-community-3.5.5-windows\\neo4j-community-3.5.5\\import\\tb_civil_servant.csv' fields terminated by ',' optionally enclosed by ' \" ' lines terminated by '\r\n' ")
     */
    //查询这个表的所有数据，保存到对应的CSV文件
    @Select("Select * FROM  ${tableName} into outfile 'D:/neo4j-community-3.5.5-windows/neo4j-community-3.5.5/import/${tableName}.csv'  fields terminated by ','  lines terminated by '\\r\\n' ")
    public List<TbCivilServant> downLoad(@Param(value="tableName") String tableName);
}