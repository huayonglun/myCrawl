package com.liuyong;

import java.util.List;
import java.util.Map;
//爬虫调度程序
public class SpiderMain {
	
	UrlManager urls;//URL管理器
	HtmlDownloader downloader;//HTML下载器
	HtmlParser parser;//HTML解析器
	HtmlOutputer outputer;//HTML输出器
	
	
	//调度程序初始化
	public SpiderMain() {
		this.urls = new UrlManager();
		this.downloader = new HtmlDownloader();
		this.parser = new HtmlParser();
		this.outputer = new HtmlOutputer();
	}

	/**
	 * 主函数
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		String root_url = "http://baike.baidu.com/subview/29/12654100.htm";//定义爬取的根URL
		SpiderMain spider = new SpiderMain();//初始化调度程序
		spider.craw(root_url);//执行爬虫
	}

	private void craw(String root_url) throws Exception {
		
		//记录当前爬取的第几个URL
		int count = 1;
		
		
		urls.addNewUrl(root_url);//添加根URL
		while(urls.hasNewUrl()){
			try{
				//有新的URL则得到一个新的URL
				String newUrl = urls.getNewUrl();
				System.out.println("craw "+count+" : "+newUrl);
				
				//获取这个URL对应页面的html
				String htmlCont = downloader.download(newUrl);
				
				//获取该页面下所有符合要求的URL
				List<String> newUrls = parser.parseNewUrls(htmlCont);
				
				//获取指定规则解析出的数据
				Data newData  = parser.parseData(htmlCont);
				
				//将得到的新URL添加到URL管理器中
				urls.addNewUrls(newUrls);
				
				//输出器将数据添加到收集器中
				outputer.collectData(newData);
					
				//定义爬取个数	
				if(count == 100){
					break;
				}
				count++;				
			}catch(Exception e){
				System.out.println("craw filed");
			}

			
		}
		
		//以html形式输出爬取到的数据
		outputer.outputHtml();
	
	}

}
