package com.relatives.demo.service;

import com.relatives.demo.dao.RelationFindDao;
import org.neo4j.driver.internal.InternalNode;
import org.neo4j.driver.internal.InternalRelationship;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Path;
import org.neo4j.driver.v1.types.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RelationFindService {
    @Autowired
    RelationFindDao relationFindDao;

    public void getpathList(List<Map<String, Object>> list, int pathSize, String name1, String name2) {
        StatementResult result = relationFindDao.getPathList(name1, name2);
        while (result.hasNext()) {
            Record record = result.next();
            List<Value> values = record.values();
            for (Value value : values) {
                String type = value.type().name();
                if (!type.equals("INTEGER")) {
                    for (int i = 0; i < value.size(); i++) {
                        Relationship relationship = value.get(i).asRelationship();
                        list.add(relationship.asMap());
                    }
                } else {
                    pathSize = value.asInt();
                    System.out.println(pathSize);
                }
            }
            break;
        }
        rule(list, pathSize,name1,name2);
    }

    public void rule(List<Map<String, Object>> list, int pathSize,String name1, String name2) {
        String path="";
        System.out.println(pathSize);
        String cql;
        for( int i = 0; i < pathSize; i++ ) {
           path=path+list.get(i).get("relation");
           System.out.println(path);
        }
        String[] str = new String[]{"父亲","母亲","女儿","儿子","妻子","丈夫","哥哥","姐姐","弟弟","妹妹"};
        if (pathSize == 2) {
            switch(path){
                case "儿子女儿":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'孙女'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "女儿女儿":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'外孙女'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "女儿儿子":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'外孙'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "儿子儿子":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'孙子'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "父亲哥哥":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'伯父'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "父亲姐姐":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'姑姑'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "父亲弟弟":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'叔叔'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "母亲姐姐":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'姨妈'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "母亲妹妹":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'姨妈妈'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "母亲哥哥":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'舅舅'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "母亲弟弟":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'舅舅 '}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "哥哥妻子":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'嫂子'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "妻子父亲":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'岳父'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "父亲父亲":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'爷爷'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "父亲母亲":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'奶奶'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "妻子母亲":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'岳母'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "母亲父亲":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'外公'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "母亲母亲":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'外婆'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "丈夫父亲":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'公公'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "丈夫母亲":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'婆婆'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "儿子妻子":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'媳妇'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "女儿丈夫":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'女婿'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "姐姐儿子":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'外甥'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "姐姐女儿":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'外甥女'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "哥哥儿子":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'侄子'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "弟弟儿子":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'侄子'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "哥哥女儿":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'侄女'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "弟弟女儿":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'侄女'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                default:
                    System.out.println("无亲属关系！");
                    break;

            }
        }
        if (pathSize == 3) {
            switch(path){
                case "儿子妻子父亲":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'亲家公'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "儿子妻子母亲":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'亲家母'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "儿子儿子儿子":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'曾孙子'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "儿子儿子女儿":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'曾孙女'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "儿子儿子妻子":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'孙媳妇'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "儿子女儿丈夫":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'孙女婿'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "女儿丈夫父亲":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'亲家公  '}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "女儿丈夫母亲":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'亲家母  '}]->(m)";
                    relationFindDao.addRelation(cql);
                case "女儿儿子儿子":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'增外孙'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "女儿儿子女儿":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'增外孙女'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "女儿女儿女儿":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'增外孙女（姥姥）'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "父亲哥哥妻子":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'伯母'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "父亲哥哥儿子":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'堂哥'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "父亲哥哥女儿":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'堂姐'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "父亲弟弟儿子":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'堂弟'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "父亲弟弟女儿":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'堂妹'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "父亲姐姐女儿":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'表姐'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "父亲姐姐儿子":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'表哥'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "父亲姐姐丈夫":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'姑父'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "母亲哥哥妻子":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'舅妈'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "母亲哥哥儿子":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'表哥'}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                case "母亲哥哥女儿":
                    cql="MATCH (n),(m) WHERE n.name="+"'"+name1+"'"+" AND m.name="+"'"+name2+"'"+"merge(n)-[r:relation{relation:'表姐 '}]->(m)";
                    relationFindDao.addRelation(cql);
                    break;
                default:
                    System.out.println("无亲属关系！");
                    break;

            }

        }
    }


    public void relationFind(String cql, Set<Map<String, Object>> nodeList, Set<Map<String, Object>> edgeList) {
        List<Record> list = relationFindDao.getShortParth(cql);
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
                                if (start.equals(map1List.get(key))) {
                                    if (fresult != null) edgeFrom = fresult;
                                    else edgeFrom = map1List.get("name");
                                }
                                if (end.equals(map1List.get(key))) {
                                    if (fresult != null) edgeTo = fresult;
                                    else edgeTo = map1List.get("name");
                                }
                            }
                        }
                    }
                    map.put("edgeId", relationInter.id());
                    map.put("edgeFrom", edgeFrom);
                    map.put("edgeTo", edgeTo);
                    edgeList.add(map);
                }
            }
        }
    }

}
