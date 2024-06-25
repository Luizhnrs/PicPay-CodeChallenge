package com.luiz.picpay.client;

import com.luiz.picpay.client.dto.AuthResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "AuthClient",
        url = "${client.authorization-service.url}"
)

public interface AuthClient {
    @GetMapping
    ResponseEntity<AuthResponseDto> isAuth();
}
