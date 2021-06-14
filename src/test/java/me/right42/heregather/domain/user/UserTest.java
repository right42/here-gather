package me.right42.heregather.domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@DataJpaTest
class UserTest {

	@Test
	void 생성테스트(){
		User user = User.builder().build();

		assertThat(user).isNotNull();
	}

}