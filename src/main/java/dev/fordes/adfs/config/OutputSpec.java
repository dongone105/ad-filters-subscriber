 package dev.fordes.adfs.config;
 
 import java.nio.file.Path;
 
 import dev.fordes.adfs.rule.model.RuleAction;

 public record OutputSpec(
        Path path, RuleType type, RuleDialect dialect, ContainerFormat container, String fileHeader,
        RuleAction actionFilter) {

    /** 兼容旧调用: 不指定 actionFilter 时视为不过滤(拦截与放行规则都写出)。 */
    public OutputSpec(Path path, RuleType type, RuleDialect dialect, ContainerFormat container, String fileHeader) {
        this(path, type, dialect, container, fileHeader, null);
    }
}
