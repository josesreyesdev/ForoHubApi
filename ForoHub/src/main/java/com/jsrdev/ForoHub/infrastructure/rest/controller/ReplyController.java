package com.jsrdev.ForoHub.infrastructure.rest.controller;

import com.jsrdev.ForoHub.domain.model.Reply;
import com.jsrdev.ForoHub.infrastructure.rest.dto.reply.ReplyRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.reply.ReplyResponse;
import com.jsrdev.ForoHub.infrastructure.rest.mapper.ReplyMapper;
import com.jsrdev.ForoHub.usecase.reply.ReplyInteractor;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @GetMapping
    ResponseEntity<PagedModel<EntityModel<ReplyResponse>>> getReplies(
            @PageableDefault(size = 15) Pageable pagination,
            PagedResourcesAssembler<ReplyResponse> assembler
    ) {
        Page<Reply> repliesPage = replyInteractor.findAllByActiveTrue(pagination);

        List<ReplyResponse> replies = repliesPage.getContent().stream()
                .map(ReplyMapper::toResponse)
                .toList();

        Page<ReplyResponse> responsePage = new PageImpl<>(
                replies,
                pagination,
                repliesPage.getTotalElements()
        );

        return ResponseEntity.ok(assembler.toModel(responsePage));
    }
}
