# web_demo_multi_tenant_separate_schema

This is a multi-tenant demo that uses a separate schema approach.

Some approaches:

1.ThreadLocal
AppContext + BaseDao

2.AOP + Connection switch schema
DaoAop + MarkDefaultDao + MarkTenantDao + DBContext + TenantRoutingDataSource + BaseDefaultDao + BaseTenantDao

3.Hibernate + Connection switch schema
//TODO

4.Hibernate 4.0+
hibernate.multiTenancy = SCHEMA
//TODO



ES SQL-DSL
http://www.nlpcn.org:9999/web/