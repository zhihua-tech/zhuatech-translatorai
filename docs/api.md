# TranslatorAI API

版权所有 © 2026 上海如静知华信息科技有限公司。

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| POST | `/api/auth/login` | 登录并获取 JWT |
| GET | `/api/admin/dashboard` | 翻译运营中心 |
| GET | `/api/admin/work-orders` | 翻译项目列表 |
| GET | `/api/shopfloor/dashboard` | 译审工作台 |
| POST | `/api/shopfloor/work-orders/{id}/reports` | 提交译审反馈 |
| POST | `/api/ai/translation/review-plan` | 生成译审路线与质量检查 |

译审接口输入语言对、字数、术语覆盖、模型置信度、版式复杂度、保密与法律属性；输出风险、译审路线、预计工时、检查项和控制措施。
