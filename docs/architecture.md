# TranslatorAI 架构

版权所有 © 2026 上海如静知华信息科技有限公司。

Vue 3 提供翻译运营端和双语译审 H5；Spring Boot 的 `TranslationReviewService` 生成可解释译审计划，任务、术语资源与审批证据通过 JPA、MySQL 和 Flyway 管理。

社区版不调用外部翻译 API。生产落地应采用私有模型或经批准的服务，并实施文档分级、加密、访问审计、保留周期和人工发布审批。
