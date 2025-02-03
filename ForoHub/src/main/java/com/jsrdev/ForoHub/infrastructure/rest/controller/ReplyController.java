package com.jsrdev.ForoHub.infrastructure.rest.controller;

import com.jsrdev.ForoHub.domain.model.Reply;
import com.jsrdev.ForoHub.infrastructure.rest.dto.reply.ReplyRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.reply.ReplyResponse;
import com.jsrdev.ForoHub.infrastructure.rest.mapper.ReplyMapper;
import com.jsrdev.ForoHub.usecase.reply.ReplyInteractor;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/replies")
public class ReplyController {

    private final ReplyInteractor replyInteractor;

    public ReplyController(ReplyInteractor replyInteractor) {
        this.replyInteractor = replyInteractor;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ReplyResponse> create(
            @Valid @RequestBody ReplyRequest replyRequest,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Reply reply = replyInteractor.save(replyRequest);

        URI uri = uriComponentsBuilder.path("/api/replies/{id}")
                .buildAndExpand(reply.getReplyId()).toUri();

        return ResponseEntity.created(uri).body(ReplyMapper.toResponse(reply));
    }
}
