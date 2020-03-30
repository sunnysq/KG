package com.relatives.demo.dao;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RelationFindDao {
    private static Driver driver;

    @Autowired
    public RelationFindDao(Driver driver) {
        RelationFindDao.driver = driver;
    }

    public StatementResult getPathList(String name1,String name2) {
        try {
            String cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'" +
                    "MATCH p=(n)-[*..4]-(m) WITH RELATIONSHIPS(p) AS paths,SIZE(RELATIONSHIPS(p))  AS pathSize " +
                    "RETURN paths,pathSize";
            Session session=driver.session();
            StatementResult result= session.run(cql);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
         return null;
    }
    public void addRelation(String cql) {
        try {
           // String cql="MATCH (n:party{name:"+"'"+name1+"'"+"}),(m:party{name:"+"'"+name2+"'"+"}) WHERE n.sex='男' merge (n)-[r:relaton{relation:'兄妹'}]->(m)";
            Session session=driver.session();
            StatementResult result= session.run(cql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Record> getShortParth(String cql) {
        try {
            Session session = driver.session();
            StatementResult result = session.run(cql);
            List<Record> list = result.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
