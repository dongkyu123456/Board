package org.zerock.project1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.project1.entity.Board;
import org.zerock.project1.repository.search.SearchBoardRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>
    , SearchBoardRepository {
  // 엔티티클래스 내부에 연관관계가 있는 경우(on 생략 가능)
  @Query("select b, w from Board b left join b.writer w where b.bno=:bno")
  Object getBoardwithWrtiter(@Param("bno") Long bno);

  // 연관관계가 없는 경우 엔티티 조인 처리에는 on 사용
  @Query("select b, r from Board b left join Reply r on r.board=b where b.bno=:bno")
  List<Object[]> getBoardWithReply(@Param("bno") Long bno);

  // 목록 화면에 필요한 데이터
  @Query(value = "select b,w,count(r) from Board b left join b.writer w left join Reply r " +
      "on r.board=b group by b "
      , countQuery = "select count(b) from Board b ")
  Page<Object[]> getBoardWithReplyCount(Pageable pageable);

  // 상세페이지
  @Query("select b,w,count(r) from Board b left join b.writer w left outer join Reply r " +
      "on r.board=b where b.bno=:bno ")
  Object getBoardByBno(@Param("bno") Long bno);
}