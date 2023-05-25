package cn.hunnu.recommender;

import cn.hunnu.recommender.examination.entity.Exams;
import cn.hunnu.recommender.examination.mapper.ExamsMapper;
import cn.hunnu.recommender.user.entity.Person;
import cn.hunnu.recommender.user.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RecommenderApplicationTests {

	@Test
	public void contextLoads() {

	}
}
