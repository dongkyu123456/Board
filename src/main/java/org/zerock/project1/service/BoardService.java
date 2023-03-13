package org.zerock.project1.service;

import org.zerock.project1.dto.BoardDTO;
import org.zerock.project1.dto.PageRequestDTO;
import org.zerock.project1.dto.PageResultDTO;
import org.zerock.project1.entity.Board;
import org.zerock.project1.entity.Member;

public interface BoardService {
  Long register(BoardDTO dto);

  PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

  BoardDTO get(Long bno);

  void removeWithReplies(Long bno);

  void modify(BoardDTO dto);

  default Board dtoToEntity(BoardDTO dto) {
    Member member = Member.builder().email(
        dto.getWriterEmail()).build();
    Board board = Board.builder()
        .bno(dto.getBno())
        .title(dto.getTitle())
        .content(dto.getContent())
        .writer(member)
        .build();
    return board;
  }
  default BoardDTO entityToDTO(Board board, Member member, Long replyCount) {
    BoardDTO dto = BoardDTO.builder()
        .bno(board.getBno())
        .title(board.getTitle())
        .content(board.getContent())
        .regDate(board.getRegDate())
        .modDate(board.getModDate())
        .writerEmail(member.getEmail())
        .writerName(member.getName())
        .replyCount(replyCount.intValue())
        .build();
    return dto;
  }
}