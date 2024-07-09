package org.example.jpa.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jpa.api.ApiResponse;
import org.example.jpa.domain.member.converter.MemberConverter;
import org.example.jpa.domain.member.enums.SocialType;
import org.example.jpa.domain.member.repository.entity.Member;
import org.example.jpa.domain.member.service.MemberService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    @Operation(summary = "회원가입 API", description = "회원가입 시 request body에 정보를 담아 전달해주세요.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @PostMapping("/signup")
    public ApiResponse<MemberResponseDto.JoinResultDTO> join(@RequestBody @Valid MemberRequestDto.JoinDto request){

        Member member = memberService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @Operation(summary = "로그인 성공 시 사용자 정보 반환")
    @GetMapping("/loginSuccess")
    public void loginSuccess(@AuthenticationPrincipal OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        String provider = getProvider(oAuth2User);

        // 사용자가 구글로 로그인한 경우 SocialType을 GOOGLE로 설정하여 Member 객체 생성 또는 업데이트
        if ("google".equalsIgnoreCase(provider)) {
            memberService.processOAuthUser(email, SocialType.GOOGLE);
        }
        // 사용자가 네이버로 로그인한 경우 SocialType을 NAVER로 설정하여 Member 객체 생성 또는 업데이트
        else if ("naver".equalsIgnoreCase(provider)) {
            memberService.processOAuthUser(email, SocialType.NAVER);
        }
        // 기타 소셜 로그인 처리 로직 추가 가능
    }

    private String getProvider(OAuth2User oAuth2User) {
        String provider = oAuth2User.getAttributes().get("sub").toString();
        // sub에는 각 소셜 제공자 별 고유한 ID가 들어있음 (예: 구글의 경우 Google ID)
        // 이 예시에서는 provider를 구글(google)과 네이버(naver)로 구분함
        return provider;
    }

    @Operation(summary = "로그인 실패 시 오류 메시지 반환")
    @GetMapping("/loginFailure")
    public Map<String, String> loginFailure() {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Login failed");
        return response;
    }

    @Operation(summary = "로그아웃 성공 시 메시지 반환")
    @GetMapping("/logoutSuccess")
    public Map<String, String> logoutSuccess() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logout successful");
        return response;
    }
//    @GetMapping("/login")
//    public ApiResponse<String> login() {
//        return ApiResponse.onSuccess("로그인");
//    }
//
//    @GetMapping("/logout")
//    public ApiResponse<String> logout(HttpServletRequest request, HttpServletResponse response) {
//        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
//
//        return ApiResponse.onSuccess("로그아웃 성공");
//    }

}