package xyz.nongfah.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.nongfah.home.dao.FlowMapper;
import xyz.nongfah.home.model.Flow;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeService {
    @Autowired
    FlowMapper flowMapper;


    /**
     * 打卡
     * @param flow
     * @return
     */
    @Transactional
    public int doFlowChange(Flow flow)   {
        try {
            if( flowMapper.countUserName(flow) ==0){
                //插入
                System.out.println("插入");
                flowMapper.insertFlow(flow);
                return 0;
            }
            else{
                //更新
                System.out.println("更新");
                flowMapper.updateFlow(flow);
                return 1;
            }
        } catch (Exception e) {
            return 3;
        }
    }


    /**
     * 人员管理页面人员列表
     * @param params
     * @return reMap    打卡人员  +  未打卡人员
     */
    @Transactional
    public Map nameList(Map params)  {

        Map reMap = new HashMap();
        reMap.put("nameListYes",flowMapper.nameListYes(params));
        reMap.put("nameListNo",flowMapper.nameListNo(params));
        return reMap;
    }

    /**
     * 日排行
     * @param params
     * @return
     */
    @Transactional
    public Map rankDay(Map params)  {
        Map reMap = new HashMap();
        reMap.put("orderWeightDay",flowMapper.orderWeightDay(params) );
        return reMap;
    }
    /**
     * 周排行
     * @param params
     * @return
     */
    @Transactional
    public Map rankWeek(Map params)  {
        Map reMap = new HashMap();
        reMap.put("orderWeightWeek", flowMapper.orderWeightWeek(params));
        return reMap;
    }
    /**
     * 月排行
     * @param params
     * @return
     */
    @Transactional
    public Map rankMonth(Map params)  {
        Map reMap = new HashMap();
        reMap.put("orderWeightMonth", flowMapper.orderWeightMonth(params));
        return reMap;
    }




















    public float selectLastWeight(String userName ){
        return flowMapper.selectLastWeight( userName);
    }







}
