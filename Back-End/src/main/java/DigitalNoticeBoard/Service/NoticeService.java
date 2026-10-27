package DigitalNoticeBoard.Service;

import DigitalNoticeBoard.Dto.CreateNoticeRequestDto;
import DigitalNoticeBoard.Dto.NoticeDto;
import DigitalNoticeBoard.Entity.Notice;
import DigitalNoticeBoard.Repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    private NoticeDto toDto(Notice notice) {
        NoticeDto dto = new NoticeDto();
        dto.setId(notice.getId());
        dto.setTitle(notice.getTitle());
        dto.setDescription(notice.getDescription());
        dto.setCategory(notice.getCategory());
        dto.setCreatedAt(notice.getCreatedAt());
        dto.setExpiryDate(notice.getExpiryDate());
        dto.setIsImportant(notice.getIsImportant());
        return dto;
    }

    public List<NoticeDto> getAllNotices() {
        return noticeRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public NoticeDto getNoticeById(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found with id: " + id));
        return toDto(notice);
    }

    public NoticeDto createNotice(CreateNoticeRequestDto request) {
        Notice notice = new Notice();
        notice.setTitle(request.getTitle());
        notice.setDescription(request.getDescription());
        notice.setCategory(request.getCategory());
        notice.setCreatedAt(LocalDateTime.now());
        notice.setExpiryDate(request.getExpiryDate());
        notice.setIsImportant(request.getIsImportant());
        return toDto(noticeRepository.save(notice));
    }

    public NoticeDto updateNotice(Long id, CreateNoticeRequestDto request) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found with id: " + id));

        notice.setTitle(request.getTitle());
        notice.setDescription(request.getDescription());
        notice.setCategory(request.getCategory());
        notice.setExpiryDate(request.getExpiryDate());
        notice.setIsImportant(request.getIsImportant());

        return toDto(noticeRepository.save(notice));
    }

    public void deleteNotice(Long id) {
        if (!noticeRepository.existsById(id)) {
            throw new RuntimeException("Notice not found with id: " + id);
        }
        noticeRepository.deleteById(id);
    }
}