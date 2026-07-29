package com.matchskills.user.service.configurations;

import com.matchskills.user.service.exceptions.customs.candidate.CandidateNotFoundException;
import com.matchskills.user.service.exceptions.customs.company.CompanyNotFoundException;
import com.matchskills.user.service.exceptions.customs.token.TokenExpiredException;
import com.matchskills.user.service.exceptions.customs.token.TokenInBlackListException;
import com.matchskills.user.service.exceptions.customs.token.TokenInvalidTypeException;
import com.matchskills.user.service.jwt.JwtService;
import com.matchskills.user.service.repositorys.CandidateRepository;
import com.matchskills.user.service.repositorys.CompanyRepository;
import com.matchskills.user.service.services.RedisBlackListService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final RedisBlackListService redisBlackListService;
    private final CandidateRepository candidateRepository;
    private final CompanyRepository companyRepository;

    public SecurityFilter(JwtService jwtService,
                          RedisBlackListService redisBlackListService,
                          CandidateRepository candidateRepository,
                          CompanyRepository companyRepository) {
        this.jwtService = jwtService;
        this.redisBlackListService = redisBlackListService;
        this.candidateRepository = candidateRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {

            var token = request.getHeader("Authorization");

            if (token != null && token.startsWith("Bearer ")) {

                token = token.replace("Bearer ", "");

                redisBlackListService.verifyIfBlacklisted(jwtService.getTokenId(token));

                var tokenDecoded = jwtService.decodeAccessToken(token);

                if (tokenDecoded.getRole().equals("company")) {

                    var company = companyRepository.findById(tokenDecoded.getUserId())
                            .orElseThrow(CompanyNotFoundException::new);

                    var authentication = new UsernamePasswordAuthenticationToken(company.toCompanyDomain(), null, company.toCompanyDomain().getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }

                if (tokenDecoded.getRole().equals("candidate")) {

                    var candidate = candidateRepository.findById(tokenDecoded.getUserId())
                            .orElseThrow(CandidateNotFoundException::new);

                    var authentication = new UsernamePasswordAuthenticationToken(candidate.toCandidateDomain(), null, candidate.toCandidateDomain().getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }


            }

            filterChain.doFilter(request, response);

        } catch (TokenExpiredException e){

            SecurityContextHolder.clearContext();

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("""
                    {\s
                        "status": 401,
                        "message": "Token is expired"
                     }\s
                   \s""");

        } catch (TokenInBlackListException e){

            SecurityContextHolder.clearContext();

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("""
                    {\s
                        "status": 401,
                        "message": "Token is blacklisted"
                     }\s
                   \s""");

        } catch (CandidateNotFoundException e){

            SecurityContextHolder.clearContext();

            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setContentType("application/json");
            response.getWriter().write("""
                    {\s
                        "status": 404,
                        "message": "Candidate not found in filter"
                     }\s
                   \s""");

        } catch (CompanyNotFoundException e){

            SecurityContextHolder.clearContext();

            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setContentType("application/json");
            response.getWriter().write("""
                    {\s
                        "status": 404,
                        "message": "Company not found in filter"
                     }\s
                   \s""");

        } catch (TokenInvalidTypeException e){

            SecurityContextHolder.clearContext();

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("""
                    {\s
                        "status": 401,
                        "message": "The type of this token is invalid"
                     }\s
                   \s""");
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String path = request.getServletPath();

        return path.startsWith("/auth/")
                && !path.equals("/auth/logout");
    }

}
