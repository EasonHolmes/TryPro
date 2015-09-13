/**   
 * Copyright © 2014 All rights reserved.
 * 
 * @Title: Item.java 
 * @Prject: PinYinSearch
 * @Package: com.example.pinyinsearch 
 * @Description: TODO
 * @author: raot  719055805@qq.com
 * @date: 2014年9月4日 下午2:20:18 
 * @version: V1.0   
 */
package com.cui.trypro.bean;

/**
 * @ClassName: Item
 * @Description: TODO
 * @author: raot 719055805@qq.com
 * @date: 2014年9月4日 下午2:20:18
 */
public class Item {

	/**
	 * 是否是标题
	 */
	private boolean isTitle = false;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 内容拼音
	 */
	private String pinyin;

	/**
	 * @return the isTitle
	 */
	public boolean isTitle() {
		return isTitle;
	}

	/**
	 * @param isTitle
	 *            the isTitle to set
	 */
	public void setTitle(boolean isTitle) {
		this.isTitle = isTitle;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the pinyin
	 */
	public String getPinyin() {
		return pinyin;
	}

	/**
	 * @param pinyin
	 *            the pinyin to set
	 */
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

}
