package com.alan.controller;

import com.alan.model.Location;
import com.alan.model.Unlock;
import com.alan.model.UseState;
import com.alan.service.*;
import com.alan.model.User;
import com.alan.util.CrawlerUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by AlanHou on 2019/4/14.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    //private Logger log = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UseStateService useStateService;
    @Autowired
    private AppService appService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private UnlockService unlockService;
    @Autowired
    private ParentService parentService;
    @Autowired
    private RelationService relationService;

    @RequestMapping("/register")
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long userTel = Long.parseLong(request.getParameter("userTel"));
        String userName = request.getParameter("userName");
        String userPwd = request.getParameter("userPwd");
        long userBirthdayTimestamp = Long.parseLong(request.getParameter("userBirthday"));
        Date userBirthday = new Date(userBirthdayTimestamp);
        int userGender = Integer.parseInt(request.getParameter("userGender"));
        String userJob = request.getParameter("userJob");
        if(userService.getUserByTel(userTel)!=null){
            response.getWriter().print("用户已存在");
        }else{
            Date userRegTime = new Date();
            userService.addUser(userTel,userName,userPwd,userBirthday,userGender,userJob,userRegTime);
            response.getWriter().print("注册成功");
        }
    }

    @RequestMapping("/editUserInfo")
    public void editUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        long userTel = Long.parseLong(request.getParameter("userTel"));
        String userJob = request.getParameter("userJob");
        if(userService.getUserById(userId)!=null){
            User user = userService.getUserByTel(userTel);
            if((user!=null)&&(user.getUserId()!=userId)){
                response.getWriter().print("该手机号已存在");
            }else{
                userService.editUserInfo(userId,userTel,userJob);
                response.getWriter().print("修改成功");
            }
        }else {
            response.getWriter().print("修改失败");
        }
    }

    @RequestMapping("/editUserPwd")
    public void editUserPwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String oldPwd = request.getParameter("oldPwd");
        User user = userService.getUserById(userId);
        if(user!=null){
            if (oldPwd.equals(user.getUserPwd())) {
                String userPwd = request.getParameter("userPwd");
                userService.editUserPwd(userId, userPwd);
                response.getWriter().print("修改成功");
            } else {
                response.getWriter().print("原密码错误");
            }
        }else{
            response.getWriter().print("用户不存在");
        }

    }

    @RequestMapping("/login")
    public void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
        long userTel = Long.parseLong(request.getParameter("userTel"));
        User user = userService.getUserByTel(userTel);
        if(user!=null){
            String userPwd = request.getParameter("userPwd");
            if(!userPwd.equals(user.getUserPwd())) {
                user = null;
            }else{
                user.setUserPwd(null);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        String userInfo = mapper.writeValueAsString(user);
        response.getWriter().print(userInfo);
    }

    @RequestMapping("addUseStates")
    public void addUseStates(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String useStatesJson = request.getParameter("useStatesJson");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(useStatesJson); // 读取Json
        JsonNode useStatesNode = rootNode.path("useStates");
        for(int i=0;i<useStatesNode.size();i++){
            String appPackage = useStatesNode.path("appPackage").asText();
            Date startTime = new Date(useStatesNode.path("startTime").asLong());
            Date endTime = new Date(useStatesNode.path("endTime").asLong());
            int appId = appService.getAppIdByPackage(appPackage);
            if(appId==0){
                String appName = useStatesNode.path("appName").asText();
                Map<String,String> typeAndIcon =  CrawlerUtil.getTypeAndIcon(appPackage);
                String appType = typeAndIcon.get("type");
                String appIcon = typeAndIcon.get("icon");
                if(appType.equals("")){
                    appType = "未分类";
                }
                if(appIcon.equals("")){
                    appIcon = "";
                }
                Date appAddTime = new Date();
                appService.addApp(appName,appPackage,appType,appIcon,appAddTime);
            }
            useStateService.addUseState(userId,appId,startTime,endTime);
        }
        response.getWriter().print("success");
    }

    @RequestMapping("getUseStatesByDate")
    public void getUseStatesByDate(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        Date startTime = new Date(Long.parseLong(request.getParameter("startTime")));
        Date endTime = new Date(Long.parseLong(request.getParameter("endTime")));
        List<UseState> useStateList = useStateService.getUseStateByUserIdAndDate(userId,startTime,endTime);
        ObjectMapper mapper = new ObjectMapper();
        String useStates = mapper.writeValueAsString(useStateList);
        List<String> appPackage = new ArrayList<String>();
        for(UseState useState:useStateList){
            appPackage.add(appService.getAppPackageByAppId(useState.getAppId()));
        }
        String appPackages = mapper.writeValueAsString(appPackage);
        response.getWriter().print(useStates+"_"+appPackages);
    }

    @RequestMapping("getAllUseStates")
    public void getAllUseStates(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        List<UseState> useStateList = useStateService.getUseStateByUserId(userId);
        ObjectMapper mapper = new ObjectMapper();
        String useStates = mapper.writeValueAsString(useStateList);
        List<String> appPackage = new ArrayList<String>();
        for(UseState useState:useStateList){
            appPackage.add(appService.getAppPackageByAppId(useState.getAppId()));
        }
        String appPackages = mapper.writeValueAsString(appPackage);
        response.getWriter().print(useStates+"_"+appPackages);
    }



    @RequestMapping("addLocations")
    public void addLocations(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String useStatesJson = request.getParameter("locationsJson");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(useStatesJson); // 读取Json
        JsonNode locationsNode = rootNode.path("locations");
        for(int i=0;i<locationsNode.size();i++){
            double longitude = locationsNode.path("longitude").asDouble();
            double latitude= locationsNode.path("latitude").asDouble();
            Date startTime = new Date(locationsNode.path("startTime").asLong());
            Date endTime = new Date(locationsNode.path("endTime").asLong());
            locationService.addLocation(userId,longitude,latitude,startTime,endTime);
        }
        response.getWriter().print("success");
    }


    @RequestMapping("getLocationsByDate")
    public void getLocationsByDate(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        Date startTime = new Date(Long.parseLong(request.getParameter("startTime")));
        Date endTime = new Date(Long.parseLong(request.getParameter("endTime")));
        List<Location> locationList = locationService.getLocationByUserIdAndDate(userId,startTime,endTime);
        ObjectMapper mapper = new ObjectMapper();
        String locations = mapper.writeValueAsString(locationList);
        response.getWriter().print(locations);
    }

    @RequestMapping("getAllLocations")
    public void getAllLocations(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        List<Location> locationList = locationService.getLocationByUserId(userId);
        ObjectMapper mapper = new ObjectMapper();
        String locations = mapper.writeValueAsString(locationList);
        response.getWriter().print(locations);
    }

    @RequestMapping("addUnlockStates")
    public void addUnlockStates(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String unlockStatesJson = request.getParameter("unlockStatesJson");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(unlockStatesJson); // 读取Json
        JsonNode unlockStatesNode = rootNode.path("unlockStates");
        for(int i=0;i<unlockStatesNode.size();i++){
            Date unlockTime = new Date(unlockStatesNode.path("unlockTime").asLong());
            unlockService.addUnlock(userId,unlockTime);
        }
        response.getWriter().print("success");
    }

    @RequestMapping("getUnlockStatesByDate")
    public void getUnlockStatesByDate(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        Date startTime = new Date(Long.parseLong(request.getParameter("startTime")));
        Date endTime = new Date(Long.parseLong(request.getParameter("endTime")));
        List<Unlock> unlockList = unlockService.getUnlockByUserIdAndDate(userId,startTime,endTime);
        ObjectMapper mapper = new ObjectMapper();
        String unlockStates = mapper.writeValueAsString(unlockList);
        response.getWriter().print(unlockStates);
    }

    @RequestMapping("getAllUnlockStates")
    public void getAllUnlockStates(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        List<Unlock> unlockList = unlockService.getLocationByUserId(userId);
        ObjectMapper mapper = new ObjectMapper();
        String locations = mapper.writeValueAsString(unlockList);
        response.getWriter().print(locations);
    }

    @RequestMapping("getParents")
    public void getParents(HttpServletRequest request,HttpServletResponse response){

    }
}
