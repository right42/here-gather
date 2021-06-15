package me.right42.heregather.service.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.groovy.util.Maps;
import org.springframework.stereotype.Service;

import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableMap;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.domain.role.Role;
import me.right42.heregather.domain.role.repository.RoleRepository;
import me.right42.heregather.domain.role.type.RoleType;

@Service
@RequiredArgsConstructor
public class RoleService {

	private final RoleRepository repository;

	private static Map<RoleType, Role> roleMap;

	@PostConstruct
	public void initMap(){
		List<Role> all = repository.findAll();
		Map<RoleType, Role> map = new HashMap<>();
		for (Role role : all) {
			RoleType roleType = RoleType.findRoleTypeByLevel(role.getLevel());
			map.put(roleType, role);
		}

		roleMap = ImmutableMap.copyOf(map);
	}

	public Role getRole(RoleType roleType){
		return roleMap.get(roleType);
	}
}
