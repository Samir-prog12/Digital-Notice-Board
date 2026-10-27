package DigitalNoticeBoard.Controller;

import DigitalNoticeBoard.Dto.CreateNoticeRequestDto;
import DigitalNoticeBoard.Dto.NoticeDto;
import DigitalNoticeBoard.Service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notices")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;


    @GetMapping("/allnotices")
    public ResponseEntity<List<NoticeDto>> getAllNotices(){
        return  ResponseEntity.ok(noticeService.getAllNotices());
    }

    @GetMapping("/notice/{id}")
    public ResponseEntity<NoticeDto> getNoticeById(@PathVariable Long id){
        return  ResponseEntity.ok(noticeService.getNoticeById(id));
    }
    @PreAuthorize("hasRole('ADMIN')")
@PostMapping("/create-notices")

    public ResponseEntity<NoticeDto> createNotice(@RequestBody CreateNoticeRequestDto createNoticeRequestDto){
        return  ResponseEntity.ok(noticeService.createNotice(createNoticeRequestDto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-notice-details/{id}")

public ResponseEntity<NoticeDto> updateNotice(@PathVariable Long id,@RequestBody CreateNoticeRequestDto createNoticeRequestDto){
    return  ResponseEntity.ok(noticeService.updateNotice(id,createNoticeRequestDto));
}
    @PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/delete-notice/{id}")

    public ResponseEntity<Void> deleteNotice(@PathVariable Long id ){
        noticeService.deleteNotice(id);
        return ResponseEntity.noContent().build();
    }
}
