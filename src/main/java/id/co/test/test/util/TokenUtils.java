package id.co.test.test.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.test.test.config.JsonConverterUtil;
import id.co.test.test.dto.MasterUserDto;
import id.co.test.test.dto.common.ForbiddenException;
import id.co.test.test.model.MasterUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Component
public class TokenUtils {
    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long EXPIRATION_TIME = 86400000;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public String generateToken(String username, MasterUser user) throws JsonProcessingException {
        HashMap<String, Object> claimData = new HashMap<>();
        claimData.put("username", user.getUsername());
        claimData.put("fullname", user.getFullname());
        claimData.put("email", user.getEmail());
        claimData.put("idUser", user.getIdUser().toString());

        HashMap<String, Object> wrappedClaim = new HashMap<>();
        wrappedClaim.put("userLogin", claimData);

        return Jwts.builder()
                .subject(username)
                .claims(wrappedClaim)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public void saveTokenToRedis(String username, String token) {
        redisTemplate.opsForValue().set("token:" + username, token);
    }

    public String getTokenFromRedis(String username) {
        return redisTemplate.opsForValue().get("token:" + username);
    }

    public void removeTokenFromRedis(String username) {
        redisTemplate.delete("token:" + username);
    }

    public static <T> T claimObjectValue(HttpServletRequest request, Class<T> clazz) {
        var claimData = claimValue(request);

        return JsonConverterUtil.fromObject(claimData, clazz);
    }

    public static Object claimValue(HttpServletRequest request) {
        var token = getToken(request);
        if (token == null) {
            throw new ForbiddenException();
        }
        return claimValue(token);
    }

    public static Object claimValue(String token) {
        try {
            if (token == null) return null;

            var claims = Jwts.parser()
                    .setSigningKey(key).build()
                    .parseClaimsJws(token.trim())
                    .getBody();

            HashMap<String, Object> userLoginClaim = claims.get("userLogin", HashMap.class);

            String username = (String) userLoginClaim.get("username");
            String fullname = (String) userLoginClaim.get("fullname");
            String email = (String) userLoginClaim.get("email");
            String idUser = (String) userLoginClaim.get("idUser");

            MasterUserDto masterUser = new MasterUserDto();
            masterUser.setUsername(username);
            masterUser.setFullname(fullname);
            masterUser.setEmail(email);
            masterUser.setIdUser(UUID.fromString(idUser));

            return masterUser;

        } catch (MalformedJwtException e) {
            System.out.println(e.getMessage());
            throw new ForbiddenException("Invalid Token");
        } catch (ExpiredJwtException e) {
            System.out.println(e.getMessage());
            return e.getClaims().get(key);
        } catch (UnsupportedJwtException e) {
            System.out.println(e.getMessage());
            throw new ForbiddenException("Unsupported Token");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new ForbiddenException("Token claims is empty");
        }
    }

    public static String getToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization").trim();
        if (token == null) return null;
        return token.replace("Bearer", "");
    }
}
