package com.example.demo.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// import login.app.service.UserDetailsServiceImpl;

/**
 * Spring Security を利用するにあたって必要な設定を行うクラス。Spring Security では専用の設定ファイルの代わりに、Java のクラスで設定を行う。
 * @author naoto.suzuki
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityFilterConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private AuthenticationProcess userDetailsService;

    //フォームの値と比較するDBから取得したパスワードは暗号化されているのでフォームの値も暗号化するために利用
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    /**
     * 疎通させるリクエストの設定
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
    	// 疎通させるファイルフォーマットを指定
        web.ignoring().antMatchers(
                            "/images/**",
                            "/css/**",
                            "/javascript/**"
                            );
    }

    /**
     * 主にURL のマッピング設定を行っている。
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login") //ログインページはコントローラを経由しないのでViewNameとの紐付けが必要
                .loginProcessingUrl("/sign_in") //フォームのSubmitURL、このURLへリクエストが送られると認証処理が実行される
                .usernameParameter("username") //リクエストパラメータのname属性を明示
                .passwordParameter("password")
                .successForwardUrl("/") // ログインに成功した場合のURL
                .failureUrl("/login?error")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll();
        http.headers()
        	.frameOptions()
        	.sameOrigin();
        
    }

    /**
     * DBから取得したユーザ情報をuserDetailsService へセットすることで認証時の比較情報としているらしい
     */
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
    	// インメモリデータベースの場合
        // auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER");
    	
    	// 外部データベースの場合
    	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
