package xyz.nongfah.home.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.nongfah.home.model.Flow;
import xyz.nongfah.home.service.HomeService;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static base.utils.CookieUtil.getName;

@Controller
public class HomeController {
    @Autowired
    private HomeService homeService;


    /**
     * 打卡页面
     * @return
     */
    @RequestMapping(value = "index")
    public String index() {
        return "redirect:/";
    }

    /**
     * 打卡ajax方法
     * @param request
     * @param flow
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "doFlowChange", method = RequestMethod.POST)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public Map doFlowChange(HttpServletRequest request, Flow flow) throws Exception {
        Map model = new HashMap();
        try {
            String userName = getName(request);
            if (userName == "" || userName == null) {
                model.put("respCode", 2);
                return model;
            }
            flow.setUserName(userName);
            model.put("respCode", homeService.doFlowChange(flow));
            return model;
        } catch (Exception e) {
            throw new Exception("HomeController flowInsert", e);
        }
    }

    /**
     * 人员管理页面
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "admin")
    public String nameList(HttpServletRequest request, Map model) throws Exception {
        try {
            Map params = new HashMap();

            String userName = getName(request);
            params.put("userName", userName);
            Map dataMap = homeService.nameList(params);

            model.put("nameListYes", dataMap.get("nameListYes"));
            model.put("nameListNo", dataMap.get("nameListNo"));
            return "admin";
        } catch (Exception e) {
            throw new Exception("HomeController orderWeight", e);
        }
    }

    /**
     * 排行榜页面
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "rank")
    public String orderWeight(HttpServletRequest request, Map model) throws Exception {
        try {
            String userName = getName(request);

            Map param = new HashMap();
            param.put("userName", userName);
            model.putAll(homeService.rankDay(param) );
            model.putAll(homeService.rankWeek(param) );
            model.putAll(homeService.rankMonth(param) );
            return "rank";
        } catch (Exception e) {
            throw new Exception("HomeController orderWeight", e);
        }
    }

    /**
     * home页面
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "home")
    public String home(HttpServletRequest request, Map model) throws Exception {
        try {

            String userName = getName(request);
            model.put("userName", userName);
        } catch (Exception e) {
            throw new Exception("HomeController home", e);
        }
        return "home";
    }

    /**
     * 机器人--打卡
     * @param request
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "doFlowChangeBot", method = RequestMethod.POST)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public Map doFlowChangeBot(HttpServletRequest request, @RequestBody String requestBody) {
        Map model = new HashMap();
        JSONObject json = JSONObject.parseObject(requestBody);
        Flow flow = new Flow();
        String userName = json.get("userName").toString();
        flow.setUserName(userName);
        if (json.get("name").toString().equals("")
                ||  json.get("weight").toString().equals("")
                ||  json.get("userName").toString().equals("")    ) {
            model.put("respCode", 3);
            return model;
        }
        flow.setName(json.get("name").toString());
        flow.setBreakfast(json.get("breakfast").toString());
        flow.setLunch(json.get("lunch").toString());
        flow.setFruits(json.get("fruits").toString());
        flow.setDinner(json.get("dinner").toString());
        flow.setDefecation(Integer.valueOf(json.get("defecation").toString()));
        flow.setWater(Integer.valueOf(json.get("water").toString()));
        flow.setWeight(Float.valueOf(json.get("weight").toString()));
        flow.setTips(json.get("tips").toString());

        model.put("respCode", homeService.doFlowChange(flow));
        return model;

    }

    /**
     * 机器人--今日打卡
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "adminBot", method = RequestMethod.POST)
    @ResponseBody
    public Map adminBot(HttpServletRequest request ,@RequestBody String requestBody) throws Exception {
        try {
            Map params = new HashMap();
            Map model = new HashMap();
            JSONObject json = JSONObject.parseObject(requestBody);
            String userName = json.get("userName").toString();
            params.put("userName", userName);
            Map dataMap = homeService.nameList(params);

            model.put("nameListYes", dataMap.get("nameListYes"));
            model.put("nameListNo", dataMap.get("nameListNo"));
            return model;
        } catch (Exception e) {
            throw new Exception("HomeController orderWeight", e);
        }
    }

    /**
     * 机器人--排行
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "rankBot", method = RequestMethod.POST)
    @ResponseBody
    public Map orderWeightBot(HttpServletRequest request,@RequestBody String requestBody) throws Exception {
        try {
            JSONObject json = JSONObject.parseObject(requestBody);
            Map param = new HashMap();
            Map model = new HashMap();
            String userName = json.get("userName").toString();
            if(userName.equals("")){
                return model;
            }

            param.put("userName", userName);
            String type = json.get("type").toString();
            switch (type) {
                case "日排行":
                    model.put("data", homeService.rankDay(param).get("orderWeightDay"));
                    break;
                case "周排行":
                    model.put("data", homeService.rankWeek(param).get("orderWeightWeek"));
                    break;
                case "月排行":
                    model.put("data", homeService.rankMonth(param).get("orderWeightMonth"));
                    break;
            }
            return model;
        } catch (Exception e) {
            throw new Exception("HomeController orderWeight", e);
        }
    }

}
