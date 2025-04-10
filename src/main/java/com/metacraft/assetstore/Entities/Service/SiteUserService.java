package com.metacraft.assetstore.Entities.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.metacraft.assetstore.Entities.SiteUser;
import com.metacraft.assetstore.Entities.UserRole;
import com.metacraft.assetstore.Entities.Repository.SiteUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SiteUserService implements UserDetailsService {
  private final SiteUserRepository userRepo;
  private final PasswordEncoder passwordEncoder;

  // 회원가입 처리
  public SiteUser create(String username, String email, String pasword) {
    // 중복 검사
    if (userRepo.existsByUsername(username)) {
      throw new IllegalArgumentException("The username is already in use.");
    }
    if (userRepo.existsByEmail(email)) {
      throw new IllegalArgumentException("The email is already in use.");
    }
    
    SiteUser user = new SiteUser();
    user.setUsername(username);
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(pasword));
    return userRepo.save(user);
  }

  public SiteUser getSiteUser(String usernam) {
    Optional<SiteUser> user = userRepo.findByusername(usernam);
    if (user.isPresent())
      return user.get();
    else
      return null;
  }

  /**
   * 사용자 이름을 기반으로 사용자 정보를 조회하고,
   * 존재하면 해당 사용자의 권한을 설ㅈ어하여 반환한다.
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<SiteUser> _siteUser = this.userRepo.findByusername(username);
    if (_siteUser.isEmpty()) {
      throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
    }
    SiteUser siteUser = _siteUser.get();
    List<GrantedAuthority> authorities = new ArrayList<>();
    if ("admin".equals(username)) {
      authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
    } else {
      authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
    }
    return new User(siteUser.getUsername(), siteUser.getPassword(), authorities);
  }
}
