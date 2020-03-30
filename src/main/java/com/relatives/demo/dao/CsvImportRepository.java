package com.relatives.demo.dao;


import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CsvImportRepository{

    private static Driver driver;
    @Autowired
    public CsvImportRepository(Driver driver) {
        CsvImportRepository.driver = driver;
    }
    public void addNode1(String fileName){
        try {
            //创建党员节点
            String cql="LOAD CSV  FROM \" file:///"+fileName+"\" AS line merge (:party{ name:line[0],id_code:line[1],address:line[2],sex:line[3],present_position:line[4],education:line[5],nation:line[6],telephone:line[7],workplace:line[8]})";
            Session session = driver.session();
            StatementResult result = session.run(cql);
            session.close();
         //   System.out.println(result);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

   public void addNode2(String fileName){
       try {
           //创建亲属节点
           String cql="LOAD CSV  FROM \" file:///"+fileName+"\" AS line merge (:relatives{ name:line[0],id_code:line[1],address:line[2],sex:line[3],telephone:line[4],workplace:line[5]})";
           Session session = driver.session();
           StatementResult result = session.run(cql);
           session.close();
           //   System.out.println(result);
       }catch (Exception e) {
           e.printStackTrace();
       }

   }
   public void addRelation(String fileName){
       try {
           //创建党员和亲属之间的关系
           String cql="LOAD CSV  FROM \" file:///"+fileName+"\" AS line match (from:party{id_code:line[0]}),(to:relatives{id_code:line[2]}) merge (from)-[:relation{relation:line[1]}]->(to)";
           String cql1="LOAD CSV  FROM \" file:///"+fileName+"\" AS line match (from:party{id_code:line[0]}),(to:party{id_code:line[2]}) merge (from)-[:relation{relation:line[1]}]->(to)";
           String cql2="LOAD CSV  FROM \" file:///"+fileName+"\" AS line match (from:relatives{id_code:line[0]}),(to:relatives{id_code:line[2]}) merge (from)-[:relation{relation:line[1]}]->(to)";
           Session session = driver.session();
           StatementResult result = session.run(cql);
           StatementResult result1 = session.run(cql1);
           StatementResult result2 = session.run(cql2);
           session.close();
           //   System.out.println(result);
       }catch (Exception e) {
           e.printStackTrace();
       }

    }
//        extends Neo4jRepository<TbCivilServant,Long> {
//    @Query(" LOAD CSV  FROM \" file:///tb_civil_servant.csv\" AS line " +
//            "merge (:party{ pid:line[0],name:line[1],id_code:line[2],address:line[3],sex:line[4]," +
//            "present_position:line[5],education:line[6],nation:line[7],telephone:line[8],workplace:line[9]}) ")
//     void addNode1(@Param("FileName") String FileName);
//    @Query(" LOAD CSV  FROM \" file:///tb_relatives.csv\" AS line " +
//            "merge (:relatives{ rid:line[0],name:line[1],id_code:line[2],address:line[3],sex:line[4],telephone:line[5],workplace:line[6]}) ")
//     void addNode2(@Param(value="FileName") String FileName);
//    @Query("LOAD CSV  FROM \"file:///tb_relation.csv\" AS line match (from:party{id_code:line[0]}),(to:relatives{id_code:line[2]}) merge (from)-[:relation{relation:line[1]}]->(to)")
//    void addRelation(@Param(value="Filename") String Filename);
//    @Query("MATCH (n:party) RETURN n")
//    List<TbCivilServant> getUserNode();

}
