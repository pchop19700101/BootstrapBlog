# 介绍 #
Java个人博客,适配电脑端和移动端

预览: [https://blog.porkchop.cn/](https://blog.porkchop.cn/)

#特点

- 博文搜索功能支持中文分词,关键字高亮
- 可随时更改背景图片
- 包含友情链接,福利部分与个人简介页面
- 支持分享博文
- 使用ueditor富文本编辑器,让文章更易排版

# 使用 #
- 安装jdk1.7+
- 安装mysql,并导入blog.sql
- 安装tomcat
- 配置solrcore

在solr/solrhome/collection1/conf/schema.xml中添加分词器和业务域

		<!--中文分词-->
		<fieldType name="text_ik" class="solr.TextField">
			<analyzer class="org.wltea.analyzer.lucene.IKAnalyzer"/>
		</fieldType>
		<field name="blog_title" type="text_ik" indexed="true" stored="true"/>
		<field name="blog_summary" type="text_ik" indexed="false" stored="true"/>
		<field name="blog_releaseDate" type="string" indexed="true" stored="true"/>
		<field name="blog_content" type="text_ik" indexed="true" stored="false"/>
		<field name="blog_keyword" type="text_ik" indexed="true" stored="false"/>

- 在tomcat上配置solr服务器,关联solrhome,并且加入IKAnalyzer
- 修改BootstrapBlog/BootstrapBlog-parent/BootstrapBlog-web/src/main/resources下的application-solr.xml的solr服务器地址和db.properties中的mysql地址
- 部署到tomcat上,并启动


# 注意事项 #
- 除了博客数据,其他都会缓存,所以修改数据库后,必须刷新缓存,接口:abc.com/refresh
- 后台登陆页面: abc.com/login.html 初始用户名:root 密码:123456
# 使用技术 #
- spring+springmvc+mybatis
- solr:全文检索
- ueditor:富文本编辑器
- maven:项目管理
- boostrap:前台页面搭建
- easyui:后台页面搭建
- druid:数据库连接池