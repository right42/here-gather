package me.right42.heregather.service.user;

import static java.util.stream.Collectors.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.domain.interest.Interest;
import me.right42.heregather.domain.interest.repository.InterestRepository;
import me.right42.heregather.domain.interest.user.UserInterest;
import me.right42.heregather.domain.user.User;
import me.right42.heregather.domain.user.repository.UserInterestRepository;
import me.right42.heregather.exception.ApplicationException;
import me.right42.heregather.web.dto.interst.InterestRequestDto;

@Service
@RequiredArgsConstructor
public class UserInterestService {

	private final UserInterestRepository userInterestRepository;

	private final InterestRepository interestRepository;

	@Transactional
	public void changeUserInterest(User user, List<InterestRequestDto> interests) {
		List<UserInterest> oldUserInterest = userInterestRepository.findByUser(user);

		userInterestRepository.deleteAll(oldUserInterest);

		List<UserInterest> userInterests = interests.stream()
			.map(interestDto -> {
				Interest interest = interestRepository.findById(interestDto.getInterestId())
					.orElseThrow(ApplicationException::new);

				return UserInterest.builder()
					.interest(interest)
					.user(user)
					.build();

			})
			.collect(toUnmodifiableList());

		userInterestRepository.saveAll(userInterests);
	}
}
