package com.plavsic.network.authorization.feign;

import com.plavsic.network.authorization.dto.RegisterRequest;
import com.plavsic.network.authorization.feign.config.ClientConfiguration;
import com.plavsic.network.authorization.feign.model.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "user",url = "${user.url}",configuration = ClientConfiguration.class)
public interface UserClient {
    @RequestMapping(method = RequestMethod.GET,value = "/api/v1/users/{username}")
    UserResponse getUser(@PathVariable("username") String username);

    @RequestMapping(method = RequestMethod.POST,value = "/api/v1/users")
    UserResponse createUser(@RequestBody RegisterRequest registerRequest);
}
