/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.translatorai.config;
import cn.zhuatech.translatorai.model.*; import cn.zhuatech.translatorai.repository.*; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.*; import org.springframework.security.crypto.password.PasswordEncoder; import java.time.LocalDate; import java.util.List;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Configuration public class DataInitializer {
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Bean CommandLineRunner seed(OperatingUnitRepository units,WorkRecordRepository tasks,ResourceRegisterRepository resources,ReviewRecordRepository reviews,UserRepository users,PasswordEncoder encoder){return args->{if(units.count()>0)return;
 var u1=units.save(new OperatingUnit("LANG-OPS","语言运营组","全球内容中心",300));var u2=units.save(new OperatingUnit("DOC-TEAM","产品文档组","工业产品中心",220));var u3=units.save(new OperatingUnit("LEGAL-LANG","法律语言组","国际法务中心",120));
 var t1=tasks.save(new WorkRecord("TR-260817-052","DOC-GW-032","智能网关安装手册 v3.2",u2,286,198,12,LocalDate.now().plusDays(1),WorkRecord.Status.RUNNING,"zh-CN→en-US"));var t2=tasks.save(new WorkRecord("TR-260817-048","LEGAL-EU-26","欧洲经销协议 2026",u3,164,82,9,LocalDate.now().plusDays(2),WorkRecord.Status.RUNNING,"zh-CN→de-DE"));var t3=tasks.save(new WorkRecord("TR-260817-043","APP-RN-826","移动端更新说明",u1,48,48,1,LocalDate.now(),WorkRecord.Status.RELEASED,"zh-CN→ja-JP"));var t4=tasks.save(new WorkRecord("TR-260816-036","SQM-GUIDE","供应商质量规范",u2,126,126,3,LocalDate.now().minusDays(1),WorkRecord.Status.COMPLETED,"zh-CN→en-US"));
 resources.saveAll(List.of(new ResourceRegister("TERM-CORE","企业核心术语库",u1,ResourceRegister.Status.RUNNING,98),new ResourceRegister("TM-PRODUCT","产品翻译记忆库",u2,ResourceRegister.Status.RUNNING,96),new ResourceRegister("MODEL-PRIVATE","私有翻译模型",u1,ResourceRegister.Status.IDLE,94)));
 reviews.saveAll(List.of(new ReviewRecord("LR-260817-032",t2,"高级译审",164,9,ReviewRecord.Result.PENDING,"陆闻"),new ReviewRecord("LR-260817-027",t3,"发布审批",48,1,ReviewRecord.Result.PASSED,"苏禾"),new ReviewRecord("LR-260817-018",t1,"质量复核",286,12,ReviewRecord.Result.FAILED,"陆闻")));
 String demo=encoder.encode("Demo@2026");users.saveAll(List.of(new UserAccount("operator",demo,"苏禾",UserAccount.Role.DOMAIN_USER,"DOC-TEAM"),new UserAccount("planner",demo,"陆闻",UserAccount.Role.DOMAIN_OPERATOR,null),new UserAccount("quality",demo,"顾清",UserAccount.Role.QUALITY,null),new UserAccount("admin",encoder.encode("ZhuaTech@2026"),"系统管理员",UserAccount.Role.ADMIN,null)));};}
}
