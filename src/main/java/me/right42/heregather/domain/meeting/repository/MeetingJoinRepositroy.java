package me.right42.heregather.domain.meeting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.right42.heregather.domain.meeting.MeetingJoin;

public interface MeetingJoinRepositroy extends JpaRepository<MeetingJoin, Long> {
}
