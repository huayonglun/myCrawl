package com.liuyong;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//输出器
public class HtmlOutputer {
	
	//维护一个存储数据的容器
	List<Data> datas = new ArrayList<Data>();

	/**
	 * 收集数据
	 * @param data 收集到的数据
	 */
	public void collectData(Data data) {
		if(data==null)
			return;
		datas.add(data);
		
	}

	
	/**
	 * 将数据以html形式输出
	 */
	public void outputHtml(){
		File file = new File("output.html");
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(file);
			//输出数据
			os.write("<html><link href='output.css' rel='stylesheet'><body><h1 align='center'>采用liuyong的程序爬取到的百科关键字信息</h1>".getBytes());
			os.write("<table border='1' width='70%' align='center'>".getBytes());
			for(Data data : datas){
				os.write("<tr>".getBytes());
				os.write("<td width='15%'>".getBytes());
				os.write(data.getTitle().getBytes());
				os.write("</td>".getBytes());
				
				os.write("<td width='85%'>".getBytes());
				os.write(data.getSummary().getBytes());
				os.write("</td>".getBytes());
				os.write("</tr>".getBytes());
			}
			os.write("</table>".getBytes());
			os.write("</body></html>".getBytes());
			
			
		}catch (Exception e) {
			System.out.println("输出器发生错误");
			e.printStackTrace();
		}finally{
			if(os!=null){
				try {
					os.flush();
				} catch (IOException e) { 
					e.printStackTrace();
				}
				try {
					os.close();
				} catch (IOException e) { 
					e.printStackTrace();
				}
			}
		}
		
	}

}
