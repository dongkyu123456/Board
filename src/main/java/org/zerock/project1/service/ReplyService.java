package org.zerock.project1.service;

import org.zerock.project1.dto.ReplyDTO;
import org.zerock.project1.entity.Board;
import org.zerock.project1.entity.Reply;

import java.util.List;

public interface ReplyService {
  Long register(ReplyDTO dto);

  List<ReplyDTO> getList(Long bno);

  void modify(ReplyDTO dto);

  void remove(Long rno);

  default Reply dtoToEntity(ReplyDTO dto) {
    Board board = Board.builder().bno(dto.getBno()).build();
    Reply reply = Reply.builder()
        .rno(dto.getRno())
        .text(dto.getText())
        .replyer(dto.getReplyer())
        .board(board)
        .build();
    return reply;
  }

  default ReplyDTO entityToDTO(Reply reply) {
    ReplyDTO dto = ReplyDTO.builder()
        .rno(reply.getRno())
        .text(reply.getText())
        .replyer(reply.getReplyer())
        .regDate(reply.getRegDate())
        .modDate(reply.getModDate())
        .build();
    return dto;
  }
}