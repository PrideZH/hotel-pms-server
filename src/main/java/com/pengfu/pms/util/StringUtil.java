package com.pengfu.pms.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
	
	private StringUtil() {}

	/** 判断是否为空 */
	public static boolean isEmpty(String str) {
		if(str == null || "".equals(str.trim())) {
			return true;
		}else {
			return false;
		}
	}
	
	/** 判断是否不为空 */
	public static boolean isNotEmpty(String str) {
		if(str == null || "".equals(str.trim())) {
			return false;
		}else {
			return true;
		}
	}

	/** 是否存在空字符串 */
	public static boolean isExistEmpty(String ...paras) {
		for (String str : paras) {
			if (isEmpty(str)) {
				return true;
			}
		}
		return false;
	}

	public static List<Long> toLongArray(String str, String limit) {
		List<Long> res = new ArrayList<>();
		String[] strList = str.split(limit);
		for (String s : strList) {
			res.add(Long.parseLong(s));
		}
		return res;
	}
	
}
