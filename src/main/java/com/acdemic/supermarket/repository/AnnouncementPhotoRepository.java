package com.acdemic.supermarket.repository;

import com.acdemic.supermarket.entity.AnnouncementPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementPhotoRepository extends JpaRepository<AnnouncementPhoto, Long> {
}
