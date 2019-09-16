-- MySQL dump 10.13  Distrib 5.6.21, for osx10.8 (x86_64)
--
-- Host: 127.0.0.1    Database: adapterj-example
-- ------------------------------------------------------
-- Server version	5.6.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_schema`
--

DROP TABLE IF EXISTS `t_schema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_schema` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `insert_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `x_sqlite_ver` (`version`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_schema`
--

LOCK TABLES `t_schema` WRITE;
/*!40000 ALTER TABLE `t_schema` DISABLE KEYS */;
INSERT INTO `t_schema` VALUES (1,8,1558349426937,1563293272318,0);
/*!40000 ALTER TABLE `t_schema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_source`
--

DROP TABLE IF EXISTS `t_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_source` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `abbr` varchar(255) NOT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `domain` varchar(255) NOT NULL,
  `www` varchar(255) NOT NULL,
  `description` varchar(511) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `country` char(2) DEFAULT NULL,
  `language` char(5) DEFAULT NULL,
  `specific` varchar(511) DEFAULT NULL,
  `priority` int(11) NOT NULL,
  `latency` int(11) NOT NULL DEFAULT '300',
  `label1` varchar(255) DEFAULT NULL,
  `url1` varchar(255) DEFAULT NULL,
  `limit1` int(11) DEFAULT '0',
  `type1` int(11) DEFAULT NULL,
  `encoding1` varchar(255) DEFAULT NULL,
  `label2` varchar(255) DEFAULT NULL,
  `url2` varchar(255) DEFAULT NULL,
  `limit2` int(11) DEFAULT NULL,
  `type2` int(11) DEFAULT NULL,
  `encoding2` varchar(255) DEFAULT NULL,
  `label3` varchar(255) DEFAULT NULL,
  `url3` varchar(255) DEFAULT NULL,
  `limit3` int(11) DEFAULT NULL,
  `type3` int(11) DEFAULT NULL,
  `encoding3` varchar(255) DEFAULT NULL,
  `label4` varchar(255) DEFAULT NULL,
  `url4` varchar(255) DEFAULT NULL,
  `limit4` int(11) DEFAULT NULL,
  `type4` int(11) DEFAULT NULL,
  `encoding4` varchar(255) DEFAULT NULL,
  `label5` varchar(255) DEFAULT NULL,
  `url5` varchar(255) DEFAULT NULL,
  `limit5` int(11) DEFAULT NULL,
  `type5` int(11) DEFAULT NULL,
  `encoding5` varchar(255) DEFAULT NULL,
  `label6` varchar(255) DEFAULT NULL,
  `url6` varchar(255) DEFAULT NULL,
  `limit6` int(11) DEFAULT NULL,
  `type6` int(11) DEFAULT NULL,
  `encoding6` varchar(255) DEFAULT NULL,
  `label7` varchar(255) DEFAULT NULL,
  `url7` varchar(255) DEFAULT NULL,
  `limit7` int(11) DEFAULT NULL,
  `type7` int(11) DEFAULT NULL,
  `encoding7` varchar(255) DEFAULT NULL,
  `label8` varchar(255) DEFAULT NULL,
  `url8` varchar(255) DEFAULT NULL,
  `limit8` int(11) DEFAULT NULL,
  `type8` int(11) DEFAULT NULL,
  `encoding8` varchar(255) DEFAULT NULL,
  `label9` varchar(255) DEFAULT NULL,
  `url9` varchar(255) DEFAULT NULL,
  `limit9` int(11) DEFAULT NULL,
  `type9` int(11) DEFAULT NULL,
  `encoding9` varchar(255) DEFAULT NULL,
  `version` varchar(255) NOT NULL,
  `insert_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `x_source_www` (`www`),
  KEY `x_source_state` (`state`)
) ENGINE=InnoDB AUTO_INCREMENT=2000020 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_source`
--

LOCK TABLES `t_source` WRITE;
/*!40000 ALTER TABLE `t_source` DISABLE KEYS */;
INSERT INTO `t_source` VALUES (1,'Microsoft Bing','Bing','http://tse4.mm.bing.net/th?id=OIP.mBuywX9Z0FuvxduMjmydmwHaC_','bing.com','cn.bing.com',NULL,240,'CN','zh_CN',NULL,-1,500,'首页','http://cn.bing.com/',0,241,'utf-8',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'v2019_001',1558355957425,1559351817626,0),(2,'百度搜索','百度','http://a2.att.hudong.com/54/49/01300542446111139563495498728.jpg','baidu.com','www.baidu.com',NULL,240,NULL,NULL,NULL,-2,500,'首页','https://www.baidu.com/',0,241,'utf-8',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'v2019_001',1558356050556,1565091916081,0),(3,'搜狗搜索','搜狗','https://cn.bing.com/th?id=OIP.YjFUGgEuYgkO8hlZhb2DCQHaHa','sogou.com','www.bbwc.cn',NULL,240,'CN','zh_CN',NULL,-2,500,'首页','https://www.sogou.com/',0,241,'utf-8',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'for test update',1558356134579,1568352228198,0),(4,'梅花网','梅花网','http://www.meihua.info/icons/logo2.png','meihua.info','www.meihua.info','梅花网是中国市场营销专业领域内容最丰富，访问量位居前茅的网站。',0,NULL,NULL,'meihua.info/a/; zhishi.meihua.info/k/;',-1,500,'首页','http://www.meihua.info/',0,1,'utf-8','资讯','http://www.meihua.info/?page=1',0,2,'utf-8','知识','http://zhishi.meihua.info/',0,2,'utf-8',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'v2019_001',1558356979251,1565671164246,4607),(5,'数英网 DIGITALING','数英网','https://file.digitaling.com/images/common/logo.@2x.png','digitaling.com','www.digitaling.com','数英网DIGITALING，是大中华区权威数字媒体及职业招聘社交平台,内容涵盖市场营销、广告传媒、创意设计、电商、移动互联网等各数字相关领域。',0,'CN','zh_CN','digitaling.com/articles/; digitaling.com/projects/;',-1,500,'首页','https://www.digitaling.com/',0,1,'utf-8','文章·头条','https://www.digitaling.com/articles/headline',0,2,'utf-8','文章·精选','https://www.digitaling.com/articles/choice',0,2,'utf-8','文章·精选','https://www.digitaling.com/articles/hot/views',0,2,NULL,'项目','https://www.digitaling.com/projects',0,2,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'v2019_001',1558490501480,1561816886479,4607),(6,'中国公关网','中国公关网','http://www.chinapr.com.cn/static/assets/images/logo_dark.png','chinapr.com.cn','www.chinapr.com.cn','中国公关网，创办于1998年，是由中国国际公共关系协会(CIPRA)指导、CIPRA公关公司工作委员会投资监管的中国公共关系行业官方门户网站。',0,NULL,NULL,'chinapr.com.cn/p/;',-6,1500,'首页','http://www.chinapr.com.cn/',0,1,'utf-8','新闻','http://www.chinapr.com.cn/news/',0,2,'utf-8','干货','http://www.chinapr.com.cn/ganhuo/',0,2,'utf-8',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'v2019_001',1558497658405,1565094007952,4096),(7,'中国公共关系行业平台 17PR','17PR','http://www.17pr.com/images/index.2016.12.27/logo.png','17pr.com','www.17pr.com','17PR成立于2004年3月，是具有影响力的中国公共关系行业平台。',0,NULL,NULL,'www.17pr.com/news/;',-1,1000,'首页','http://www.17pr.com/',0,1,'utf-8','热点聚焦','http://www.17pr.com/news/list/13.html',0,2,'utf-8','实用干货','http://www.17pr.com/news/list/14.html',0,2,'utf-8','品牌动态','http://www.17pr.com/news/list/2.html',0,2,'utf-8','人物','http://www.17pr.com/news/list/6.html',0,2,'utf-8','行业报告','http://www.17pr.com/news/list/16.html',0,2,'utf-8','大事记','http://www.17pr.com/news/list/15.html',0,2,'utf-8',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'v2019_001',1558514632450,1565092547299,4607),(8,'中国广告网','中国广告网','http://www.cnad.com/newindex/images/logo_04.png','cnad.com','www.cnad.com','中国广告网(CNAD)，创立于1999年，以敏锐的触角掌握中国广告、媒体界的全新动态，成为国内享有知名度且规模比较大的广告媒体行业专业网站之一。',0,NULL,NULL,'cnad.com/show/;cnad.com/html/;',-6,1000,'首页','http://www.cnad.com/',0,1,'utf-8','资讯1','http://search.cnad.com/TagsList.aspx',0,2,'utf-8','资讯2','http://search.cnad.com/TagsList.aspx?tags=&page=2',0,2,'utf-8',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'v2019_001',1558520045758,1565094111468,4096),(9,'4A广告提案网','4A广告提案网','http://www.4aquan.com/wp-content/uploads/2016/01/logo.png','4aquan.com','www.4aquan.com','4A广告提案网，每天更新全球最新最热的广告圈资讯、创意。关注新知、广告、文案、创意、策划、设计、艺术、人文...。',0,'CN','zh_CN','www.4aquan.com;',-6,2500,'首页','http://www.4aquan.com/',0,1,'utf-8','业界','http://www.4aquan.com/dongtai',0,2,'utf-8','作品','http://www.4aquan.com/zuopin',0,2,'utf-8','社会化营销','http://www.4aquan.com/social',0,2,'utf-8','案例','http://www.4aquan.com/anli',0,2,'utf-8',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'v2019_001',1558538534679,1558540223351,4096),(10,'广告门','广告门','https://www.adquan.com/images/about_logo.png','adquan.com','www.adquan.com','广告门根植于营销传播产业，服务于品牌、广告公司、媒体三方及其从业人员，建立起优质的资源整合平台。',0,'CN','zh_CN',NULL,-7,500,'首页','https://www.adquan.com/',0,1,'utf-8',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'v2019_001',1558577472697,1561432860844,4096),(11,'TOPYS','TOPYS','https://www.topys.cn/Public/home/img/topys-logo.png','topys.cn','www.topys.cn','TOPYS成立于2003年，是中国成立最早、最受欢迎的创意垂直网站，专注在全球创意、艺术、人文领域寻找最有质感的声音。',0,NULL,NULL,'topys.cn/article/;',-1,500,'首页','https://www.topys.cn/',0,1,'utf-8','创意','https://www.topys.cn/category/7.html',0,2,'utf-8','设计','https://www.topys.cn/category/12.html',0,2,'utf-8','商业','https://www.topys.cn/category/8.html',0,2,'utf-8','艺术','https://www.topys.cn/category/11.html',0,2,'utf-8','文化','https://www.topys.cn/category/10.html',0,2,'utf-8','科技','https://www.topys.cn/category/9.html',0,2,'utf-8',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'v2019_001',1558577830232,1565092991695,4607),(12,'销售与市场·第一营销网','第一营销网','http://www.cmmo.cn/static/image/common/logo.jpg','cmmo.cn','www.cmmo.cn','«销售与市场»杂志创刊于1994年，是中国内地第一家大型营销专业期刊。第一营销网依托杂志强大的资源，成为中国最具影响力的营销专业服务网站。',0,'CN','zh_CN','cmmo.cn;',-7,1000,'首页','http://www.cmmo.cn/',0,1,'gbk',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'v2019_001',1558604205769,1558627500893,4096),(13,'网络广告人社区 iWEBAD','网络广告人社区','http://iwebad.com/images/newlogo_k.png','iwebad.com','iwebad.com','网络广告人社区(iWEBAD)创建于2007年12月，是一家致力于原创翻译国外营销案例的垂直类传媒网站。',0,'CN','zh_CN','iwebad.com/case/;',-2,500,'首页','http://iwebad.com/',0,1,'gb2312','整合营销','http://iwebad.com/interactive-marketing/integrated-marketing/',0,2,'gb2312','社会化营销','http://iwebad.com/interactive-marketing/social-media-marketing/',0,2,'gb2312','病毒营销','http://iwebad.com/interactive-marketing/viral-marketing/',0,2,'gb2312',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'v2019_001',1558688564885,1561821994899,4607),(14,'SocialBeta','SocialBeta','https://socialbeta.com/View/image/logo.png','socialbeta.com','socialbeta.com','SocialBeta是社交媒体和数字营销内容与招聘平台。分享营销动态、创意案例，营销趋势和实践经验，为来自品牌主、营销代理商和媒体平台从业者提供交流和学习。',0,NULL,NULL,'socialbeta.com/t/;',-1,500,'首页','https://socialbeta.com/',0,1,'utf8','营销周报','https://socialbeta.com/tag/%E8%90%A5%E9%94%80%E5%91%A8%E6%8A%A5',0,2,'utf-8','案例一周','https://socialbeta.com/tag/%E6%A1%88%E4%BE%8B%E4%B8%80%E5%91%A8',0,2,'utf-8',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'v2019_001',1561371093794,1565091960350,4607),(15,'麦迪逊邦','麦迪逊邦','http://www.madisonboom.com/wp-content/themes/madisonboom/images/14_124122_mblogo_0914_final.jpg','madisonboom.com','www.madisonboom.com','麦迪逊邦创办于2006年。前身是博客《懒男惰女的广告欲》，以及后来曾经使用过一段时间的《麦迪逊鼓吹》。麦迪逊邦为大中华区的营销人提供最新最快最好的行业资讯、作品案例、观点、洞察以及招聘信息。',0,'CN','zh_CN','madisonboom.com/2019/; madisonboom.com/2018/; madisonboom.com/2017/;',-3,1000,'首页','http://www.madisonboom.com/',0,1,'utf-8',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'v2019_001',1561822493323,1561822493323,4607),(16,'姑婆那些事儿','姑婆那些事儿','http://s1.gupowang.com/dist/static/img/logo.2314ea1.png','gupowang.com','www.gupowang.com','姑婆那些事儿采用新媒体运营，通过微信公众号、姑婆网、各大媒体专栏等媒体平台为广大用户分享以推广运营为主的各类互联网知识，并以持有专业的互联网技术、拥有大量的高质量粉丝及掌握极具影响力的合作渠道为优势，为有需求的企业提供各类APP推广服务。',0,'CN','zh_CN','gupowang.com/article/;',-8,1000,'首页','http://www.gupowang.com/',0,2,'utf-8',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'v2019_001',1561823229752,1561960351733,4096),(17,'Arting365','Arting365','http://arting365.com/assets/img/logo.svg','arting365.com','arting365.com','Arting365是一个关注全球设计影响力的媒体平台，庞大和活跃的用户群表现出极强的互动性。',0,NULL,NULL,'arting365.com/articles/;',-2,500,'首页','http://arting365.com/',0,1,'utf-8','设计','http://arting365.com/channel/design',0,2,'utf-8','品牌','http://arting365.com/channel/brand',0,2,'utf-8','商业','http://arting365.com/channel/biz',0,2,'utf-8','产品','http://arting365.com/channel/device',0,2,'utf-8','生活','http://arting365.com/channel/life',0,2,'utf-8',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'v2019_001',1561823573529,1565681949001,4607),(18,'PITCHINA','PITCHINA','https://www.pitchina.com.cn/Public/Home/images/logo_bot.png','pitchina.com.cn','www.pitchina.com.cn','作为专注分享全球优秀创意营销广告及行业动态的顶尖创意人社区，PITCHINA一直在坚持做原创和深度的内容。在内容方面，我们提供深度热门营销案例的解读；营销、广告、公关行业观点；国内外顶尖营销人、广告人、市场人的深度专访......',0,'CN','zh_CN','pitchina.com.cn;',-8,3000,'首页','https://www.pitchina.com.cn/',0,1,'utf-8',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'v2019_001',1561823877285,1561823877285,4096),(19,'鸟哥笔记','鸟哥笔记','http://www.niaogebiji.com/img/logo_011.png','niaogebiji.com','www.niaogebiji.com','鸟哥笔记成立于2010年，旗下现有移动互联网垂直媒体(鸟哥笔记官网/官方微信公众号/鸟哥笔记小程序)、资源合作平台(App换量推广小程序、BD市场合作小程序、全国各类社群)、App 推广流量平台(小鱼赚钱、小鸟星球、小鸟看看、小鸟应用市场)等品牌矩阵，致力于多维度提升行业效能，帮助从业者高效学习、高效合作，助力广告主高效获取真实流量。',0,'CN','zh_CN','www.niaogebiji.com/article;',-3,1000,'首页','http://www.niaogebiji.com/',0,1,'utf-8',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'v2019_001',1561824313348,1561892595924,4607),(20,'市场部网','市场部网','http://shichangbu.com/404/01.png','shichangbu.com','www.shichangbu.com','市场部网，是中国领先的市场人职业服务平台，成立于2006年，目前有超过1000万会员，在中国40多个城市设立了城市分会，在市场人职业群体中有强大的影响力与号召力。',0,'CN','zh_CN','shichangbu.com/article;',-7,1000,'首页','http://shichangbu.com/',0,1,'utf-8',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'v2019_001',1561824628057,1561824628057,4096),(21,'创意铺子','创意铺子','http://www.ioioz.com/wp-content/themes/Loostrive/images/logo.png','ioioz.com','www.ioioz.com','集合各类创意灵感的网站。',0,'CN','zh_CN','ioioz.com;',-9,1000,'创意广告','http://www.ioioz.com/ad',0,2,'utf-8',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'v2019_001',1561824856963,1561824856963,4096),(22,'waaaat?','waaaat?','https://www.egouz.com/uploadfile/2017/1013/20171013015422970.jpg','waaaat.com','waaaat.com','世界创意资讯分享平台。',0,'CN','zh_CN','waaaat.com/post/;',-9,1000,'首页','http://waaaat.com/',0,1,'utf-8',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,'v2019_001',1561825301295,1561825301295,4096);
/*!40000 ALTER TABLE `t_source` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_version`
--

