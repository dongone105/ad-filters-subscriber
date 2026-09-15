package dev.fordes.adfs.rule.model;

/** 推断任意规则条目的拦截/放行语义, 供输出按 action 过滤时使用。 */
public final class RuleActions {

    private RuleActions() {
    }

    public static RuleAction of(RuleEntry entry) {
        return switch (entry) {
            case DomainRule rule -> rule.action();
            case AdblockNetworkRule rule -> rule.action();
            case IpCidrRule rule -> rule.action();
            case CosmeticRule rule -> rule.exception() ? RuleAction.ALLOW : RuleAction.BLOCK;
            case HostMappingRule _ -> RuleAction.BLOCK;
            case RouteRule _ -> RuleAction.BLOCK;
            case DnsAddressRule _ -> RuleAction.BLOCK;
            case SafariRule safari -> of(safari.rule());
            case OpaqueRule opaque -> opaque.payload().startsWith("@@") ? RuleAction.ALLOW : RuleAction.BLOCK;
        };
    }
}
