package com.group12.bookinghomestay.admin.repository;

import com.group12.bookinghomestay.admin.model.Comment;
import com.group12.bookinghomestay.admin.model.Report;
import com.group12.bookinghomestay.admin.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query(value = "select * from comment r where r.hotel_id = ?1", nativeQuery = true)
    List<Comment> listCommentByHotelId(int hotelId);
}