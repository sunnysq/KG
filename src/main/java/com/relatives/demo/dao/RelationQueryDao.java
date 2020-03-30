package com.relatives.demo.dao;

import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Path;
import org.neo4j.driver.v1.types.Relationship;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class RelationQueryDao {
//    @Autowired
//    private Driver neo4jDriver;
//    Session session = neo4jDriver.session();


    /**
     * 批量创建
     *
     * @throws Exception
     */

    public String RelationQuery(String sql) throws Exception {
        String relationQury = "";
        try {
            Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "neo4j"));
            Session session = driver.session();
            String cmdSql = sql;
            StatementResult result = session.run(cmdSql);
            while (result.hasNext()) {
                Record record = result.next();
                List<Value> values = record.values();
                Map<Long, Node> nodesMap = new HashMap<>();
                for (Value value : values) {
//                    if (value.type().name().equals("PATH")) {
                    Path p = value.asPath();
//                        System.out.println("小讯和小锐之间的关系最短路径长度为：" + p.length());
                    System.out.println("====================================");
                    Iterable<Node> nodes = p.nodes();
                    for (Node node : nodes) {
                        nodesMap.put(node.id(), node);
                    }

                    /**
                     * 打印最短路径里面的关系 == 关系包括起始节点的ID和末尾节点的ID，以及关系的type类型
                     */
                    Iterable<Relationship> relationships = p.relationships();
                    for (Relationship relationship : relationships) {
                        Long startID = relationship.startNodeId();
                        Long endID = relationship.endNodeId();
//                            String rType = relationship.type();
                        Map<String, Object> rType = relationship.asMap();
                        /**
                         * asMap 相当于 节点的properties属性信息
                         */

                        relationQury+=nodesMap.get(startID).asMap() + "-" + rType+ "-"
                                        + nodesMap.get(endID).asMap();

                    }
                }
            }
//            }
        } catch (Exception e) {
            System.err.println(e.getClass() + "," + e.getMessage());
            return "error";
        }
        return relationQury;
    }

}
