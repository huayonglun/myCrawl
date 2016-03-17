package com.liuyong;

import java.util.ArrayList;
import java.util.List;
//URL管理器
public class UrlManager {
	
	List<String> newUrls = new ArrayList();//管理待访问的URL
	List<String> oldUrls = new ArrayList();//管理已访问的URL
	
	/**
	 * 添加新的URL到待访问URL列表中
	 * @param url
	 */
	public void addNewUrl(String url) {
		if(url==null)
			return;
		if((!newUrls.contains(url))&&(!oldUrls.contains(url))){//添加原则是，该url既不在待访问列表中，也不在已访问列表中
			newUrls.add(url);
		}
		
	}

	/**
	 * 添加新的URL集合到待访问URL列表中
	 * @param urls
	 */
	public void addNewUrls(List<String> urls) {
		
		if(urls==null||urls.isEmpty()){
			return;
		}
		for(String url:urls){
			addNewUrl(url);
		}
		
	}
	
	/**
	 * 是否有待访问的URL存在
	 * @return 若有待访问的URL，返回true
	 */
	public boolean hasNewUrl() {
		return !newUrls.isEmpty();
	}

	
	/**
	 * 从待访问URL列表中取出一个URL
	 * @return 取出的URL名称
	 */
	public String getNewUrl() {
		if(newUrls.size()==0){
			return null;
		}
		
		//待访问列表取出后加入到已访问列表中
		String newUrl = newUrls.get(newUrls.size()-1);
		newUrls.remove(newUrls.size()-1);
		oldUrls.add(newUrl);
		return newUrl;
	}



}
