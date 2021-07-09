package com.example.demo.form;

import javax.validation.constraints.NotBlank;

import org.springframework.web.context.annotation.SessionScope;

// @Component
// @Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
@SessionScope
public class Articleform {
	@NotBlank(message = "名前を入力してください")
	String author;

	@NotBlank(message = "タイトルを入力してください")
	String title;

	@NotBlank(message = "内容を入力してください")
	String content;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Articleform [author=" + author + ", title=" + title + ", content=" + content + "]";
	}


}
