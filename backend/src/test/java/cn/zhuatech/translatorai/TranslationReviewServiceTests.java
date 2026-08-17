/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.translatorai;
import cn.zhuatech.translatorai.service.TranslationReviewService; import org.junit.jupiter.api.Test; import static org.assertj.core.api.Assertions.assertThat;
class TranslationReviewServiceTests { private final TranslationReviewService service=new TranslationReviewService();
 @Test void routesLegalDocumentToSeniorReview(){var result=service.createReviewPlan(new TranslationReviewService.Request("DOC-9","zh-CN","en-US",8000,58,62,4,true,true));assertThat(result.reviewRoute()).isEqualTo("SENIOR_REVIEW");assertThat(result.controls()).anyMatch(x->x.contains("私有化"));}
 @Test void samplesHighConfidenceCopy(){var result=service.createReviewPlan(new TranslationReviewService.Request("DOC-10","zh-CN","ja-JP",600,96,95,1,false,false));assertThat(result.reviewRoute()).isEqualTo("SAMPLE_REVIEW");}}
