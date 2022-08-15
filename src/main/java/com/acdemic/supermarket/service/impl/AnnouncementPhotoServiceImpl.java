package com.acdemic.supermarket.service.impl;

import com.acdemic.supermarket.dto.request.AnnouncementPhotoRequest;
import com.acdemic.supermarket.dto.response.AnnouncementPhotoResponse;
import com.acdemic.supermarket.dto.response.Response;
import com.acdemic.supermarket.entity.Announcement;
import com.acdemic.supermarket.entity.AnnouncementPhoto;
import com.acdemic.supermarket.mapper.AnnouncementPhotoMapper;
import com.acdemic.supermarket.repository.AnnouncementPhotoRepository;
import com.acdemic.supermarket.repository.AnnouncementRepository;
import com.acdemic.supermarket.service.AnnouncementPhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AnnouncementPhotoServiceImpl implements AnnouncementPhotoService {

    private final AnnouncementPhotoMapper announcementPhotoMapper;
    private final AnnouncementPhotoRepository announcementPhotoRepository;
    private final AnnouncementRepository announcementRepository;

    @Override
    public ResponseEntity<?> save(AnnouncementPhotoRequest request) {
        if (request == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Wrong request", false));
        }
        AnnouncementPhotoResponse announcementPhotoResponse =
                announcementPhotoMapper
                        .toResponse(announcementPhotoRepository
                                .save(announcementPhotoMapper.toEntity(request)));
        return ResponseEntity.status(201).body(new Response(
                "AnnouncementPhoto successfully saved !!!",
                true,
                       announcementPhotoResponse));
    }

    @Override
    public ResponseEntity<?> edit(AnnouncementPhotoRequest request, Long id) {
        AnnouncementPhoto announcementPhoto = announcementPhotoRepository
                .findById(id).orElseThrow(()
                        -> new IllegalArgumentException("Id not found"));
        Announcement announcement = announcementRepository
                .findById(request.getAnnouncementId()).orElseThrow(()
                -> new IllegalArgumentException("Announcement id not found"));
        if (!announcementPhoto.getAnnouncement().equals(announcement))
            announcementPhoto.setAnnouncement(announcement);
        if (!announcementPhoto.getExtension().equals(request.getExtension()))
            announcementPhoto.setExtension(request.getExtension());
        if (!announcementPhoto.getKey().equals(request.getKey()))
            announcementPhoto.setKey(request.getKey());
        if (!announcementPhoto.getPath().equals(request.getPath()))
            announcementPhoto.setPath(request.getPath());
        if (!announcementPhoto.getSize().equals(request.getSize()))
            announcementPhoto.setSize(request.getSize());
        if (!announcementPhoto.getOriginName().equals(request.getOriginName()))
            announcementPhoto.setOriginName(request.getOriginName());
        AnnouncementPhotoResponse announcementPhotoResponse= announcementPhotoMapper
                .toResponse(announcementPhotoRepository
                        .save(announcementPhoto));
        return ResponseEntity.status(HttpStatus.OK).body(
                new Response(
                        "Successfully edited !!!",
                        true,
                        announcementPhotoResponse));
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        if (id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Id null", false));
        }
        AnnouncementPhotoResponse announcementPhotoResponse = announcementPhotoMapper
                .toResponse(announcementPhotoRepository
                .findById(id).orElseThrow(()
                        -> new IllegalArgumentException("Id not found")));
        return ResponseEntity.status(HttpStatus.OK).body(new Response(
                "Find Announcement photo",
                true,
                announcementPhotoResponse));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Id null", false));
        }
        announcementPhotoRepository.delete(
                announcementPhotoRepository.findById(id).orElseThrow(()
                        -> new IllegalArgumentException("Id not found")));
        return ResponseEntity.status(204).body(
                new Response(
                "Announcement photo successfully deleted !!!",
                 true));
    }
}