DROP TABLE IF EXISTS `t_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_version` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_source` bigint(20) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `version` varchar(255) NOT NULL,
  `year` int(11) NOT NULL,
  `serial` int(11) NOT NULL,
  `insert_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `x_version_unique` (`id_source`,`type`,`year`,`serial`),
  KEY `x_version_state` (`state`),
  KEY `x_version_version` (`version`)
) ENGINE=InnoDB AUTO_INCREMENT=2000053 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_version`
--

LOCK TABLES `t_version` WRITE;
/*!40000 ALTER TABLE `t_version` DISABLE KEYS */;
INSERT INTO `t_version` VALUES (1,NULL,1,'v2019_001',2019,1,1558436812161,1558436812161,0),(2,NULL,16,'v2019_001',2019,1,1558436812161,1558436812161,0),(3,NULL,160,'v2019_001',2019,1,1558436812161,1558436812161,0),(4,NULL,240,'v2019_001',2019,1,1558436812161,1558436812161,0),(5,1,240,'v2019_001',2019,1,1558436923310,1558436923310,0),(6,2,240,'v2019_001',2019,1,1558437249747,1558437249747,0),(7,3,240,'v2019_001',2019,1,1558437397157,1558437397157,0),(8,4,1,'v2019_001',2019,1,1561818282485,1561818282485,0),(9,4,160,'v2019_001',2019,1,1558440438078,1558440438078,0),(10,4,16,'v2019_001',2019,1,1558438685253,1558438685253,0),(11,5,1,'v2019_001',2019,1,1558490501480,1558490501480,0),(12,5,160,'v2019_001',2019,1,1558491507403,1558491507403,0),(13,5,16,'v2019_001',2019,1,1558493537143,1558493537143,0),(14,6,1,'v2019_001',2019,1,1558501431922,1558501431922,0),(15,6,160,'v2019_001',2019,1,1558497658405,1558497658405,0),(16,6,16,'v2019_001',2019,1,1558498191278,1558498191278,0),(17,7,1,'v2019_001',2019,1,1558514632450,1558514632450,0),(18,7,160,'v2019_001',2019,1,1558515080154,1558515080154,0),(19,7,16,'v2019_001',2019,1,1558515716779,1558515716779,0),(20,8,1,'v2019_001',2019,1,1558520045758,1558520045758,0),(21,8,160,'v2019_001',2019,1,1558529867293,1558529867293,0),(22,8,16,'v2019_001',2019,1,1558531026400,1558531026400,0),(23,9,1,'v2019_001',2019,1,1558538534679,1558538534679,0),(24,9,160,'v2019_001',2019,1,1558539361691,1558539361691,0),(25,9,16,'v2019_001',2019,1,1558542760108,1558542760108,0),(26,10,1,'v2019_001',2019,1,1558577472697,1558577472697,0),(27,10,160,'v2019_001',2019,1,1561978414084,1561978414084,0),(28,10,16,'v2019_001',2019,1,1561983155525,1561983155525,0),(29,11,1,'v2019_001',2019,1,1558577830232,1558577830232,0),(30,11,160,'v2019_001',2019,1,1558578516522,1558578516522,0),(31,11,16,'v2019_001',2019,1,1558600006271,1558600006271,0),(32,12,1,'v2019_001',2019,1,1558604205769,1558604205769,0),(33,12,160,'v2019_001',2019,1,1558604991311,1558604991311,0),(34,12,16,'v2019_001',2019,1,1558620779013,1558620779013,0),(35,13,1,'v2019_001',2019,1,1558688564885,1558688564885,0),(36,13,160,'v2019_001',2019,1,1558705599597,1558705599597,0),(37,13,16,'v2019_001',2019,1,1558706189487,1558706189487,0),(38,14,1,'v2019_001',2019,1,1561371093794,1561371093794,0),(39,14,160,'v2019_001',2019,1,1561864974994,1561864974994,0),(40,14,16,'v2019_001',2019,1,1561950624260,1561950624260,0),(41,15,1,'v2019_001',2019,1,1561822493323,1561822493323,0),(42,15,160,'v2019_001',2019,1,1561867502152,1561867502152,0),(43,15,16,'v2019_001',2019,1,1561959158561,1561959158561,0),(44,16,1,'v2019_001',2019,1,1561823229752,1561823229752,0),(45,16,160,'v2019_001',2019,1,1561959756331,1561959756331,0),(46,16,16,'v2019_001',2019,1,1561960195153,1561960195153,0),(47,17,1,'v2019_001',2019,1,1561823573529,1561823573529,0),(48,17,160,'v2019_001',2019,1,1561889695490,1561889695490,0),(49,17,16,'v2019_001',2019,1,1561966828687,1561966828687,0),(50,18,1,'v2019_001',2019,1,1561823877285,1561823877285,0),(51,18,160,'v2019_001',2019,1,1561891751965,1561891751965,0),(52,19,1,'v2019_001',2019,1,1561824313348,1561824313348,0),(53,19,160,'v2019_001',2019,1,1561892407997,1561892407997,0),(54,19,16,'v2019_001',2019,1,1561971401689,1561971401689,0),(55,20,1,'v2019_001',2019,1,1561824628057,1561824628057,0),(56,20,160,'v2019_001',2019,1,1561893121705,1561893121705,0),(57,20,16,'v2019_001',2019,1,1561973050403,1561973050403,0),(58,21,1,'v2019_001',2019,1,1561824856963,1561824856963,0),(59,22,1,'v2019_001',2019,1,1561825301295,1561825301295,0);
/*!40000 ALTER TABLE `t_version` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-13 15:50:33
