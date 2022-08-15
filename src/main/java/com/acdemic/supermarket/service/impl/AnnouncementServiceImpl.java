package com.acdemic.supermarket.service.impl;

import com.acdemic.supermarket.dto.request.AnnouncementRequest;
import com.acdemic.supermarket.dto.response.AnnouncementResponse;
import com.acdemic.supermarket.dto.response.Response;
import com.acdemic.supermarket.entity.Announcement;
import com.acdemic.supermarket.mapper.AnnouncementMapper;
import com.acdemic.supermarket.repository.AnnouncementRepository;
import com.acdemic.supermarket.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;

    @Override
    public ResponseEntity<?> save(AnnouncementRequest request) {
        Response response = Response.builder().build();
        if (request.getCaption() != null && request.getTitle() != null) {
            AnnouncementResponse announcementResponse =announcementMapper.toResponse(announcementRepository
                    .save(announcementMapper.toEntity(request)));
            response.setSuccess(true);
            response.setData(announcementResponse);
            response.setStatusCode(201);
            response.setMessage("Announcement successfully saved !!!");
        }
        else {
            response.setMessage("There is an error in the announcement");
            response.setSuccess(false);
            response.setStatusCode(404);
        }
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    public ResponseEntity<?> get(Long id){
        if (id == null)
            return ResponseEntity.status(404).body(new Response("Id is null", false));
        Announcement announcement = announcementRepository
                .findById(id)
                .orElseThrow(()
                        -> new IllegalArgumentException("Id not found"));
        AnnouncementResponse announcementResponse = announcementMapper.toResponse(announcement);
        return ResponseEntity.status(200).body(new Response(
                "Find Announcement",
                true,
                announcementResponse));
    }

    public ResponseEntity<?> edit(AnnouncementRequest request, Long id){
        if (id == null)
            return ResponseEntity.status(404).body(new Response("Id is null", false));
        Announcement announcement = announcementRepository
                .findById(id)
                .orElseThrow(()
                        -> new IllegalArgumentException("Id not found"));
        if (!announcement.getCaption().equals(request.getCaption()))
            announcement.setCaption(request.getCaption());

        if (!announcement.getTitle().equals(request.getTitle()))
            announcement.setTitle(request.getTitle());
        AnnouncementResponse announcementResponse =
                announcementMapper.toResponse(announcementRepository.save(announcement));
        return ResponseEntity.status(200).body(new Response(
                "Edit Announcement successfully",
                true,
                announcementResponse));
    }

    public ResponseEntity<?> delete(Long id){
        if (id == null)
            return ResponseEntity.status(404).body(new Response("Id is null", false));
        announcementRepository
                .delete(announcementRepository.findById(id).orElseThrow(()
                        -> new IllegalArgumentException("Id not found")));
        return ResponseEntity.status(204).body(new Response("Delete successfully completed !!!", true));
    }
}
