package me.right42.heregather.web.dto.region;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.service.region.RegionQueryService;
import me.right42.heregather.web.dto.response.RestResponse;

@RestController
@RequiredArgsConstructor
public class RegionController {

	private final RegionQueryService regionQueryService;

	@GetMapping("/regions")
	public RestResponse<List<RegionResponse>> regions(@RequestBody @Valid RegionSearchDto regionSearchDto){
		return new RestResponse<>(regionQueryService.searchRegion(regionSearchDto));
	}
}
