package com.alan.controller;

import com.alan.model.*;
import com.alan.service.*;
import com.alan.util.CaptchaUtil;
import com.alan.util.ChartHelper;
import com.alan.util.DateUtil;
import com.alan.util.RelationshipUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/parent")
public class ParentController {

    @Autowired
    AppService appService;
    @Autowired
    UserService userService;
    @Autowired
    ParentService parentService;
    @Autowired
    RelationService relationService;
    @Autowired
    UseStateService useStateService;
    @Autowired
    LocationService locationService;
    @Autowired
    UnlockService unlockService;

    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest request, HttpSession session) {
        Map<String, String> result = new HashMap<>();
        if (!CaptchaUtil.checkVerifyCode(request)) {
            model.addAttribute("login_result", "验证码错误");
            return "parent/login";
        } else {
            String parentTel = request.getParameter("username");
            //对密码进行MD5加密
            String parentPwd = DigestUtils.md5Hex(request.getParameter("password"));

            if (parentTel == "" || parentPwd == "") {
                model.addAttribute("login_result", "用户名和密码不能为空");
                return "parent/login";
            }
            //数据库检查
            Parent parent = parentService.getParentByTel(Long.parseLong(parentTel));
            if (parent != null) {
                if (parent.getParentPwd().trim().equals(parentPwd.trim())) {
                    //将用户信息存储到Session中
                    session.setAttribute("SESSION_USER", parent);
                    model.addAttribute("login_result", "登录成功");
                    return "forward:/parent/index";
                }
            }

            model.addAttribute("login_result", "用户名或密码错误");
            return "parent/login";
        }
    }

    //增
    @RequestMapping("/register")
    @ResponseBody
    public Map<String, String> register(@RequestParam long parentTel, @RequestParam String parentName,
                                        @RequestParam String parentPwd) {
        Map<String, String> res = new HashMap<>();
        if (parentService.getParentByTel(parentTel) != null) {
            res.put("status", "2");
            res.put("message", "该手机号已存在");
            return res;
        } else {
            Date parentRegTime = new Date();
            parentPwd = DigestUtils.md5Hex(parentPwd);
            parentService.addParent(parentTel, parentName, parentPwd, parentRegTime);
            res.put("status", "1");
            res.put("message", "添加成功");
            return res;
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("SESSION_USER", null);
        return "parent/login";
    }

    @RequestMapping("/index")
    public String index(Model model, HttpSession session) {
        List<Relation> relationList = new ArrayList<Relation>();
        Map<Integer, User> userMap = new HashMap<Integer, User>();
        relationList = relationService.getRelationByParentId(((Parent) session.getAttribute("SESSION_USER")).getParentId());
        for (Relation relation : relationList) {
            User user = userService.getUserById(relation.getUserId());
            if (user != null) {
                relation.setRelationship(RelationshipUtil.reverseRelationship(relation.getRelationship(), user.getUserGender()));
                userMap.put(relation.getRelationId(), user);
            }
        }
        int currentYear = Calendar.getInstance().get(Calendar.YEAR) - 1900;
        model.addAttribute("userMap", userMap);
        model.addAttribute("relationList", relationList);
        model.addAttribute("currentYear", currentYear);
        return "parent/index";
    }

    @RequestMapping("/myInfo")
    public String myInfo(Model model, HttpSession session) {
        int parentId = ((Parent) session.getAttribute("SESSION_USER")).getParentId();
        Parent parent = parentService.getParentById(parentId);
        model.addAttribute("parent", parent);
        return "parent/myInfo";
    }

    @RequestMapping("/editMyInfo")
    public String editMyInfo(HttpSession session, Model model) {
        Parent parent = (Parent) session.getAttribute("SESSION_USER");
        model.addAttribute("parent", parent);
        return "parent/editMyInfo";
    }

    @RequestMapping("/editParent")
    @ResponseBody
    public Map<String, String> editParent(HttpServletRequest request) {
        Map<String, String> res = new HashMap<>();
        int editType = Integer.parseInt(request.getParameter("editType"));
        int parentId = Integer.parseInt(request.getParameter("parentId"));
        if (editType == 0) {//修改电话
            long parentTel = Long.parseLong(request.getParameter("parentTel"));
            Parent parent = parentService.getParentById(parentId);
            if (parentService.getParentByTel(parentTel) != null) {
                res.put("status", "2");
                res.put("message", "该手机号已存在");
                return res;
            } else {
                parentService.editParentTel(parentId, parentTel);
                parentService.recordEdit(parentId, "parent_tel", String.valueOf(parent.getParentTel()), String.valueOf(parentTel), new Date());
                res.put("status", "1");
                res.put("message", "修改成功");
                return res;
            }
        } else if (editType == 1) {//修改密码
            String oldPwd = request.getParameter("oldPwd");
            oldPwd = DigestUtils.md5Hex(oldPwd);
            if (oldPwd.equals(parentService.getParentById(parentId).getParentPwd())) {
                String parentPwd = request.getParameter("parentPwd");
                parentPwd = DigestUtils.md5Hex(parentPwd);
                parentService.editParentPwd(parentId, parentPwd);
                parentService.recordEdit(parentId, "parent_pwd", oldPwd, parentPwd, new Date());
                res.put("status", "1");
                res.put("message", "修改成功");
                return res;
            } else {
                res.put("status", "2");
                res.put("message", "原密码错误");
                return res;
            }
        }
        res.put("status", "2");
        res.put("message", "出现错误，请稍后再试");
        return res;
    }

    @RequestMapping("/bindChild")
    @ResponseBody
    public Map<String, String> bindChild(@RequestParam long userTel, @RequestParam String userPwd,
                                         @RequestParam String relationship, HttpSession session) {
        Map<String, String> res = new HashMap<>();
        userPwd = DigestUtils.md5Hex(userPwd);
        User user = userService.getUserByTel(userTel);
        if (user != null) {
            if (user.getUserPwd().equals(userPwd)) {
                int parentId = ((Parent) session.getAttribute("SESSION_USER")).getParentId();
                if (relationService.getRelationByParentIdAndUserId(parentId, user.getUserId()) != null) {
                    res.put("status", "2");
                    res.put("message", "已绑定过该用户，无法重复绑定");
                    return res;
                }
                Date relateTime = new Date();
                relationService.addRelation(parentId, user.getUserId(), relationship, relateTime);
                Relation relation = relationService.getRelationByParentIdAndUserId(parentId,user.getUserId());
                relationService.recordEdit(relation.getRelationId(),parentId,user.getUserId(),"add_relation",null,relationship,relateTime);
                res.put("status", "1");
                res.put("message", "绑定成功");
                return res;
            }
        }
        res.put("status", "2");
        res.put("message", "用户名或密码错误");
        return res;
    }

    @RequestMapping("/unbindChild")
    @ResponseBody
    public Map<String, String> unbindChild(@RequestParam int relationId, HttpSession session) {
        Map<String, String> res = new HashMap<>();
        Relation relation = relationService.getRelationById(relationId);
        relationService.delRelation(relationId);
        relationService.recordEdit(relationId,relation.getParentId(),relation.getUserId(),"del_relation",relation.getRelationship(),null,new Date());
        res.put("status", "1");
        res.put("message", "解绑成功");
        return res;
    }

    @RequestMapping("/showData")
    public String showData(@RequestParam int userId, @RequestParam String formatDate,
                           Model model, HttpSession session) throws JsonProcessingException, ParseException {
        int parentId = ((Parent) session.getAttribute("SESSION_USER")).getParentId();
        if (relationService.getRelationByParentIdAndUserId(parentId, userId) == null) {
            return "forward:/parent/index";
        }
        User user = userService.getUserById(userId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(simpleDateFormat.parse(formatDate).getTime());
        Date startTime = DateUtil.getNeedTime(date, 0, 0, 0, 0);
        Date endTime = DateUtil.getNeedTime(startTime, 23, 59, 59, 0);
        List<UseState> useStateList = useStateService.getUseStateByUserIdAndDate(userId, startTime, endTime);
        List<Location> locationList = locationService.getLocationByUserIdAndDate(userId, startTime, endTime);
        List<Unlock> unlockList = unlockService.getUnlockByUserIdAndDate(userId, startTime, endTime);
        List<App> appList = new ArrayList<App>();
        for (UseState useState : useStateList) {
            App app = appService.getAppById(useState.getAppId());
            int flag = 0;
            int i = 0;
            for (App appTemp : appList) {
                if (appTemp.getAppId() == app.getAppId()) {
                    flag = 1;
                    break;
                }
                i++;
            }
            if (flag == 0) {
                List<UseState> useStateListTemp = app.getUseStateList();
                useStateListTemp.add(useState);
                app.setUseStateList(useStateListTemp);
                appList.add(app);
            } else {
                List<UseState> useStateListTemp = appList.get(i).getUseStateList();
                useStateListTemp.add(useState);
                app.setUseStateList(useStateListTemp);
                appList.set(i, app);
            }

        }
        int totalUseTime = 0;
        List<String> barDataList = new ArrayList<String>();
        for (App app : appList) {
            int useTime = 0;
            ChartHelper barHelper = new ChartHelper();
            barHelper.setName(app.getAppName());
            List<Object> data = new ArrayList<Object>();
            for (int j = 0; j < 24; j++) {
                data.add(0);
            }
            for (UseState useState : app.getUseStateList()) {
                Date startMoment = useState.getStartTime();
                Date endMoment = useState.getEndTime();
                Calendar calBegin = Calendar.getInstance();
                calBegin.setTime(startMoment);
                Calendar calFinish = Calendar.getInstance();
                calFinish.setTime(endMoment);
                int begin = calBegin.get(Calendar.HOUR_OF_DAY);
                int finish = calFinish.get(Calendar.HOUR_OF_DAY);
                for (int i = begin; i <= finish; i++) {
                    Date startLimit = DateUtil.getNeedTime(startTime, i, 0, 0, 0);
                    Date endLimit = DateUtil.getNeedTime(startLimit, i, 59, 59, 0);
                    data.set(i, DateUtil.getDuration(startMoment, endMoment, startLimit, endLimit));
                    totalUseTime += (int) data.get(i);
                    useTime += (int) data.get(i);
                }

            }
            app.setUseTime(useTime);
            barHelper.setData(data);
            ObjectMapper mapper = new ObjectMapper();
            barDataList.add(mapper.writeValueAsString(barHelper));
        }
        Collections.sort(appList, new Comparator<App>() {
            @Override
            public int compare(App app1, App app2) {
                int useTime1 = app1.getUseTime();
                int useTime2 = app2.getUseTime();
                if (useTime1 == useTime2) {
                    return 0;
                } else if (useTime1 > useTime2) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        List<String> pieDataList = new ArrayList<String>();
        ChartHelper pieHelper = new ChartHelper();
        pieHelper.setName("应用名");
        List<Object> pieData = new ArrayList<Object>();
        for (App app : appList) {
            Map<String, Object> appMap = new HashMap<String, Object>();
            appMap.put("name", app.getAppName());
            appMap.put("y", app.getUseTime());
            pieData.add(appMap);
        }
        pieHelper.setData(pieData);
        ObjectMapper mapper = new ObjectMapper();
        pieDataList.add(mapper.writeValueAsString(pieHelper));
        List<Type> typeList = new ArrayList<Type>();
        for (App app : appList) {
            int i = 0;
            int flag = 0;
            for (Type type : typeList) {
                if (type.getName().equals(app.getAppType())) {
                    flag = 1;
                    break;
                }
                i++;
            }
            Type type = new Type();
            if (flag == 0) {
                type.setName(app.getAppType());
                type.setTypeUseTime(app.getUseTime());
                typeList.add(type);
            } else {
                type = typeList.get(i);
                int typeUseTime = type.getTypeUseTime() + app.getUseTime();
                type.setTypeUseTime(typeUseTime);
                typeList.set(i, type);
            }
        }

        Collections.sort(typeList, new Comparator<Type>() {
            @Override
            public int compare(Type type1, Type type2) {
                int useTime1 = type1.getTypeUseTime();
                int useTime2 = type2.getTypeUseTime();
                if (useTime1 == useTime2) {
                    return 0;
                } else if (useTime1 > useTime2) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        List<String> pieDataList2 = new ArrayList<String>();
        ChartHelper pieHelper2 = new ChartHelper();
        pieHelper.setName("类别名");
        List<Object> pieData2 = new ArrayList<Object>();
        for (Type type : typeList) {
            Map<String, Object> typeMap = new HashMap<String, Object>();
            typeMap.put("name", type.getName());
            typeMap.put("y", type.getTypeUseTime());
            pieData2.add(typeMap);
        }
        pieHelper2.setData(pieData2);
        ObjectMapper mapper2 = new ObjectMapper();
        pieDataList2.add(mapper2.writeValueAsString(pieHelper2));

//        Location location = new Location();
//        location.setLatitude(0);
//        location.setLongitude(0);
//        location.setStartTime(new Date());
//        location.setEndTime(new Date());
//        locationList.add(location);
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
        for (Location locationTemp : locationList) {
            String locationDescription = "'" + sf.format(locationTemp.getStartTime()) + "~" + sf.format(locationTemp.getEndTime()) + "'";
            locationTemp.setDescription(locationDescription);
        }
//        for (int i = 0;i<locationList.size();i++){
//            System.err.println(locationList.get(i).getDescription());
//        }

        model.addAttribute("user", user);
        model.addAttribute("date", date);
        model.addAttribute("locationList", locationList);
        model.addAttribute("unlockList", unlockList);
        model.addAttribute("appList", appList);
        model.addAttribute("barDateList", barDataList);
        model.addAttribute("pieDateList", pieDataList);
        model.addAttribute("pieDateList2", pieDataList2);
        model.addAttribute("totalUseTime", totalUseTime);
        model.addAttribute("typeList", typeList);
        return "parent/showData";
    }

    @RequestMapping("/showReport")
    public String showReport(@RequestParam int userId, Model model, HttpSession session) throws JsonProcessingException {
        int parentId = ((Parent) session.getAttribute("SESSION_USER")).getParentId();
        if (relationService.getRelationByParentIdAndUserId(parentId, userId) == null) {
            return "forward:/parent/index";
        }
        User user = userService.getUserById(userId);
        user.setUserPwd("");
        Date thisWeek[] = new Date[7];
        Date lastWeek[] = new Date[7];
        List<UseState> useStateThisWeek = new ArrayList<UseState>();
        List<UseState> useStateLastWeek = new ArrayList<UseState>();
        List<UseState> useStatesAll = useStateService.getUseStateByUserId(userId);
        for (int i = 0; i < 7; i++) {
            Date dateThisWeek = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * (6 - i));
            Date dateLastWeek = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * (13 - i));
            thisWeek[i] = DateUtil.getNeedTime(dateThisWeek, 0, 0, 0, 0);
            lastWeek[i] = DateUtil.getNeedTime(dateLastWeek, 0, 0, 0, 0);
        }
        for (int i = 0; i < 7; i++) {
            useStateThisWeek.addAll(useStateService.getUseStateByUserIdAndDate(userId,thisWeek[i], DateUtil.getNeedTime(thisWeek[i], 23, 59, 59, 0)));
            useStateLastWeek.addAll( useStateService.getUseStateByUserIdAndDate(userId,lastWeek[i], DateUtil.getNeedTime(lastWeek[i], 23, 59, 59, 0)));
        }
        int totalUseTime = 0;
        for (UseState useState : useStatesAll){
            Date startMoment = useState.getStartTime();
            Date endMoment = useState.getEndTime();
            totalUseTime = totalUseTime + DateUtil.getDuration(startMoment,endMoment,startMoment,endMoment);
        }
        int totalAverage = 0;
        if(!useStatesAll.isEmpty()){
            int totalDuringDay = DateUtil.getDuringDay(useStatesAll.get(0).getStartTime(),useStatesAll.get(useStatesAll.size()-1).getEndTime())+1;
            totalAverage = totalUseTime/totalDuringDay;
        }


        int thisWeekUseTime[] = new int[7];
        int lastWeekUseTime[] = new int[7];
        int thisWeekTotalUseTime = 0;
        int lastWeekTotalUseTime = 0;
        int thisWeekNightTime[] = new int[7];
        int lastWeekNightTime[] = new int[7];
        int thisWeekTotalNightTime = 0;
        int lastWeekTotalNightTime = 0;
        for(UseState useState: useStateThisWeek){
            Date startMoment = useState.getStartTime();
            Date endMoment = useState.getEndTime();
            for(int i=0;i<7;i++){
                thisWeekUseTime[i] = thisWeekUseTime[i]+DateUtil.getDuration(startMoment,endMoment,thisWeek[i],DateUtil.getNeedTime(thisWeek[i], 23, 59, 59, 0));
                thisWeekNightTime[i] = thisWeekNightTime[i]+DateUtil.getDuration(startMoment,endMoment,thisWeek[i],DateUtil.getNeedTime(thisWeek[i], 5, 59, 59, 0));
                thisWeekNightTime[i] = thisWeekNightTime[i]+DateUtil.getDuration(startMoment,endMoment,DateUtil.getNeedTime(thisWeek[i], 23, 0, 0, 0),DateUtil.getNeedTime(thisWeek[i], 23, 59, 59, 0));
            }
        }

        for(UseState useState: useStateLastWeek){
            Date startMoment = useState.getStartTime();
            Date endMoment = useState.getEndTime();
            for(int i=0;i<7;i++){
                lastWeekUseTime[i] = lastWeekUseTime[i]+DateUtil.getDuration(startMoment,endMoment,lastWeek[i],DateUtil.getNeedTime(lastWeek[i], 23, 59, 59, 0));
                lastWeekNightTime[i] = lastWeekNightTime[i]+DateUtil.getDuration(startMoment,endMoment,lastWeek[i],DateUtil.getNeedTime(lastWeek[i], 6, 0, 0, 0));
                lastWeekNightTime[i] = lastWeekNightTime[i]+DateUtil.getDuration(startMoment,endMoment,DateUtil.getNeedTime(lastWeek[i], 23, 0, 0, 0),DateUtil.getNeedTime(lastWeek[i], 23, 59, 59, 0));
            }
        }


        for (int i=0;i<7;i++){
            thisWeekTotalUseTime += thisWeekUseTime[i];
            lastWeekTotalUseTime += lastWeekUseTime[i];
            thisWeekTotalNightTime += thisWeekNightTime[i];
            lastWeekTotalNightTime += lastWeekNightTime[i];
        }
        int thisWeekAverage = thisWeekTotalUseTime/7;

        SimpleDateFormat sp = new SimpleDateFormat("E");
        String thisWeekStr[] = new String[7];
        for (int i = 0; i < 7; i++) {
            thisWeekStr[i] = sp.format(thisWeek[i]);
        }
        ObjectMapper mapper = new ObjectMapper();
        String lastSevenDays = mapper.writeValueAsString(thisWeekStr);
        String useTime = mapper.writeValueAsString(thisWeekUseTime);


        List<App> appList = new ArrayList<App>();
        for (UseState useState : useStateThisWeek) {
            App app = appService.getAppById(useState.getAppId());
            int flag = 0;
            int i = 0;
            for (App appTemp : appList) {
                if (appTemp.getAppId() == app.getAppId()) {
                    flag = 1;
                    break;
                }
                i++;
            }
            if (flag == 0) {
                List<UseState> useStateListTemp = app.getUseStateList();
                useStateListTemp.add(useState);
                app.setUseStateList(useStateListTemp);
                appList.add(app);
            } else {
                List<UseState> useStateListTemp = appList.get(i).getUseStateList();
                useStateListTemp.add(useState);
                app.setUseStateList(useStateListTemp);
                appList.set(i, app);
            }

        }
        for (App app : appList) {
            int appUseTime = 0;
            for (UseState useState : app.getUseStateList()) {
                Date startMoment = useState.getStartTime();
                Date endMoment = useState.getEndTime();
                appUseTime = appUseTime+DateUtil.getDuration(startMoment,endMoment,startMoment,endMoment);
            }
            app.setUseTime(appUseTime);
        }
        Collections.sort(appList, new Comparator<App>() {
            @Override
            public int compare(App app1, App app2) {
                int useTime1 = app1.getUseTime();
                int useTime2 = app2.getUseTime();
                if (useTime1 == useTime2) {
                    return 0;
                } else if (useTime1 > useTime2) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        int count = 0;
        if(appList.size()>=5){
            count = 5;
        }else{
            count = appList.size();
        }
        List<App> appListTop5 = new ArrayList<App>();
        for (int i = 0;i<count;i++){
            appListTop5.add(appList.get(i));
        }
        List<Type> typeList = new ArrayList<Type>();
        for (App app : appList) {
            int i = 0;
            int flag = 0;
            for (Type type : typeList) {
                if (type.getName().equals(app.getAppType())) {
                    flag = 1;
                    break;
                }
                i++;
            }
            Type type = new Type();
            if (flag == 0) {
                type.setName(app.getAppType());
                type.setTypeUseTime(app.getUseTime());
                typeList.add(type);
            } else {
                type = typeList.get(i);
                int typeUseTime = type.getTypeUseTime() + app.getUseTime();
                type.setTypeUseTime(typeUseTime);
                typeList.set(i, type);
            }
        }
        Collections.sort(typeList, new Comparator<Type>() {
            @Override
            public int compare(Type type1, Type type2) {
                int useTime1 = type1.getTypeUseTime();
                int useTime2 = type2.getTypeUseTime();
                if (useTime1 == useTime2) {
                    return 0;
                } else if (useTime1 > useTime2) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        if(typeList.size()>=5){
            count = 5;
        }else{
            count = typeList.size();
        }
        List<Type> typeListTop5 = new ArrayList<Type>();
        for (int i = 0;i<count;i++){
            typeListTop5.add(typeList.get(i));
        }


        int thisWeekCount = useStateThisWeek.size();
        int lastWeekCount = useStateLastWeek.size();
        List<Unlock> thisWeekUnlock = unlockService.getUnlockByUserIdAndDate(userId,thisWeek[0],DateUtil.getNeedTime(thisWeek[6],23,59,59,0));
        List<Unlock> lastWeekUnlock = unlockService.getUnlockByUserIdAndDate(userId,lastWeek[0],DateUtil.getNeedTime(lastWeek[6],23,59,59,0));
        model.addAttribute("user", user);
        model.addAttribute("lastWeek", lastSevenDays);
        model.addAttribute("useTime", useTime);
        model.addAttribute("thisWeekTotal", thisWeekTotalUseTime);
        model.addAttribute("lastWeekTotal", lastWeekTotalUseTime);
        model.addAttribute("thisWeekCount", thisWeekCount);
        model.addAttribute("lastWeekCount", lastWeekCount);
        model.addAttribute("thisWeekUnlock", thisWeekUnlock.size());
        model.addAttribute("lastWeekUnlock", lastWeekUnlock.size());
        model.addAttribute("thisWeekAverage",thisWeekAverage);
        model.addAttribute("totalAverage",totalAverage);
        model.addAttribute("appListTop5",appListTop5);
        model.addAttribute("typeListTop5",typeListTop5);
        model.addAttribute("appList",appList);
        model.addAttribute("typeList",typeList);
        model.addAttribute("thisWeekTotalNightTime",thisWeekTotalNightTime);
        model.addAttribute("lastWeekTotalNightTime",lastWeekTotalNightTime);

        return "parent/showReport";
    }
}
