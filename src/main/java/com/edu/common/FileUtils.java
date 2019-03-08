package com.edu.common;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xxd 根据浏览器设置编码
 */
public class FileUtils {

	public static String filenameEncoding(String filename, HttpServletRequest request) throws IOException {
		String agent = request.getHeader("User-Agent"); // 获取浏览器
		if (agent.contains("Firefox")) {
			// BASE64Encoder base64Encoder = new BASE64Encoder();
			// filename = "=?utf-8?B?"
			// + base64Encoder.encode(filename.getBytes("utf-8"))
			// + "?=";
		} else if (agent.contains("MSIE")) {
			filename = URLEncoder.encode(filename, "utf-8");
		} else if (agent.contains("Safari")) {
			filename = new String(filename.getBytes("utf-8"), "ISO8859-1");
		} else {
			filename = URLEncoder.encode(filename, "utf-8");
		}
		return filename;
	}
}
