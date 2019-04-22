package buct.software.utils;

import lombok.Data;

/**
 * @author 高谦
 * 简介: 用于解析 浏览器的User-Agent 对象的简单工具类
 */


@Data
public class UserAgentParser {
    private String url;
    private String platform;
    public UserAgentParser(String url){
        this.url=url;
        if(this.url.indexOf("Android")!=-1 || this.url.indexOf("iPhone")!=-1){
            this.setPlatform("mobile");
        }
        else{
            this.setPlatform("pc");
        }
    }

}
