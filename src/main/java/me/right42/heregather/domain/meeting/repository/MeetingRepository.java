package me.right42.heregather.domain.meeting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.right42.heregather.domain.meeting.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
