package com.relatives.demo.config;

import org.neo4j.driver.internal.InternalNode;
import org.neo4j.driver.internal.InternalRelationship;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Path;
import org.neo4j.driver.v1.types.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Neo4jUtil {
    private static Driver driver;

    @Autowired
    public Neo4jUtil(Driver driver) {
        Neo4jUtil.driver = driver;
    }

    /**
     * cql 多跳路径查询 返回节点和关系
     *
     * @param cql      查询语句
     * @param nodeList 节点
     * @param edgeList 关系
     * @return List<Map < String, Object>>
     */
    public  void getPathList(String cql, Set<Map<String, Object>> nodeList, Set<Map<String, Object>> edgeList) {
        try {
            Session session = driver.session();
            StatementResult result = session.run(cql);
            List<Record> list = result.list();
//            Map<String, Object> nodesmap=new HashMap<>();
            for (Record r : list) {
                for (String index : r.keys()) {
                    Path path = r.get(index).asPath();
                    //节点
                    Iterable<Node> nodes = path.nodes();
                    for (Iterator iter = nodes.iterator(); iter.hasNext(); ) {
                        InternalNode nodeInter = (InternalNode) iter.next();
                        Map<String, Object> nodemap = new HashMap<>();
                        //节点上设置的属性
                        nodemap.putAll(nodeInter.asMap());
                        //外加一个固定属性
                        nodemap.put("nodeId", nodeInter.id());
                        nodeList.add(nodemap);
                    }
                    //关系
                    Iterable<Relationship> edges = path.relationships();
                    for (Iterator iter = edges.iterator(); iter.hasNext(); ) {
                        InternalRelationship relationInter = (InternalRelationship) iter.next();
                        Map<String, Object> map = new HashMap<>();
                        map.putAll(relationInter.asMap());
                        //关系上设置的属性
                        Long start = relationInter.startNodeId();
                        Long end = relationInter.endNodeId();
                        Object edgeFrom = null;
                        Object edgeTo = null;
                        Object fresult = null;
                        for (Map<String, Object> map1List : nodeList) {
                            fresult = null;
                            for (String key : map1List.keySet()) {
                                if (key.equals("name")) fresult = map1List.get(key);
                                if (key.equals("nodeId")) {
                                    //    System.out.println(map1List.get(key));
                                    if (start.equals(map1List.get(key))) {
                                        if (fresult != null) edgeFrom = fresult;
                                        else edgeFrom = map1List.get("name");
                                    }
                                    if (end.equals(map1List.get(key))) {
                                        if (fresult != null) edgeTo = fresult;
                                        else edgeTo = map1List.get("name");
                                    }
                                    //System.out.println( key + "-->" + map1List.get(key) );
                                }
                            }
                        }
                        map.put("edgeId", relationInter.id());
                        map.put("edgeFrom", edgeFrom);
                        map.put("edgeTo", edgeTo);
//                        map.put("edgeFrom", relationInter.startNodeId());
//                        map.put("edgeTo", relationInter.endNodeId());
                        edgeList.add(map);
                    }
                }

            }
            session.close();
          //  driver.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改节点的信息
     */

    public static Object alternode(String cql,Object alter) {
        try {
            Map<List<String>, Object> map=new HashMap<>();
            Session session = driver.session();
            StatementResult result = session.run(cql);
            while(result.hasNext()){
                Record record=result.next();
                List<String> keys = record.keys();
                for (int i=0;i<keys.size();i++) {
                    Object value = record.get(keys.get(i));
                    alter=value;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return alter;
    }


    /**
     * cql的return返回多种节点match (n)-[edge]-(n) return n,m,edge：限定返回关系时，关系的别名必须“包含”edge
     *
     * @param cql   查询语句
     * @param lists 和cql的return返回节点顺序对应
     * @return List<Map < String, Object>>
     */
    public static <T> void getList(String cql, Set<T>... lists) {
        //用于给每个Set list赋值
        int listIndex = 0;
        try {
            Session session = driver.session();
            StatementResult result = session.run(cql);
            List<Record> list = result.list();
            for (Record r : list) {
                if (r.size() != lists.length) {
                    System.out.println("节点数和lists数不匹配");
                    return;
                }
            }
            for (Record r : list) {
                for (String index : r.keys()) {
                    //对于关系的封装
                    if (index.indexOf("认识") != -1) {
                        Map<String, Object> map = new HashMap<>();
                        //关系上设置的属性
                        map.putAll(r.get(index).asMap());
                        //外加三个固定属性
                        map.put("edgeId", r.get(index).asRelationship().id());
                        map.put("edgeFrom", r.get(index).asRelationship().startNodeId());
                        map.put("edgeTo", r.get(index).asRelationship().endNodeId());
                        lists[listIndex++].add((T) map);
                    }
                    //对于节点的封装
                    else {
                        Map<String, Object> map = new HashMap<>();
                        //节点上设置的属性
                        map.putAll(r.get(index).asMap());
                        //外加一个固定属性
                        map.put("nodeId", r.get(index).asNode().id());
                        lists[listIndex++].add((T) map);
                    }
                }
                listIndex = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
