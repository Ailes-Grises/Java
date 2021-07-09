package com.example.demo.db;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "article")
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String author;
	private String title;
	private String content;
	private Integer favorite_count;
	private Integer access_count;
	private Date create_at;
	
	public Integer getFavorite_count() {
		return favorite_count;
	}
	public void setFavorite_count(Integer favorite_count) {
		this.favorite_count = favorite_count;
	}
	public Integer getAccess_count() {
		return access_count;
	}
	public void setAccess_count(Integer access_count) {
		this.access_count = access_count;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	
	public Article() {
		super();
	}
	public Article(Long id, String author, String title, String content, Date create_at) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.content = content;
		this.create_at = create_at;
		this.favorite_count = 0;
		this.access_count = 0;
	}

	public Article(String author, String title, String content, Date create_at) {
		super();
		this.author = author;
		this.title = title;
		this.content = content;
		this.create_at = create_at;
		this.favorite_count = 0;
		this.access_count = 0;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}
	@Override
	public String toString() {
		return "Content [id=" + id + ", author=" + author + ", title=" + title + ", content=" + content + ", create_at=" + create_at + "]";
	}


}
