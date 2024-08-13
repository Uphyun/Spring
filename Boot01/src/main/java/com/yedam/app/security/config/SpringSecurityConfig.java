package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity // 화살표함수같은 것?
public class SpringSecurityConfig {
	@Bean // 비밀번호 암호화에 사용되는 빈
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // 암호화해서 사용 됌
	}
	
	// 인증 및 인가
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
	    // SecurityFilterChain 빈(bean)을 정의하고, HttpSecurity 객체를 사용하여 시큐리티 구성을 설정
	    http 
	        .authorizeHttpRequests((authorize) 
	            -> authorize
	                // FORWARD 타입의 DispatcherType에 대한 요청은 인증 없이 접근을 허용합니다.
	                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
	                
	                // "/"와 "/all" 경로에 대한 요청은 인증 없이 접근을 허용합니다.
	                .requestMatchers("/", "/all").permitAll()

	                // "/user/**" 경로에 대한 요청은 "USER, ADMIN" 역할을 가진 사용자만 접근
	                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")

	                // "/admin/**" 경로에 대한 요청은 "ROLE_ADMIN" 권한을 가진 사용자만 접근
	                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
	                
	                // 위에서 명시된 경로들을 제외한 나머지 모든 요청은 인증된 사용자만 접근
	                .anyRequest().authenticated() 
	        )
	        // 폼 기반 로그인 설정을 추가합니다.
	        .formLogin(formlogin -> formlogin // 로그인 데이터가 자동으로 뜨는거
	                    // 로그인 성공 시 기본적으로 "/all" 경로로 리다이렉트됩니다.
	                    .defaultSuccessUrl("/all"))
	        // 로그아웃 설정을 추가합니다.
	        .logout(logout -> logout
	                    // 로그아웃 성공 시 "/all" 경로로 리다이렉트됩니다.
	                    .logoutSuccessUrl("/all")
	                    // 로그아웃 시 세션을 무효화하여 사용자 세션 정보를 삭제합니다.
	                    .invalidateHttpSession(true));
	    
	    // 설정된 HttpSecurity 객체를 기반으로 SecurityFilterChain을 빌드하여 반환합니다.
	    http.csrf(csrf -> csrf.disable());
	    return http.build();
	}
	
	/*
	 * @Bean InMemoryUserDetailsManager inMemoryUserDetailsService() { //
	 * InMemoryUserDetailsManager 빈(bean)을 정의하여 메모리 내에서 사용자 세부 정보를 관리합니다.
	 * 
	 * 
	 * UserDetails user = User.builder() // "user1"이라는 사용자 이름을 가진 UserDetails 객체를
	 * 빌드합니다. .username("user1")
	 * 
	 * // 비밀번호를 "1234"로 설정하고, 이를 passwordEncoder()를 사용해 암호화합니다.
	 * .password(passwordEncoder().encode("1234"))
	 * 
	 * // 사용자의 역할을 "USER"로 설정합니다. 이는 'ROLE_USER'라는 권한을 자동으로 부여합니다. .roles("USER") //
	 * ROLE_가 붙어 있다고 판단 즉, = ROLE_USER이랑 같은 의미임
	 * 
	 * //.authorities("ROLE_USER") // 이 부분은 주석 처리되어 있으며, roles 메서드로 대체되고 있습니다.
	 * 
	 * // 빌드 메서드를 사용해 UserDetails 객체를 생성합니다. .build();
	 * 
	 * UserDetails admin = User.builder() // "admin1"이라는 사용자 이름을 가진 UserDetails 객체를
	 * 빌드합니다. .username("admin1")
	 * 
	 * // 비밀번호를 "1234"로 설정하고, 이를 passwordEncoder()를 사용해 암호화합니다.
	 * .password(passwordEncoder().encode("1234"))
	 * 
	 * // 사용자의 권한을 "ROLE_ADMIN"으로 설정합니다. .authorities("ROLE_ADMIN", "ROLE_USER")
	 * 
	 * //.roles("ADMIN") // 이 부분은 주석 처리되어 있으며, authorities 메서드로 대체되고 있습니다.
	 * 
	 * // 빌드 메서드를 사용해 UserDetails 객체를 생성합니다. .build();
	 * 
	 * // 두 개의 사용자 정보(user와 admin)를 포함한 InMemoryUserDetailsManager 객체를 생성하고 반환합니다.
	 * return new InMemoryUserDetailsManager(user, admin); }
	 */
}//end of class
