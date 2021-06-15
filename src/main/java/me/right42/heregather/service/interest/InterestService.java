package me.right42.heregather.service.interest;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.domain.interest.repository.InterestRepository;

@Service
@RequiredArgsConstructor
public class InterestService {

	private final InterestRepository interestRepository;


}
