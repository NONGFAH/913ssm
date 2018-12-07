package xyz.nongfah.home.dao;

import base.annotation.MybatisDao;
import xyz.nongfah.home.model.Flow;

import java.util.List;
import java.util.Map;

@MybatisDao
public interface FlowMapper {

    Integer countUserName(Flow flow);

    void insertFlow(Flow flow);
    void updateFlow(Flow flow);

    List<Map> nameListYes(Map param);
    List<Map> nameListNo(Map param);

    List<Map> orderWeightDay(Map param);
    List<Map> orderWeightWeek(Map param);
    List<Map> orderWeightMonth(Map param);

    float selectLastWeight(String userName );
    List<Map> orderUserName(Map param);




}
