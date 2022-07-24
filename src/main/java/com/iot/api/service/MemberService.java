package com.iot.api.service;

import com.iot.api.model.Member;
import com.iot.api.model.common.SuccessResponse;
import com.iot.api.model.request.create.MemberCreationRequest;
import com.iot.api.model.request.filter.MemberFilter;
import com.iot.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public Member createMember(MemberCreationRequest request) {
        Member member = new Member();
        BeanUtils.copyProperties(request, member);
        return memberRepository.save(member);
    }

    public SuccessResponse getAllMember(MemberFilter memberFilter) {
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setData(memberRepository.findAll());
        successResponse.setStatus(HttpStatus.OK.value());
        successResponse.setMessage(HttpStatus.OK.name());
        return successResponse;
    }

    public SuccessResponse updateMember(Member reqMember) {
        SuccessResponse successResponse = new SuccessResponse();
        Optional<Member> optionalMember = memberRepository.findById(reqMember.getId());
        if (!optionalMember.isPresent()) {
            successResponse.setStatus(HttpStatus.NOT_MODIFIED.value());
            successResponse.setMessage("Member not present in the database");
//            throw new EntityNotFoundException("");
        } else {
            Member member = optionalMember.get();
            member.setLastName(reqMember.getLastName());
            member.setFirstName(reqMember.getFirstName());
            member.setStatus(reqMember.getStatus());
            member.setId(reqMember.getId());
            Member savedMember = memberRepository.save(member);
            if (savedMember != null && savedMember.getId() != null) {
                successResponse.setData(Collections.singletonList(memberRepository.findById(savedMember.getId())));
                successResponse.setStatus(HttpStatus.OK.value());
                successResponse.setMessage(HttpStatus.OK.name());
            } else {
                successResponse.setStatus(HttpStatus.NOT_MODIFIED.value());
                successResponse.setMessage(HttpStatus.NOT_MODIFIED.name());
            }
        }
        return successResponse;
    }


}
