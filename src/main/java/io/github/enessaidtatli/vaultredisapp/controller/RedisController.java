package io.github.enessaidtatli.vaultredisapp.controller;

import io.github.enessaidtatli.vaultredisapp.service.RedisService;
import org.springframework.web.bind.annotation.*;
/**
 * @author etatli on 13.05.2025 14:51
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @PostMapping("/{key}/{value}")
    public String set(@PathVariable String key, @PathVariable String value) {
        redisService.set(key, value);
        return "OK";
    }

    @GetMapping("/{key}")
    public String get(@PathVariable String key) {
        return redisService.get(key);
    }
}