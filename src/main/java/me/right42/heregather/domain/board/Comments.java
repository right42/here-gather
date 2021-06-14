package me.right42.heregather.domain.board;

import static javax.persistence.FetchType.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.right42.heregather.domain.common.BaseEntity;
import me.right42.heregather.domain.user.User;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Comments extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "comments_id")
	private Long id;

	private String content;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "board_id")
	private Board board;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User writer;
}
