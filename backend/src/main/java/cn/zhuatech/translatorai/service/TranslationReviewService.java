/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.translatorai.service;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 依据术语覆盖、模型置信度与文档风险生成译审计划。
 *
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class TranslationReviewService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Result createReviewPlan(Request request) {
        int score = Math.max(0, 100 - request.modelConfidencePercent());
        score += Math.max(0, 85 - request.glossaryCoveragePercent()) / 2;
        if (request.confidential()) score += 15;
        if (request.legalDocument()) score += 25;
        score += Math.max(0, request.layoutComplexity() - 2) * 6;
        score = Math.min(100, score);
        String route = score >= 70 ? "SENIOR_REVIEW" : score >= 40 ? "BILINGUAL_REVIEW" : "SAMPLE_REVIEW";
        int minutes = Math.max(10, request.wordCount() / (score >= 70 ? 120 : score >= 40 ? 220 : 420));
        List<String> checks = new ArrayList<>(List.of("术语一致性", "数字与单位", "格式完整性"));
        List<String> controls = new ArrayList<>(List.of("保留源文、译文及版本证据", "发布前由责任人确认"));
        if (request.legalDocument()) checks.add("法律条款逐句对照");
        if (request.confidential()) controls.add("仅允许私有化处理并禁止外发语料");
        if (request.glossaryCoveragePercent() < 80) checks.add("补齐低覆盖术语并回译验证");
        return new Result(request.documentNo(), request.sourceLanguage() + "→" + request.targetLanguage(), score,
            score >= 70 ? "HIGH" : score >= 40 ? "MEDIUM" : "LOW", route, minutes, checks, controls);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(@NotBlank String documentNo, @NotBlank String sourceLanguage,
                          @NotBlank String targetLanguage, @Min(1) int wordCount,
                          @Min(0) @Max(100) int glossaryCoveragePercent,
                          @Min(0) @Max(100) int modelConfidencePercent,
                          @Min(1) @Max(5) int layoutComplexity,
                          boolean confidential, boolean legalDocument) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Result(String documentNo, String languagePair, int reviewScore, String riskTier,
                         String reviewRoute, int estimatedReviewMinutes,
                         List<String> checks, List<String> controls) {}
}
