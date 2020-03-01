package com.ivan.pinellia.secure.provider;



import com.ivan.pinellia.secure.constant.SecureConstant;

import com.ivan.pinellia.secure.model.UserVO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Custem user service
 *
 * @author wfnuser
 */
@Service
@AllArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {


    private final Logger log = LoggerFactory.getLogger(CustomUserDetailsServiceImpl.class);

    private final JdbcTemplate jdbcTemplate;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating user '{}'", login);
        String lowercaseLogin = login.toLowerCase(Locale.CHINA);
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SecureConstant.DEFAULT_SELECT_STATEMENT, new String[]{lowercaseLogin}, new BeanPropertyRowMapper<>(UserVO.class)))
                    .map(user -> createSpringSecurityUser(lowercaseLogin, user))
                    .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
        } catch (Exception e) {
            log.error("User {} was not found in the database", lowercaseLogin);
            throw new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database");
        }

    }

    private User createSpringSecurityUser(String lowercaseLogin, UserVO user) {
        if (user.getStatus() == 1) {
            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
        }

        List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoleName());

        return new User(user.getAccount(), user.getPassword(), authorityList);
    }
}
