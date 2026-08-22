/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.translatorai.controller;

import cn.zhuatech.translatorai.common.ApiResponse;
import cn.zhuatech.translatorai.service.TranslationReviewService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai/translation")
@PreAuthorize("hasAnyRole('DOMAIN_USER','DOMAIN_OPERATOR','ADMIN')")
public class TranslationReviewController {
    private final TranslationReviewService service;
    public TranslationReviewController(TranslationReviewService service) { this.service = service; }
    @PostMapping("/review-plan")
    public ApiResponse<TranslationReviewService.Result> create(@Valid @RequestBody TranslationReviewService.Request request) {
        return ApiResponse.ok("译审计划已生成", service.createReviewPlan(request));
    }
}
