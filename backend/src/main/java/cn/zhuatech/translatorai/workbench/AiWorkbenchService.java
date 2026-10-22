/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.translatorai.workbench;
import org.springframework.stereotype.Service;
import java.math.*;
import java.util.*;
import static cn.zhuatech.translatorai.workbench.WorkbenchInput.*;
/** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
@Service
public class AiWorkbenchService {
    /** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
    public Map<String,Object> execute(Map<String,Object> r){
        validate(r);

        keys(r,"source","translation","glossary");String source=text(r,"source",32000),target=text(r,"translation",32000);var pattern=java.util.regex.Pattern.compile("\\{\\{[^{}\\r\\n]{1,100}}}|\\$\\{[^{}\\r\\n]{1,100}}|%(?:\\d+\\$)?[sdif]|</?[A-Za-z][^>\\r\\n]{0,200}>");
        Map<String,Integer> left=new TreeMap<>(),right=new TreeMap<>();for(var pair:List.of(Map.entry(source,left),Map.entry(target,right))){var m=pattern.matcher(pair.getKey());while(m.find())pair.getValue().merge(m.group(),1,Integer::sum);}
        List<String> missing=new ArrayList<>(),extra=new ArrayList<>(),terms=new ArrayList<>();Set<String> all=new TreeSet<>(left.keySet());all.addAll(right.keySet());for(String token:all){int diff=left.getOrDefault(token,0)-right.getOrDefault(token,0);for(int i=0;i<Math.abs(diff);i++)(diff>0?missing:extra).add(token);}
        for(var entry:rows(r,"glossary",0,100)){keys(entry,"source","target");String from=text(entry,"source",200),to=text(entry,"target",200);if(source.contains(from)&&!target.contains(to))terms.add(from+" -> "+to);}
        return Map.of("valid",missing.isEmpty()&&extra.isEmpty()&&terms.isEmpty(),"missingTokens",missing,"unexpectedTokens",extra,"missingGlossaryTerms",terms,"checksSemanticQuality",false);

    }
}
