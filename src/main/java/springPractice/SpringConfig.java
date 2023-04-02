package springPractice;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import springPractice.repository.JdbcMemberRepository;
import springPractice.repository.MemberRepository;
import springPractice.service.MemberService;

//자바코드로 직접 스프링빈 등록시 장점
//(과거엔 XML로 했었는데 지금은 안함.)
//DI에는 생성자 주입, 필드 주입<-바꿔치기가 힘듬. 세터 주입 <-퍼블릭하게 노출이 되어 있기에 문제, 
//생성자 주입이 권장됨<-의존관계가 실행중에 동적으로 변하는 경우는 거의 없기 떄문에.
//정형화된 코드는 컴포넌트 스캔을 사용. 
//정형화 되지 않거나, 상황에 따라 구현이 바뀌는 경우, 설정을 통해 스프링 빈으로 등록한다.


@Configuration
@EnableAspectJAutoProxy
public class SpringConfig {

	private DataSource dataSource;
	
	@Autowired
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	//설정 컴포넌트가 켜지면 스프링빈에 아래 빈들을 등록
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
		return new JdbcMemberRepository(dataSource);
	}
	
	
}
