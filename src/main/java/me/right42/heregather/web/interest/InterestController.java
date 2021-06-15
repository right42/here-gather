package me.right42.heregather.web.interest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.service.interest.InterestQueryService;
import me.right42.heregather.web.dto.interst.InterestGroupingDto;
import me.right42.heregather.web.dto.response.RestResponse;

@RestController
@RequiredArgsConstructor
public class InterestController {

	private final InterestQueryService interestQueryService;

	// @GetMapping("/interests")
	// public RestResponse<List<InterestGroupingDto>> findAll(){
	// 	return new RestResponse<>(interestQueryService.findAllWithGrouping());
	// }

	@GetMapping("/interests")
	public RestResponse<List<InterestGroupingDto>> search(@RequestParam(required = false) String keyword){
		return new RestResponse<>(interestQueryService.findByNameWithGrouping(keyword));
	}

}
