package com.alan.util;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.HashMap;
import java.util.Map;


public class CrawlerUtil implements PageProcessor {
    static String type = "";
    static String icon = "";

    // setSleepTime: 抓取的时间间隔 setRetryTimes: 重试的次数
    private Site site = Site.me().setRetryTimes(5).setSleepTime(10);

    @Override
    public void process(Page page) {
        // TODO Auto-generated method stub


        type = page.getHtml().xpath("//a[@class='det-type-link']/text()").toString();
        icon = page.getHtml().xpath("//div[@class='det-icon']/img/@src").toString();

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static Map<String,String> getTypeAndIcon(String packageName){
        String url = "https://sj.qq.com/myapp/detail.htm?apkName="+packageName;
        Spider.create(new CrawlerUtil()).addUrl(url).thread(5).run();
        Map<String,String> resultMap = new HashMap<String, String>();
        resultMap.put("type",type);
        resultMap.put("icon",icon);
        return resultMap;
    }
}
