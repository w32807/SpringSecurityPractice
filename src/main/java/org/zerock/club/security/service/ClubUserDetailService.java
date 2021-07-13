package org.zerock.club.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.club.entity.ClubMember;
import org.zerock.club.repository.ClubMemberRepository;
import org.zerock.club.security.dto.ClubAuthMemberDTO;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubUserDetailService implements UserDetailsService {
    // 시큐리티와 DB 연동
    private final ClubMemberRepository clubMemberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 먼저 입력 받은 id로 회원의 존재유무를 파악
        Optional<ClubMember> result = clubMemberRepository.findByEmail(username, false);

        if(!result.isPresent()) {throw new UsernameNotFoundException("check Email or Social");}

        ClubMember clubMember = result.get();

        ClubAuthMemberDTO clubAuthMemberDTO = new ClubAuthMemberDTO(
            clubMember.getEmail(),
            clubMember.getPassword(),
            clubMember.isFromSocial(),
            clubMember.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toSet()));

            clubAuthMemberDTO.setName(clubMember.getName());
            clubAuthMemberDTO.setFromSocial(clubMember.isFromSocial());

            return clubAuthMemberDTO;
    }
}
