package org.zerock.project1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.project1.dto.ReplyDTO;
import org.zerock.project1.service.ReplyService;

import java.util.List;

@RestController
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService service;

    @GetMapping(value = "/board/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReplyDTO>> getListByBoard(@PathVariable("bno") Long bno) {
        log.info("bno: "+bno);
        return new ResponseEntity<>(service.getList(bno), HttpStatus.OK);
    }

    @PostMapping({"","/"})
    public ResponseEntity<Long> register(@RequestBody ReplyDTO dto) {
        log.info("reply dto: " + dto);
        Long rno = service.register(dto);
        return new ResponseEntity<>(rno, HttpStatus.OK);
    }

    @PutMapping("/{rno}")
    public ResponseEntity<Long> modify(@RequestBody ReplyDTO dto) {
        log.info("reply dto: " + dto);
        service.modify(dto);
        return new ResponseEntity<>(dto.getRno(), HttpStatus.OK);
    }

    @DeleteMapping("/{rno}")
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
        log.info("reply rno: " + rno);
        service.remove(rno);
        return new ResponseEntity<>(rno+"", HttpStatus.OK);
    }
}