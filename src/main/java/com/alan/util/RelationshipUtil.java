package com.alan.util;

public class RelationshipUtil {
    public static String reverseRelationship(String relationship, int gender){
        if(relationship.equals("祖父")||relationship.equals("祖母")){
            if (gender==0){
                return "孙女";
            }else{
                return "孙子";
            }
        }
        if(relationship.equals("外祖父")||relationship.equals("外祖母")){
            if (gender==0){
                return "外孙女";
            }else{
                return "外孙";
            }
        }
        if(relationship.equals("父亲")||relationship.equals("母亲")){
            if (gender==0){
                return "女儿";
            }else{
                return "儿子";
            }
        }
        if(relationship.equals("伯父")||relationship.equals("伯母")
                ||relationship.equals("叔叔")||relationship.equals("婶婶")
                ||relationship.equals("姑姑")||relationship.equals("姑父")){
            if (gender==0){
                return "侄女";
            }else{
                return "侄子";
            }
        }
        if(relationship.equals("舅舅")||relationship.equals("舅妈")
                ||relationship.equals("姨夫")||relationship.equals("姨妈")){
            if (gender==0){
                return "外甥女";
            }else{
                return "外甥";
            }
        }
        if(relationship.equals("哥哥")||relationship.equals("姐姐")){
            if (gender==0){
                return "妹妹";
            }else{
                return "弟弟";
            }
        }

        return "其他";
    }
}
