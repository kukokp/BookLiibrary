package com.iot.api.controller;

import com.iot.api.model.Author;
import com.iot.api.model.Member;
import com.iot.api.model.common.SuccessResponse;
import com.iot.api.model.request.create.MemberCreationRequest;
import com.iot.api.model.request.filter.MemberFilter;
import com.iot.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/library")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/create-member")
    public ResponseEntity<Member> createMember(@RequestBody MemberCreationRequest memberCreationRequest) {
        return ResponseEntity.ok(memberService.createMember(memberCreationRequest));
    }

    @PostMapping("/get-member")
    public ResponseEntity<SuccessResponse<Member>> getMember(@RequestBody MemberFilter memberFilter) {
        return ResponseEntity.ok(memberService.getAllMember(memberFilter));
    }

    @PostMapping("/update-member")
    public ResponseEntity<SuccessResponse<Author>> updateMember(@RequestBody Member member) {
        return ResponseEntity.ok(memberService.updateMember(member));
    }


}
