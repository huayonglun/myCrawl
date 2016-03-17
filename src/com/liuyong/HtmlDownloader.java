package com.liuyong;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//HTML下载器
public class HtmlDownloader {

	/**
	 * 获取指定URL页面的HTML内容
	 * @param downloadUrl
	 * @return html页面字符串
	 */
	public String download(String downloadUrl) {	
		Document doc = null;
		try {
			doc = Jsoup.connect(downloadUrl).get();
		} catch (IOException e) {
			System.out.println("下载器出错啦！！！");
		}
		
		return  doc.html();
		
	}

}
