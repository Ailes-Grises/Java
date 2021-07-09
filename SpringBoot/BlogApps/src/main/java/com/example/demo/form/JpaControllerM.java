package com.example.demo.form;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.db.Article;
import com.example.demo.db.JpaArticleDao;

@Controller
public class JpaControllerM {
	//DAOのオブジェクトが座る椅子を用意
	private final JpaArticleDao jpadao;
	private Article tmp;

	//DAOを予め控室に待機させておき、必要なときに呼んで座らせる
	@Autowired
	public JpaControllerM(JpaArticleDao jpadao) {
		this.jpadao = jpadao;
		this.tmp = new Article();
	}

	// submit.html（ブログの執筆画面）
	@RequestMapping("/submit")
	public String submit(Articleform articleform) {
		return "submit.html";
	}

	// 登録内容が正しければpreviewに飛ぶ
	@RequestMapping("/submit_complete")
	public String submit_complete(@Validated Articleform articleform, BindingResult result, Model model) {
		//		System.out.println(contentform.getContent());
		System.out.println(result.hasErrors());
		if (result.hasErrors()) {
			System.out.println("error");
			return "/submit";
		}

		tmp.setAuthor(articleform.getAuthor());
		tmp.setTitle(articleform.getTitle());
		tmp.setContent(articleform.getContent());

		// previewで表示するために一時的にpreview_content.htmlとして保存しておく
		
		String saved_content = tmp.getContent();
		saved_content = saved_content.replace("script","span");
		saved_content = "<div th:fragment='content'>" + saved_content + "</div>";
		// 保存先の存在判定
		File folder = new File("C:/var/pages");
		if (!folder.exists()) {
			Path p = Paths.get("C:/var/pages");
			try {
				Files.createDirectories(p);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		String file_name = "/var/pages/preview_content.html";
//		String file_name = "src\\main\\resources\\templates\\preview_content.html";
		try {
			File file = new File(file_name);
			FileWriter filewriter;
			filewriter = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(filewriter);
			bw.write(saved_content);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:preview";
	}

	@RequestMapping("/preview")
	public String preview(Articleform articleform, Model model) {
		//		Content content = jpadao.findById(id).get();
		Article article = new Article(
				tmp.getAuthor(),
				tmp.getTitle(),
				tmp.getContent(),
				new Date());
		File file = new File("src\\main\\resources\\templates\\preview_content.html");
		FileReader fr;
		try {
			fr = new FileReader(file);
			fr.read();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		model.addAttribute("article", article);
		model.addAttribute("pageName", "preview");

		return "preview.html";
	}

	// データベースへのContent登録処理
	@RequestMapping("/preview_complete")
	public String preview_complete(Articleform articleform) {
		Article article = new Article(
				tmp.getAuthor(),
				tmp.getTitle(),
				"", // contentは別で指定する
				new Date());
		System.out.println(article);
		Article saved = jpadao.save(article);
		String file_name = "/var/pages/" + saved.getId() + ".html";
		String update_file_name = "/" + saved.getId() + ".html";
		jpadao.updateContent(saved.getId(), update_file_name);

		try {
			File file = new File(file_name);
			FileWriter filewriter;
			filewriter = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(filewriter);
			bw.write(tmp.getContent());
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/";
	}
	//

	//	//【JPAでINSERT】
	//	//完了画面・登録(INSERT)
	//	@RequestMapping("/complete")
	//	public String complete(Form form, Model model) {
	//		//フォームの値をエンティティに入れ直す
	//		Staff s1 = new Staff();
	//		s1.setName(form.getName());
	//		//JPAでINSERT実行
	//		jpadao.save(s1);
	//		return "jpa_complete";
	//	}
	//
	//	//入力画面(localhost:8080/form)
	//	@RequestMapping("/form")
	//	public String form(Model model, Form form) {
	//		System.out.println(form.getName());
	//		return "jpa_input";
	//	}
	//
	//	//確認画面(localhost:8080/confirm)
	//	@RequestMapping("/confirm")
	//	public String confirm(@Validated Form form, BindingResult result, Model model) {
	//		System.out.println("test");
	//		//エラーがあれば入力画面に戻す
	//		if (result.hasErrors()) {
	//			return "jpa_input";
	//		}
	//		return "jpa_confirm";
	//	}

}
