package org.zerock.club.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Log4j2
@Getter
@Setter
@ToString
// Spring Security에서 제공하는 User 클래스 사용
public class ClubAuthMemberDTO extends User {

    private String email;

    private String name;

    private boolean fromSocial;

    // 이와 같이 생성자를 구성하여 DTO역할을 수행할 수도있고, Spring Security의 인가/인증작업에 사용할 수 도 있다.
    public ClubAuthMemberDTO(String username, String password, boolean fromSocial, Collection<? extends GrantedAuthority> authorities){
        super(username, password, authorities);
        this.email = username;
        this.fromSocial = fromSocial;
    }
}
