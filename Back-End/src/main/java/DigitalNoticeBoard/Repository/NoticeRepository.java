package DigitalNoticeBoard.Repository;

import DigitalNoticeBoard.Entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice,Long> {
}
