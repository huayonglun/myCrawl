package com.liuyong;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {

	/**
	 * 解析html文档
	 * @param htmlCont html文档内容
	 * @return List<String> 从文档中查找到的url列表
	 */
	public List<String> parseNewUrls(String htmlCont){
		
		if(htmlCont==null)
			return null;
		
		List<String> newUrls = new ArrayList<String>();
		
		Document doc = Jsoup.parse(htmlCont);
		Elements links = doc.select("a[href]"); //带有href属性的a元素
		//Elements pngs = doc.select("img[src$=.png]"); //扩展名为.png的图片
		
		// 正则表达式规则
		String regEx = "/view/\\d+.htm";
		// 编译正则表达式
	    Pattern pattern = Pattern.compile(regEx);
	    
	    for (Element link : links) {
			  String linkHref = link.attr("href");
			  Matcher matcher = pattern.matcher(linkHref);
			  // 查找字符串中是否有匹配正则表达式的字符/字符串
			  boolean rs = matcher.find();
			  //如果匹配，拼接成URL加入URL的待爬取列表
			  if(rs){
				  if(!linkHref.startsWith("http://baike.baidu.com")){
					  linkHref = "http://baike.baidu.com"+linkHref;
				  }
				  
				  newUrls.add(linkHref);
			  }
		}
	    return newUrls;
	}

	/**
	 * 解析html文档中的数据
	 * @param htmlCont html文档内容
	 * @return Data 查找到的所需的数据
	 */
	public Data parseData(String htmlCont){
		
		if(htmlCont==null)
			return null;
		
		Document doc = Jsoup.parse(htmlCont);
		
		//获取标题
		//<dd class="lemmaWgt-lemmaTitle-title"><h1>Python</h1>
		Element title_node = doc.select("dd[class=lemmaWgt-lemmaTitle-title]").select("h1").first();
		String title = title_node.text();
		
		//获取概要
		//<div class="lemma-summary" label-module="lemmaSummary">
		Element summary_node = doc.select("div[class=lemma-summary]").first();
		String summary = summary_node.text();
		
		Data resData = new Data(title, summary);
		
		return resData;
	}

	

}
