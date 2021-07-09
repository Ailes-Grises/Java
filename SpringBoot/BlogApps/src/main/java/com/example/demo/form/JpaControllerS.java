package com.example.demo.form;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.db.Article;
import com.example.demo.db.JpaArticleDao;

@Controller
public class JpaControllerS {
	//DAOのオブジェクトが座る椅子を用意
	private final JpaArticleDao jpadao;

	//DAOを予め控室に待機させておき、必要なときに呼んで座らせる
	@Autowired
	public JpaControllerS(JpaArticleDao jpadao) {
		this.jpadao = jpadao;
	}

	// "/ --> /index.html"
	@RequestMapping("/")
	public String index(Model model) {
		//JPAを用いたデータの取得
		List<Article> article_list = jpadao.findAll();
		model.addAttribute("article_list", article_list);
		//model.addAttribute("article_list", jpadao.findAll());
		System.out.println(article_list);
		return "index";
	}

	// "/article/{id} --> /article.html"
	@RequestMapping("/article/{id}")
	public String article_id(@PathVariable Long id, Model model) {
		//JPAを用いたデータの取得
		jpadao.increaseAccessCount(id);
		Article article = jpadao.findById(id).get();
		model.addAttribute("article", article);
		model.addAttribute("pageName","article");	
		return "article";
	}
	
	@GetMapping("/favorite")
	public String favorite(@RequestParam(name = "id") Long id) {
		//JPAを用いたデータの取得
		jpadao.increaseFavoriteCount(id);
		return "redirect:/article/"+id;
	}
	
}
